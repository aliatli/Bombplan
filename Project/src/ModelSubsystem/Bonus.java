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


	protected Bonus() {
		this.destroyable = true;
		this.point = 100;
	}

	/**
	 *
	 * @return type
     */
	public int getType(){
		return this.type;
	}

	/**
	 *
	 * @return point
     */
	public int getPoint(){
		return point;
	}
}