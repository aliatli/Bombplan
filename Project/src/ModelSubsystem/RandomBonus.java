package ModelSubsystem;

public class RandomBonus extends Bonus {

	public RandomBonus( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.type = 1;
		getIconFromFile("src/Sources/Images/randomBonus.png");
	}

}