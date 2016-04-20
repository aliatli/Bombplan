package ModelSubsystem;

import ControllerSubsystem.*;

public class Player extends MapObject implements Movable{

	private boolean multipleBomb = false;
	private int lives = 3;
	private boolean bombControllable;
	private int velocity;
    private CollisionManager colMan;

	public boolean isMultipleBomb() {
		return this.multipleBomb;
	}

	public void setMultipleBomb(boolean multipleBomb) {
		this.multipleBomb = multipleBomb;
	}

	/**
	 * 
	 * @param type
	 */
	public void takeBonus(int type) {
		// TODO - implement Player.takeBonus
		throw new UnsupportedOperationException();
	}

	public boolean isBombControllable() {
		return this.bombControllable;
	}

	public Player() {
		// TODO - implement Player.Player
		throw new UnsupportedOperationException();
	}

	public int getVelocity() {
		return this.velocity;
	}

	public int getLife() {
		// TODO - implement Player.getLife
		throw new UnsupportedOperationException();
	}

	public void decreaseLife() {
		// TODO - implement Player.decreaseLife
		throw new UnsupportedOperationException();
	}

	public void move(int movement){
        if (movement == 0) this.setX(getX()+1);
        else if (movement == 1) this.setY(getY()+1);
        else if (movement == 2) this.setX(getX()-1);
        else if (movement == 3) this.setY(getY()-1);

	}

}