package ControllerSubsystem;

import ModelSubsystem.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameEngine {
    private int BOMB_TIME = 5;
    private int score = 0;
    private int currentLevel;
    private int time;
    private boolean paused;
    private boolean destroyBombs;
    private HashMap<Bomb, Integer> bombTimers;
    private ArrayList<Integer> movements;
    private GameEngine uniqueInstance;
    private TimerListener timeListener;
    private Timer timer;
    private CollisionManager colMan;
    private SoundManager souMan;
	GameMap map;

    private GameEngine(){
        paused = false;
        currentLevel = 1;
        time = 0;
        movements = new ArrayList<Integer>();
        destroyBombs = false;
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



	public void movePlayer(int movement) {
        Player player = map.getPlayer();
        int t_x = player.getX();
        int t_y = player.getY();
        player.move(movement);
        map.setObject(null, t_x, t_y);

        int collision = colMan.checkCollision(player.getX(), player.getY(), map.getMap());
        if (collision == 1) {
            player.move((movement + 2) % 4);
        }
        else if (collision == 2) {
            ArrayList<MapObject> slot = map.getObj(player.getX(), player.getY());
            for (int i = 0; i < slot.size(); i++) {
                if (slot.get(i) instanceof Bonus) {
                    takeBonus((Bonus) slot.get(i));
                    score+= ((Bonus)slot.get(i)).getPoint();
                }
            }
        }
        else if (collision == -1){
            stopGame();
        }
        map.setObject(player);
	}

	public void startGameLoop() {
        if (paused)
            paused = false;
            timer.start();
	}

    public void stopGame() {
        if (!paused)
            paused = true;
        timer.stop();
    }
    
	public void plantBomb() {
		Player player = map.getPlayer();
        Bomb bomb = new Bomb(player.getX(), player.getY(), player.getRange());

        map.addObject(bomb);
        if (!player.isBombControllable())
            bombTimers.put(bomb, BOMB_TIME);

	}

	public void changeSound() {
        souMan.changeSound();
	}

	public void options() {
        //TODO - what should this method do AMK?
	}

	public void nextLevel() {
		this.stopGame();
        map.constructLevel(++currentLevel);
        this.startGameLoop();
	}

    public void healthDecrease(){
        map.getPlayer().decreaseLife();
        if (map.getPlayer().getLife() == -1){

        }
    }
	/**
	 *
     * @param objects
     */
	public void destroyObjects(ArrayList<MapObject> objects) {
		map.removeObjects((MapObject[]) objects.toArray());
	}

	/**
	 * 
	 * @param bonus
	 */
	public void takeBonus(Bonus bonus) {
        map.getPlayer().takeBonus(bonus);
	}


	public GameEngine getInstance() {
        return this.uniqueInstance;
	}

    public void moveMonsters(){
        ArrayList<Monster> monsters = map.getMonsters();
        for (Monster monster : monsters){
            int t_x = monster.getX();
            int t_y = monster.getY();
            int direction = ((int)(Math.random() * 4)) % 4;

            while(checkPossible(monster, direction)){
                direction = ((int)(Math.random() * 4)) % 4;
            }

            monster.randomizedMove(direction);
            map.setObject(null, t_x, t_y);

            int collision = colMan.checkCollision(monster.getX(), monster.getY(), map.getMap());
            if (collision == -1){
                map.setObject(monster);
                healthDecrease();
                break;
            }
            else {
                map.setObject(monster);
            }
        }
    }

    private boolean checkPossible(Monster m, int direction){
        ArrayList<MapObject> slot = map.getMap()[m.getX()][m.getY()];
        if (slot == null)
            return true;
        for (int i = 0; i < slot.size(); i++) {
            if (slot.get(i) instanceof Wall)
                return false;
        }
        return true;
    }

    public void setDestroyBombs(){
        if (map.getPlayer().isBombControllable())
            destroyBombs = true;
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!paused){
                time++;
                if (!movements.isEmpty()){
                    for (int i = 0;movements.size()!= 0;){
                        movePlayer(movements.get(i));
                        movements.remove(i);
                    }
                }
                if (time % 2 == 1)
                    moveMonsters();

                if (!map.getPlayer().isBombControllable()) {
                    Set<Bomb> keys = bombTimers.keySet();
                    for (Bomb key : keys) {
                        if (bombTimers.get(key) != 0) {
                            bombTimers.put(key, bombTimers.get(key) - 1);
                        } else {
                            bombTimers.remove(key);
                            key.destroy();
                            ArrayList<MapObject> colliding = colMan.checkCollision(key.getRange(), key, map.getMap());
                            destroyObjects(colliding);
                        }
                    }
                }
                else if (destroyBombs){
                    Set<Bomb> keys = bombTimers.keySet();
                    for (Bomb key : keys) {
                        bombTimers.remove(key);
                        key.destroy();
                        ArrayList<MapObject> colliding = colMan.checkCollision(key.getRange(), key, map.getMap());
                        destroyObjects(colliding);
                    }
                    destroyBombs = false;
                }
                
                score += 5;
            }
        }
    }



}
