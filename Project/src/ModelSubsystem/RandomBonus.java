package ModelSubsystem;

public class RandomBonus extends Bonus {

	public RandomBonus( int x, int y) {
		super();
		super.x = x;
		super.y = y;
		super.type = 1;
		getIconFromFile("src/Sources/Images/randomBonus.png");
	}

}