package ModelSubsystem;

public class RangeExtender extends Bonus {

	private int extendValue = 1;

	public RangeExtender( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.type = 4;
		getIconFromFile("src/Sources/Images/rangeExtender.png");
	}

}