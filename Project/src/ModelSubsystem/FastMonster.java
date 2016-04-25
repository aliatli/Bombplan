package ModelSubsystem;

public class FastMonster extends Monster{
    public FastMonster( int x, int y){
        super.x = x;
        super.y = y;
        super.type = 2;
        super.velocity = 5;
        super.destroyable = true;
        getIconFromFile("fastMonster.png");
    }
}