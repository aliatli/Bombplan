package ModelSubsystem;

public class NondestroyableWall extends Wall {

	public NondestroyableWall(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = 2;
		getIconFromFile("src/Sources/Images/nonDestroyableWall.png");
	}

}