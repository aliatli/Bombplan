package ModelSubsystem;

public class RandomBonus extends Bonus {

	private int randBonus;

	public RandomBonus( int x, int y) {
		super();
		super.x = x;
		super.y = y;
		// calculate randBonus: random number between 2-5
		super.type = randBonus;
	}

}