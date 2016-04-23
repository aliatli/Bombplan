package ModelSubsystem;

public class RangeExtender extends Bonus {

	private int extendValue = 1;

	public int getExtendValue() {
		return extendValue;
	}

	public RangeExtender( int x, int y) {
		super();
		super.x = x;
		super.y = y;
		super.type = 4;
	}

}