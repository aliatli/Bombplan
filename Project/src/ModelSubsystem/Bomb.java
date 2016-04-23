package ModelSubsystem;

public class Bomb extends MapObject implements Destroyable {

	private int destroyTime = 3;
	private int range;

	public boolean isControllable() {
		return ( destroyTime < 0);
	}

	public void setControllable(boolean control) {
		if( control)
			this.destroyTime = -1;
		else
			this.destroyTime = 3;
	}


	public int getRange() {
		return this.range;
	}

	public Bomb(int x, int y, int range) {
		super.x = x;
		super.y = y;
		super.destroyable = true;
		this.range = range;
	}

	@Override
	public void destroy() {

	}
}