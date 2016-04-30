package ModelSubsystem;

public class Fire extends MapObject {

    private int destroyTime = 2;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
        this.destroyable = true;
        getIconFromFile("src/Sources/Images/fire.png");
    }

    public void decreaseTime() {
        destroyTime--;
    }

    public int getDestroyTime(){
        return destroyTime;
    }


}