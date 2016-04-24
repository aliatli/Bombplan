package ModelSubsystem;

public class Monster extends MapObject {

	/*
	type 1: slow monster
	type 2: fast monster
	*/

	protected int type;
	protected int velocity;

	public void randomizedMove() {
		// TODO - implement Monster.randomizedMove
		throw new UnsupportedOperationException();
	}

	public int getVelocity() {
		return this.velocity;
	}
}