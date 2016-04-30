package ModelSubsystem;

public class Bomb extends MapObject {

	private int range;

	public int getRange() {
		return this.range;
	}

	public Bomb(int x, int y, int range) {
		this.x = x;
		this.y = y;
		this.destroyable = true;
		this.range = range;
		getIconFromFile("src/Sources/Images/bomb.png");
	}

}