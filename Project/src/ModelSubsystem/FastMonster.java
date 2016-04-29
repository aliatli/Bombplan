package ModelSubsystem;

import java.awt.*;

public class FastMonster extends Monster{
    public FastMonster( int x, int y){
        this.x = x;
        this.y = y;
        type = 2;
        velocity = 5;
        destroyable = true;
        getIconFromFile("src/Sources/Images/fastMonster.png");
    }


}