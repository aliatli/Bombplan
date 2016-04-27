package ModelSubsystem;

import java.util.Random;

public class Player extends MapObject implements Movable{

	private boolean multipleBomb = false;
	private int lives = 3;
	private boolean bombControllable = false;
	private int velocity = 4;
    //private CollisionManager colMan;
	private int maxBomb = 1;
	private int range;


	public boolean isMultipleBomb() {
		return this.multipleBomb;
	}
	public int getMaxBomb() { return this.maxBomb;}
	public void setMultipleBomb(boolean multipleBomb) {
		this.multipleBomb = multipleBomb;
	}

	public int takeBonus( Bonus bonus) {
		bonus.activateBonus();
		Random rand = new Random();
		int bonusType = bonus.getType();
		if(bonusType == 1){				//RandomBonus
			bonusType = rand.nextInt(5) + 2;
		}
		if( bonusType == 2) {     	   //bombNumberExtender
			this.maxBomb += 1;
			this.multipleBomb = true;
		}
		else if( bonusType == 3) {		//bombTimerCanceller
			this.bombControllable = true;
		}
		return bonusType;
	}

	public boolean isBombControllable() {
		return this.bombControllable;
	}

	public Player( int x, int y) {
		this.range = 1;
		this.x = x;
		this.y = y;
		this.destroyable = true;
		getIconFromFile("src/Sources/Images/player.png");
	}

	public int getVelocity() {
		return this.velocity;
	}

	public int getLife() {
		return this.lives;
	}
	public int getRange() {
		return this.range;
	}

	public void decreaseLife() {
		this.lives--;
	}

	public void move(int movement){
        if (movement == 0) this.setX(getX()+1);
        else if (movement == 1) this.setY(getY()+1);
        else if (movement == 2) this.setX(getX()-1);
        else if (movement == 3) this.setY(getY()-1);
	}
	public void increaseBomb(){
		maxBomb++;
	}

	public void increaseRange(){
		range++;
	}

}