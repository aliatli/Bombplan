package ControllerSubsystem;

import ModelSubsystem.*;
import UserInterfaceSubsystem.GameScreenPanel;
import UserInterfaceSubsystem.LoadGamePanel;
import UserInterfaceSubsystem.ScreenView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.PaintEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

public class GameEngine {
    private final int BOMB_TIME = 5;
    private final int DEFAULT_TIME = 360;

    private int score;
    private int currentLevel;
    private int time;
    private boolean paused;
    private boolean destroyBombs;
    private HashMap<Bomb, Integer> bombTimers;
    private ArrayList<Fire> fireTimers;
    private ArrayList<Integer> movements;
    private CollisionManager colMan;
    private SoundManager souMan;
    private static GameEngine uniqueInstance;
    private StorageManager storageMan;
    private Player player;
    private boolean soundEffect;
    private boolean musicEffect;


    private GameEngine(){
        paused = true;
        currentLevel = 1;
        score = 0;
        time = DEFAULT_TIME;
        movements = new ArrayList<Integer>();
        destroyBombs = false;
        storageMan = new StorageManager();
        colMan = new CollisionManager();
        souMan = new SoundManager();

        bombTimers = new HashMap<Bomb, Integer>();
        fireTimers = new ArrayList<Fire>();
        try {
            GameMap.getInstance().constructLevel(currentLevel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }





    public static void setUniqueInstance(GameEngine engine){
        uniqueInstance = engine;
    }
    public int getScore(){
        return this.score;
    }

    public int getLevel(){
        return this.currentLevel;
    }

    public int getTime(){
        return this.time;
    }
    public void restart(){

        uniqueInstance = new GameEngine();
    }

    public void setPaused(boolean setVal){
        paused = setVal;
    }

    public StorageManager getStorageMan(){
        return storageMan;
    }


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
            nextLevel();
            return;
        }
        else if (collision == -1){
            healthDecrease();
        }
        GameMap.getInstance().addObject(player);

    }

	public void startGameLoop() {
        if (paused)
            paused = false;
        if(musicEffect)
            playGameMusic();
        else
            stopGameMusic();
	}

    private void stopGame() {
        if (!paused)
            paused = true;
    }
    
	private void plantBomb() {
		Player player = GameMap.getInstance().getPlayer();
        Bomb bomb = new Bomb(player.getX(), player.getY(), player.getRange());

        GameMap.getInstance().addObject(bomb);
        bombTimers.put(bomb, BOMB_TIME);

	}

    public ArrayList<Integer> getMovements(){
        return movements;
    }
	public void options() {
        //TODO - what should this method do AMK?
	}

	public void nextLevel() {
        if (currentLevel != 4) {
    /*        ScreenView.getInstance().changeActivePanel( (ScreenView.getInstance()).getPassing(currentLevel+1));
            try {
                GameScreenPanel.repaint();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ScreenView.getInstance().changeActivePanel( (ScreenView.getInstance()).getGame());
      */      paused = true;
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
	}

    private void healthDecrease() throws Exception {
        player.decreaseLife();

        if (player.getLife() == -1){
            gameOver();
        }
    }

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
            }
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


	public static GameEngine getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new GameEngine();
        }
        return uniqueInstance;
	}

    private boolean checkTried(boolean[] tried){
        for(boolean bool: tried){
            if(!bool) return false;
        }
        return true;
    }

    private void moveSlowMonsters() throws Exception {
        ArrayList<Monster> monsters = GameMap.getInstance().getMonsters();
        for (Monster monster : monsters){
            if (monster instanceof SlowMonster)
                moveMonster(monster);
        }
    }

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

    private void moveFastMonsters() throws Exception {
        ArrayList<Monster> monsters = GameMap.getInstance().getMonsters();
        for (Monster monster : monsters){
            if (monster instanceof FastMonster) {
                moveMonster(monster);
            }
        }
    }

    public boolean isDone(){
        return getMap().isDone();
    }

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


    
    public GameMap getMap()
    {
    	return GameMap.getInstance();
    }
	
	public boolean isPaused()
	{
		return paused;
	}

    int a = 0;

    private boolean isDestroyBombs(){
        return this.destroyBombs;
    }

    public void addFire(int x, int y){
        Fire fire = new Fire(x, y);
        fireTimers.add(fire);
        getMap().addObject(fire);
    }

    public void update() throws Exception {
        if(!paused ){


            if (a < 10){
                a++;
            }
            else{
                time--;
                if (time < 0){
                    //nextLevel();
                    throw new Exception("nextLevel");
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
                                key.destroy();
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
                        key.destroy();
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
    public void playGameMusic()
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

    public void playBombEffect()
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
    }

}
