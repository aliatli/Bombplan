package ModelSubsystem;

public class Bonus extends MapObject {
	/*
	type 1: RandomBonus
	type 2: BombNumberExtender
	type 3: BombTimerCanceller
	type 4: RangeExtender
	type 5: TimerReset
	*/
	protected int type;
	private int point;
	private boolean active = false;

	public boolean isActive() {
        return this.active;
    }

	public void activateBonus(){
		this.active = true;
	}
	protected Bonus() {
		super.destroyable = true;
		this.point = 100;
	}
	public int getType(){
		return this.type;
	}
	public int getPoint(){
		return point;
	}
}