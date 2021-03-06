package ControllerSubsystem;

import ModelSubsystem.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class GameEngine {
    private final int BOMB_TIME = 5;
    private final int DEFAULT_TIME = 500;

    private int score;
    private int currentLevel;
    private int time;
    private boolean paused;
    private boolean destroyBombs;
    private HashMap<Bomb, Integer> bombTimers;
    private ArrayList<Fire> fireTimers;
    private ArrayList<Integer> movements;
    private CollisionManager colMan;
  //  private SoundManager souMan;
    private static GameEngine uniqueInstance;
    private StorageManager storageMan;
    private Player player;
    private boolean soundEffect;
    private boolean musicEffect;
    private int a = 0;      // Used for timers!



    private GameEngine(){
        paused = true;
        currentLevel = 1;
        score = 0;
        time = DEFAULT_TIME;
        movements = new ArrayList<Integer>();
        destroyBombs = false;
        storageMan = new StorageManager();
        colMan = new CollisionManager();
    //    souMan = new SoundManager();

        bombTimers = new HashMap<Bomb, Integer>();
        fireTimers = new ArrayList<Fire>();
        try {
            GameMap.getInstance().constructLevel(currentLevel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param engine
     */
    public static void setUniqueInstance(GameEngine engine){
        uniqueInstance = engine;
    }

    /**
     *
     * @return score
     */
    public int getScore(){
        return this.score;
    }

    /**
     *
     * @return level
     */
    public int getLevel(){
        return this.currentLevel;
    }

    /**
     *
     * @return time
     */
    public int getTime(){
        return this.time;
    }

    /**
     * Restarts the engine!
     */
    public void restart(){

        uniqueInstance = new GameEngine();
    }

    /**
     * pause/Unpause
     * @param setVal
     */
    public void setPaused(boolean setVal){
        paused = setVal;
    }

    /**
     *
     * @return StorageManager
     */
    public StorageManager getStorageMan(){
        return storageMan;
    }

    /**
     *
     * @param movement
     * @throws Exception
     */
	private void movePlayer(int movement) throws Exception {
        player = GameMap.getInstance().getPlayer();
        int t_x = player.getX();
        int t_y = player.getY();
        int x = t_x;
        int y = t_y;

        if (movement == 0)
            x++;
        else if (movement == 1)
            y--;
        else if (movement == 2)
            x--;
        else if (movement == 3)
            y++;
        else if (movement == 4) {
            if (bombTimers.size() < player.getMaxBomb())
                plantBomb();
            return;
        }
        else if (movement == 5){
            if (player.isBombControllable()){
                destroyBombs = true;
            }
            return;
        }


        int collision = colMan.checkCollision(x, y, GameMap.getInstance().getMap());

        GameMap.getInstance().removeObject(player);
        if (collision != 1) {
            player.move(movement);
        }

        if (collision == 2) {
            ArrayList<MapObject> slot = GameMap.getInstance().getObj(player.getX(), player.getY());
            for (int i = 0; i < slot.size(); i++){
                if (slot.get(i) instanceof Bonus) {
                    Bonus bonus = (Bonus)slot.get(i);
                    takeBonus(bonus);
                    GameMap.getInstance().removeObject(bonus);
                    score+= bonus.getPoint();
                }
            }
        }
        else if(collision == 3){
            throw new Exception("nextLevel");
        }
        else if (collision == -1){
            healthDecrease();
        }
        GameMap.getInstance().addObject(player);

    }

    /**
     * start the timer!
     */
	public void startGameLoop() {
        if (paused)
            paused = false;
	}

    /**
     * plants bomb
     */
	private void plantBomb() {
		Player player = GameMap.getInstance().getPlayer();
        Bomb bomb = new Bomb(player.getX(), player.getY(), player.getRange());

        GameMap.getInstance().addObject(bomb);
        bombTimers.put(bomb, BOMB_TIME);

	}

    /**
     * returns the movements in ArrayList<Integer> form
     * @return movements
     */
    public ArrayList<Integer> getMovements(){
        return movements;
    }

    /**
     * fetches the nextLevel, handles the end of the game scenario
     * @throws Exception
     */
	public void nextLevel() throws Exception {
        if (currentLevel != 4) {
            paused = true;
            currentLevel++;
            time = DEFAULT_TIME;
            movements = new ArrayList<Integer>();
            destroyBombs = false;
            storageMan = new StorageManager();
            colMan = new CollisionManager();
            bombTimers = new HashMap<Bomb, Integer>();
            fireTimers = new ArrayList<Fire>();
            try {
                GameMap.getInstance().constructLevel(currentLevel);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new Exception("gameOver!");
        }
	}

    /**
     * decreases the health of the player
     * @throws Exception
     */
    private void healthDecrease() throws Exception {
        player.decreaseLife();

        if (player.getLife() == -1){
            gameOver();
        }
    }

    /**
     *
     * @throws Exception
     */
    private void gameOver() throws Exception {
        throw new Exception("gameOver!");
    }
	/**
	 *
     * @param objects
     */
	private void destroyObjects(ArrayList<MapObject> objects) throws Exception {

        for (int i = 0; i < objects.size(); i++){
            MapObject obj = objects.get(i);
            if (obj instanceof Player) {
                objects.remove(obj);
                player = (Player) obj;
                healthDecrease();
                i--;
                score -= 10;
            }
            if (obj instanceof DestroyableWall)
                score += ((DestroyableWall) obj).getPoint();
            if (obj instanceof Monster)
                score += ((Monster)obj).getPoint();
        }
        GameMap.getInstance().removeObjects(objects);
	}

	/**
	 *
	 * @param bonus
	 */
	public void takeBonus(Bonus bonus) {
        player.takeBonus(bonus);
        if (bonus instanceof BombTimerCanceller) {
            player.setBombControllable(true);
        }
        else if (bonus instanceof BombNumberExtender)
            player.increaseBomb();
        else if (bonus instanceof TimerReset) {
         //   time = DEFAULT_TIME;
        }
        else if (bonus instanceof RangeExtender)
            player.increaseRange();
        else if (bonus instanceof RandomBonus)
        {
            Random rand = new Random();
            int rand_num = rand.nextInt(3);
            switch (rand_num){
                case 0: player.setBombControllable(true);
                    break;
                case 1: player.increaseBomb();
                    break;
                case 2: time = DEFAULT_TIME;
                    break;
                case 3: player.increaseRange();
                    break;
            }

        }
        score+=20;
    }

    /**
     *
     * @return uniqueInstance
     */
	public static GameEngine getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new GameEngine();
        }
        return uniqueInstance;
	}

    /**
     *
     * @param tried
     * @return boolean
     */
    private boolean checkTried(boolean[] tried){
        for(boolean bool: tried){
            if(!bool) return false;
        }
        return true;
    }

    /**
     * moves slowMonsters
     * @throws Exception
     */
    private void moveSlowMonsters() throws Exception {
        ArrayList<Monster> monsters = GameMap.getInstance().getMonsters();
        for (Monster monster : monsters){
            if (monster instanceof SlowMonster)
                moveMonster(monster);
        }
    }

    /**
     * moves monsters
     * @param monster
     */
    private void moveMonster(Monster monster){
        int t_x = monster.getX();
        int t_y = monster.getY();
        int direction = ((int)(Math.random() * 4)) % 4;

        boolean[] tried = new boolean[4];
        for (int i = 0; i < 4; i ++){
            tried[i] = false;
        }

        while(!checkPossible(monster, direction) && !checkTried(tried)){
            tried[direction] = true;
            direction = ((int)(Math.random() * 4)) % 4;
        }

        if (checkTried(tried))
            return;

        int x = t_x;
        int y = t_y;

        if (direction == 0)
            x++;
        else if (direction == 1)
            y--;
        else if (direction == 2)
            x--;
        else if (direction == 3)
            y++;

        int collision = colMan.checkCollision(x, y, GameMap.getInstance().getMap());

        if (collision != 1) {
            GameMap.getInstance().removeObject(monster);
            monster.move(direction);
        }

        if (collision == -1){

            GameMap.getInstance().addObject(monster);
            //             healthDecrease();
            return;
        }
        else {
            GameMap.getInstance().addObject(monster);
        }
    }

    /**
     * moves FastMonsters
     * @throws Exception
     */
    private void moveFastMonsters() throws Exception {
        ArrayList<Monster> monsters = GameMap.getInstance().getMonsters();
        for (Monster monster : monsters){
            if (monster instanceof FastMonster) {
                moveMonster(monster);
            }
        }
    }

    /**
     * checks if the map is done for the current level
     * @return boolean
     */
    public boolean isDone(){
        return getMap().isDone();
    }

    /**
     * checks if the object can move on to the given direction
     * @param m
     * @param direction
     * @return boolean
     */
    private boolean checkPossible(Monster m, int direction){
        int x = m.getX();
        int y = m.getY();

        if (direction == 0)
            x++;
        else if (direction == 1)
            y--;
        else if (direction == 2)
            x--;
        else if (direction == 3)
            y++;
        ArrayList<MapObject>[][] t_map = GameMap.getInstance().getMap();
        if (x < 15 && x >= 0 && y >= 0 && y < 13 ) {
            ArrayList<MapObject> slot = GameMap.getInstance().getMap()[y][x];
            if (slot == null)
                return true;
            for (MapObject aSlot : slot) {
                if (aSlot instanceof Wall || aSlot instanceof Bomb)
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     *
     * @return GameMap
     */
    public GameMap getMap()
    {
    	return GameMap.getInstance();
    }

    /**
     *
     * @return boolean
     */
    public boolean isPaused()
	{
		return paused;
	}

    /**
     * used in checking for destroying the bombs with user control
     * @return destroyBombs
     */
    private boolean isDestroyBombs(){
        return this.destroyBombs;
    }

    /**
     * adds fire to the specified location
     * @param x
     * @param y
     */
    public void addFire(int x, int y){
        Fire fire = new Fire(x, y);
        fireTimers.add(fire);
        getMap().addObject(fire);
    }

    /**
     * updates the map according to the given move commands!
     * @throws Exception
     */
    public void update() throws Exception {
        if(!paused ){


            if (a < 10){
                a++;
            }
            else{
                time--;
                if (time < 0){
                    //nextLevel();
                    throw new Exception("gameOver");
                }
                a = 0;
                moveSlowMonsters();
            }

            if (a % 5 == 1){

                moveFastMonsters();
                if (!movements.isEmpty()){
                    for (int i = 0;movements.size()!= 0;){
                        movePlayer(movements.get(i));
                        movements.remove(i);
                    }
                }
                try{
                    GameMap.getInstance().getPlayer().isBombControllable();
                }catch (Exception e){
                    e.printStackTrace();
                }

                if (!GameMap.getInstance().getPlayer().isBombControllable()) {
                    Set<Bomb> keys = bombTimers.keySet();
                    if (keys.size() > 0) {
                        for (Bomb key : keys) {
                            if (bombTimers.get(key) != 0) {
                                bombTimers.put(key, bombTimers.get(key) - 1);
                            } else {
                                bombTimers.remove(key);
                                ArrayList<MapObject> colliding = colMan.checkCollision(key.getRange(), key, GameMap.getInstance().getMap());
                                GameMap.getInstance().removeObject(key);
                                colliding.remove(key);
                                destroyObjects(colliding);
                            }
                        }
                    }
                }
                else if(isDestroyBombs()){
                    Set<Bomb> keys = bombTimers.keySet();
                    for (Bomb key : keys) {
                        bombTimers.remove(key);
                        ArrayList<MapObject> colliding = colMan.checkCollision(key.getRange(), key, GameMap.getInstance().getMap());
                        GameMap.getInstance().removeObject(key);
                        colliding.remove(key);
                        destroyObjects(colliding);
                    }
                    destroyBombs = false;
                }

                ArrayList<MapObject> willBeDestroyed = new ArrayList<MapObject>();
                if (fireTimers == null)
                    fireTimers = new ArrayList<Fire>();
                for (int i = 0; i < fireTimers.size(); i++){

                    fireTimers.get(i).decreaseTime();
                    if (fireTimers.get(i).getDestroyTime() == -1){
                        willBeDestroyed.add(fireTimers.get(i));
                        fireTimers.remove(i--);
                    }
                }
                destroyObjects(willBeDestroyed);
            }

        }
    }
    //Music Methods
 /*   public void playGameMusic()
    {
        souMan.playGameMusic();
    }

    public void stopGameMusic()
    {
        if (souMan != null)
            souMan.stopGameMusic();
    }

	/*public void playWalkEffect()
	{
		souMan.walkEffect();
	}*/

/*    public void playBombEffect()
    {
        souMan.bombEffect();
    }

    public void playDeadEffect()
    {
        souMan.deadEffect();
    }

    public void setSoundEffect(boolean given)
    {
        soundEffect = given;
    }

    public void setMusicEffect(boolean given)
    {
        musicEffect = given;
    }

    public boolean getMusicEffect()
    {
        return musicEffect;
    }*/

}
