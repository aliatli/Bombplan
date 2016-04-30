package ModelSubsystem;

import java.awt.*;

public class Monster extends MapObject {

	/*
	type 1: slow monster
	type 2: fast monster
	*/

	protected int type;
	protected int point;

	public void move(int movement){
		if (movement == 0) this.setX(getX()+1);
		else if (movement == 1) this.setY(getY()-1);
		else if (movement == 2) this.setX(getX()-1);
		else if (movement == 3) this.setY(getY()+1);
	}


	public void draw(Graphics g) {

		icon.paintIcon(null, g, this.getIncrementX(), this.getIncrementY());

	}

	public int getPoint(){
		return point;
	}
}