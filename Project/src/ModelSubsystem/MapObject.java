package ModelSubsystem;

import java.awt.*;

public class MapObject {

	private Image icon;
	protected boolean destroyable;
	protected int x;
	protected int y;

	public boolean isDestroyable() {
		return this.destroyable;
	}

	public void draw() {
		// TODO - implement MapObject.draw
		throw new UnsupportedOperationException();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}


	public Rectangle getCollisionBoundary() {
		// TODO - implement MapObject.getCollisionBoundary
		throw new UnsupportedOperationException();
	}

}