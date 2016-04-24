package ModelSubsystem;

public class SlowMonster extends Monster{
    public SlowMonster( int x, int y){
        super.x = x;
        super.y = y;
        super.type = 1;
        super.velocity = 3;
        super.destroyable = true;
    }
}