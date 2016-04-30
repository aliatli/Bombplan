package ModelSubsystem;

import java.awt.*;
import java.util.Random;

public class Player extends MapObject implements Movable{

	private int lives = 10;
	private boolean bombControllable = false;
	private int maxBomb = 1;
	private int range;

	/**
	 *
	 * @return maxBomb
     */
	public int getMaxBomb() { return this.maxBomb;}

	/**
	 *
	 * @param bonus
	 * @return bonusType
     */
	public int takeBonus( Bonus bonus) {
		Random rand = new Random();
		int bonusType = bonus.getType();
		if(bonusType == 1){				//RandomBonus
			bonusType = rand.nextInt(5) + 2;
		}
		if( bonusType == 2) {     	   //bombNumberExtender
			this.maxBomb += 1;
		}
		else if( bonusType == 3) {		//bombTimerCanceller
			this.bombControllable = true;
		}
		return bonusType;
	}

	/**
	 *
	 * @return bombControllable
     */
	public boolean isBombControllable() {
		return this.bombControllable;
	}

	/**
	 *
	 * @param val
     */
	public void setBombControllable(boolean val){
		this.bombControllable = val;
	}

	/**
	 *
	 * @param x
	 * @param y
     */
	public Player( int x, int y) {
		this.range = 1;
		this.x = x;
		this.y = y;
		this.draw_x = x*64;
		this.draw_y = y*64;
		this.destroyable = true;
		getIconFromFile("src/Sources/Images/player.png");
	}

	/**
	 *
	 * @return lives
     */
	public int getLife() {
		return this.lives;
	}

	/**
	 *
	 * @return range
     */
	public int getRange() {
		return this.range;
	}


	public void decreaseLife() {
		this.lives--;
	}

	/**
	 *
	 * @param movement
     */
	public void move(int movement){
        if (movement == 0){
			this.setX(getX()+1);

		}
        else if (movement == 1){
			this.setY(getY()-1);
		}
        else if (movement == 2){
			this.setX(getX()-1);
		}
        else if (movement == 3){
			this.setY(getY()+1);
		}


	}

	/**
	 *
	 */
	public void increaseBomb(){
		maxBomb++;
	}

	/**
	 *
	 */
	public void increaseRange(){
		range++;
	}

	/**
	 *
	 * @param g
     */
	public void draw(Graphics g) {

		icon.paintIcon(null, g, this.getIncrementX(), this.getIncrementY());

	}
}