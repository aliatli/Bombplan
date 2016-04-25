package ModelSubsystem;

public class NondestroyableWall extends Wall {

	public NondestroyableWall(int x, int y) {
		super.x = x;
		super.y = y;
		super.type = 2;
		getIconFromFile("src/Sources/Images/nonDestroyableWall.png");
	}

}