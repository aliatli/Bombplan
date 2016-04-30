package ModelSubsystem;

public class FastMonster extends Monster{
    public FastMonster( int x, int y){
        this.x = x;
        this.y = y;
        this.draw_x = x * 64;
        this.draw_y = y * 64;
        type = 2;
        this.point = 20;
        destroyable = true;
        getIconFromFile("src/Sources/Images/fastMonster.png");
    }


}