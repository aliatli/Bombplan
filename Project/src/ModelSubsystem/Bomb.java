package ModelSubsystem;

public class Bomb extends MapObject implements Destroyable {

	private int destroyTime = 3;
	private int range = 1;

	public boolean isControllable() {
		return ( destroyTime < 0);
	}

	public void setControllable() {
		this.destroyTime = -1;
	}

	public void setRange( int range) {
		// range can be maximum 4 blocks
		this.range += range;
		if( this.range > 4)
			this.range = 4;
	}

	public int getRange() {
		return this.range;
	}

	public Bomb(int x, int y) {
		super.x = x;
		super.y = y;
		super.destroyable = true;
	}

	@Override
	public void destroy() {

	}
}