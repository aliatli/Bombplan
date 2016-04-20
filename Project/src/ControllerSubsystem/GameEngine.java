package ControllerSubsystem;

import UserInterfaceSubsystem.*;
import ModelSubsystem.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEngine {

	ScreenView notified;
	GameMap map;
	private int currentLevel;
	private int score = 0;
	private boolean paused;
	private GameEngine uniqueInstance;
    private int time;

    private GameEngine(){
        paused = false;
        currentLevel = 1;
        time = 0;
        map.constructLevel(1);


    }
	public GameEngine createGame() {
		if(uniqueInstance == null){
			uniqueInstance = new GameEngine();
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
        map.get
	}

	public void startGameLoop() {
		// TODO - implement GameEngine.startGameLoop
		throw new UnsupportedOperationException();
	}

	public void plantBomb() {
		// TODO - implement GameEngine.plantBomb
		throw new UnsupportedOperationException();
	}

	public void changeSound() {
		// TODO - implement GameEngine.changeSound
		throw new UnsupportedOperationException();
	}

	public void options() {
		// TODO - implement GameEngine.options
		throw new UnsupportedOperationException();
	}

	public void nextLevel() {
		// TODO - implement GameEngine.nextLevel
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param objects
	 */
	public void destroyObjects(MapObject[] objects) {
		// TODO - implement GameEngine.destroyObjects
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bonus
	 */
	public void takeBonus(Bonus bonus) {
		// TODO - implement GameEngine.takeBonus
		throw new UnsupportedOperationException();
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
