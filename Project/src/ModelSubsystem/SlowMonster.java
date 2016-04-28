package ModelSubsystem;

public class SlowMonster extends Monster{
    public SlowMonster( int x, int y){
        this.x = x;
        this.y = y;
        this.type = 1;
        this.velocity = 3;
        this.destroyable = true;
        getIconFromFile("src/Sources/Images/slowMonster.png");
    }
}