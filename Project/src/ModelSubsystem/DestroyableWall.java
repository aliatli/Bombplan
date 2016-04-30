package ModelSubsystem;

public class DestroyableWall extends Wall {

	private int point;

	/**
	 *
	 * @return point;
     */
	public int getPoint(){ return this.point;}

	public DestroyableWall(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = 1;
		point = 10;
		getIconFromFile("src/Sources/Images/destroyableWall.png");
	}
}