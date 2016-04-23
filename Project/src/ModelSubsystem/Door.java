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
		super.x = x;
		super.y = y;
		super.destroyable = true;
	}
}