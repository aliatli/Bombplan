package ModelSubsystem;

public class BombNumberExtender extends Bonus {

	public BombNumberExtender( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.type = 2;
		getIconFromFile("src/Sources/Images/bombNumberExtender.png");
	}
}