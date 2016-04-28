package ModelSubsystem;

public class Door extends MapObject {

	private boolean active = false;

	public boolean isActive() {
		return this.active;
	}

	public void activate() {
		this.active = true;
	}

	public Door( int x, int y) {
		this.x = x;
		this.y = y;
		this.destroyable = true;
		getIconFromFile("src/Sources/Images/door.png");
	}
}