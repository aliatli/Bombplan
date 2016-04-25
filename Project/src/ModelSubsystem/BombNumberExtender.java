package ModelSubsystem;

public class BombNumberExtender extends Bonus {

	public BombNumberExtender( int x, int y) {
		super();
		super.x = x;
		super.y = y;
		super.type = 2;
		getIconFromFile("bombNumberExtender.png");
	}
}