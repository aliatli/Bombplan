package ModelSubsystem;

public class Door extends MapObject {

	public Door( int x, int y) {
		this.x = x;
		this.y = y;
		this.destroyable = true;
		getIconFromFile("src/Sources/Images/door.png");
	}
}