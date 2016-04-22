package ModelSubsystem;

import java.awt.*;

public class MapObject {

	private Image icon;
	private boolean destroyable;
	private int x;
	private int y;

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

	public int setX(int x) {
		this.x = x;
	}

	public int setY(int y) {
		this.y = y;
	}

	public Rectangle getCollisionBoundary() {
		// TODO - implement MapObject.getCollisionBoundary
		throw new UnsupportedOperationException();
	}

}