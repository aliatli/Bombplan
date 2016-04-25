package ModelSubsystem;

public class DestroyableWall extends Wall {

	private int point = 200;

	public int getPoint(){ return this.point;}

	public DestroyableWall(int x, int y) {
		super.x = x;
		super.y = y;
		super.type = 1;
		getIconFromFile("destroyableWall.png");
	}
}