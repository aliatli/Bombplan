import UserInterfaceSubsystem.*;
import ModelSubsystem.*;

public class GameEngine {

	ScreenView notified;
	GameMap modified;
	private int currentLevel;
	private int score = 0;
	private boolean paused;
	private GameEngine engine;
	private GameEngine uniqueInstance;

	public void createGame() {
		// TODO - implement GameEngine.createGame
		throw new UnsupportedOperationException();
	}

	public void resumeGame() {
		// TODO - implement GameEngine.resumeGame
		throw new UnsupportedOperationException();
	}

	public void stopGame() {
		// TODO - implement GameEngine.stopGame
		throw new UnsupportedOperationException();
	}

	public void movePlayer() {
		// TODO - implement GameEngine.movePlayer
		throw new UnsupportedOperationException();
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

	public GameEngine getEngine() {
		return this.engine;
	}

	public GameEngine getInstance() {
		// TODO - implement GameEngine.getInstance
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

}