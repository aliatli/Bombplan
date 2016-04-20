package ControllerSubsystem;

import UserInterfaceSubsystem.*;
import ModelSubsystem.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GameEngine {
    private int BOMB_TIME = 5;
    private int score = 0;
    private int currentLevel;
    private boolean paused;
    private GameEngine uniqueInstance;
    private int time;
    private CollisionManager colMan;
    private SoundManager souMan;
    private HashMap<Bomb, Integer> bombTimers;
    private TimerListener timeListener;
    private Timer timer;

	ScreenView notified;
	GameMap map;

    private GameEngine(){
        paused = false;
        currentLevel = 1;
        time = 0;
        map.constructLevel(1);


    }
	public GameEngine createGame() {
		if(uniqueInstance == null){
			uniqueInstance = new GameEngine();
            timeListener = new TimerListener();
            timer = new Timer(300, timeListener);
            return uniqueInstance;
		}
        else{
            return uniqueInstance;
        }
	}

	public void resumeGame() {
		paused = false;
	}

	public void stopGame() {
        paused = true;
	}

	public void movePlayer(int movement) {
        Player player = map.getPlayer();
        player.move(movement);
        if (colMan.checkCollision(player.getX(), player.getY(), map.getMap()) == 1) {
            player.move((movement + 2) % 4);
        }
        if (colMan.checkCollision(player.getX(), player.getY(), map.getMap()) == 2) {
            takeBonus(map.getObj(player.getX(), player.getY()));
        }
	}

	public void startGameLoop() {
		// TODO - implement GameEngine.startGameLoop
		throw new UnsupportedOperationException();
	}

	public void plantBomb() {
		Player player = map.getPlayer();
        Bomb bomb = new Bomb(player.getX(), player.getY());

        map.addObject(bomb);
        if (!player.isBombControllable())
            bombTimers.put(bomb, BOMB_TIME);

	}

	public void changeSound() {
        souMan.changeSound();
	}

	public void options() {
        //TODO - what should this method do ANA?
	}

	public void nextLevel() {
		this.stopGame();
        map.constructLevel(++currentLevel);
        this.startGameLoop();
	}

	/**
	 * 
	 * @param objects
	 */
	public void destroyObjects(MapObject[] objects) {
		map.removeObjects(objects);
	}

	/**
	 * 
	 * @param bonus
	 */
	public void takeBonus(Bonus bonus) {
        map.getPlayer().takeBonus(bonus.getType());
	}


	public GameEngine getInstance() {
        return this.uniqueInstance;
	}


    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!paused){
                time++;
            }
        }
    }



}
