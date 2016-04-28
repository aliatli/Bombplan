package ControllerSubsystem;

import ModelSubsystem.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

public class GameEngine {
    private final int BOMB_TIME = 5;
    private final int DEFAULT_TIME = 120;

    private int score;
    private int currentLevel;
    private int time;
    private boolean paused;
    private boolean destroyBombs;
    private HashMap<Bomb, Integer> bombTimers;
    private ArrayList<Integer> movements;
    private CollisionManager colMan;
    private SoundManager souMan;
    private static GameEngine uniqueInstance;
    private StorageManager storageMan;
    GameMap map;


    private GameEngine(){
        paused = true;
        currentLevel = 1;
        score = 0;
        time = DEFAULT_TIME;
        movements = new ArrayList<Integer>();
        destroyBombs = false;
        storageMan = new StorageManager();
        colMan = new CollisionManager();
        map = GameMap.getInstance();
        bombTimers = new HashMap<Bomb, Integer>();
        try {
            map.constructLevel(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setPaused(boolean setVal){
        paused = setVal;
    }
	public GameEngine createGame() {
		if(uniqueInstance == null){
			uniqueInstance = new GameEngine();
            return uniqueInstance;
		}
        else{
            paused = false;
            return uniqueInstance;
        }
	}

    public StorageManager getStorageMan(){
        return storageMan;
    }


	private void movePlayer(int movement) throws Exception {
        Player player = map.getPlayer();
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
            plantBomb();
            return;
        }


        int collision = colMan.checkCollision(x, y, map.getMap());

        map.removeObject(player);
        if (collision != 1) {
            player.move(movement);
        }

        if (collision == 2) {
            ArrayList<MapObject> slot = map.getObj(player.getY(), player.getX());
            for (int i = 0; i < slot.size(); i++){
                if (slot.get(i) instanceof Bonus) {
                    takeBonus((Bonus) slot.get(i));
                    score+= ((Bonus)slot.get(i)).getPoint();
                }
            }
        }
        else if (collision == -1){
            healthDecrease();
        }
        map.addObject(player);

    }

	public void startGameLoop() {
        if (paused)
            paused = false;
	}

    private void stopGame() {
        if (!paused)
            paused = true;
    }
    
	private void plantBomb() {
		Player player = map.getPlayer();
        Bomb bomb = new Bomb(player.getX(), player.getY(), player.getRange());

        map.addObject(bomb);
        if (!player.isBombControllable())
            bombTimers.put(bomb, BOMB_TIME);

	}

    public ArrayList<Integer> getMovements(){
        return movements;
    }
	public void options() {
        //TODO - what should this method do AMK?
	}

	private void nextLevel() {
		this.stopGame();
  //      map.constructLevel(++currentLevel);
        this.startGameLoop();
	}

    private void healthDecrease() throws Exception {
        map.getPlayer().decreaseLife();
        if (map.getPlayer().getLife() == -1){
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
	private void destroyObjects(ArrayList<MapObject> objects) {
		map.removeObjects(objects);
	}

	/**
	 * 
	 * @param bonus
	 */
	public void takeBonus(Bonus bonus) {
        map.getPlayer().takeBonus(bonus);
        if (bonus instanceof BombTimerCanceller)
            setDestroyBombs();
        else if (bonus instanceof BombNumberExtender)
            map.getPlayer().increaseBomb();
        else if (bonus instanceof TimerReset)
            time = DEFAULT_TIME;
        else if (bonus instanceof RangeExtender)
            map.getPlayer().increaseRange();
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

    private void moveMonsters() throws Exception {
        ArrayList<Monster> monsters = map.getMonsters();
        for (Monster monster : monsters){
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
                continue;

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

            int collision = colMan.checkCollision(x, y, map.getMap());

            if (collision != 1) {
                map.removeObject(monster);
                monster.move(direction);
            }

            if (collision == -1){

                map.addObject(monster);
   //             healthDecrease();
                break;
            }
            else {
                map.addObject(monster);
            }
        }
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
        ArrayList<MapObject>[][] t_map = map.getMap();
        if (x < 15 && x >= 0 && y >= 0 && y < 13 ) {
            ArrayList<MapObject> slot = map.getMap()[y][x];
            if (slot == null)
                return true;
            for (MapObject aSlot : slot) {
                if (aSlot instanceof Wall)
                    return false;
            }
            return true;
        }
        return false;
    }

    public void setDestroyBombs(){
        if (map.getPlayer().isBombControllable())
            destroyBombs = true;
    }
    
    public GameMap getMap()
    {
    	return map;
    }
	
	public boolean isPaused()
	{
		return paused;
	}

    static int a = 500000;

    public void update() throws Exception {
        if(!paused ){

            if (a % 50 == 0) {
                a--;
                time--;
            }
            if (!movements.isEmpty()){
                for (int i = 0;movements.size()!= 0;){
                    movePlayer(movements.get(i));
                    movements.remove(i);
                }
            }
            if (time % 2 == 1)
                moveMonsters();
            try{
                map.getPlayer().isBombControllable();
            }catch (Exception e){
                e.printStackTrace();
            }

            if (!map.getPlayer().isBombControllable()) {
                Set<Bomb> keys = bombTimers.keySet();
                for (Bomb key : keys) {
                    if (bombTimers.get(key) != 0) {
                        bombTimers.put(key, bombTimers.get(key) - 1);
                    } else {
                        bombTimers.remove(key);
                        key.destroy();
                        ArrayList colliding = colMan.checkCollision(key.getRange(), key, map.getMap());
                        destroyObjects(colliding);
                    }
                }
            }
            else if (destroyBombs){
                Set<Bomb> keys = bombTimers.keySet();
                for (Bomb key : keys) {
                    bombTimers.remove(key);
                    key.destroy();
                    ArrayList colliding = colMan.checkCollision(key.getRange(), key, map.getMap());
                    destroyObjects(colliding);
                }
                destroyBombs = false;
            }

            score += 5;
        }
    }





}
