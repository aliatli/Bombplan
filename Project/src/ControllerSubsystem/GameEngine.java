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
        paused = false;
        currentLevel = 1;
        score = 0;
        time = DEFAULT_TIME;
        movements = new ArrayList<Integer>();
        destroyBombs = false;
        storageMan = new StorageManager();
        map = GameMap.getInstance();
        try {
            map.constructLevel(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
	public GameEngine createGame() {
		if(uniqueInstance == null){
			uniqueInstance = new GameEngine();
            return uniqueInstance;
		}
        else{
            return uniqueInstance;
        }
	}

    public StorageManager getStorageMan(){
        return storageMan;
    }


	public void movePlayer(int movement) throws Exception {
        Player player = map.getPlayer();
        int t_x = player.getX();
        int t_y = player.getY();
        int x = t_x;
        int y = t_y;

        if (movement == 0)
            x++;
        else if (movement == 1)
            y++;
        else if (movement == 2)
            x--;
        else if (movement == 3)
            y--;

        int collision = colMan.checkCollision(x, y, map.getMap());

        if (collision != 1) {
            player.move(movement);
        }
        map.setObject(null, t_x, t_y);

        if (collision == 2) {
            ArrayList<MapObject> slot = map.getObj(player.getX(), player.getY());
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
        map.setObject(player);

    }

	public void startGameLoop() {
        if (paused)
            paused = false;
	}

    public void stopGame() {
        if (!paused)
            paused = true;
    }
    
	public void plantBomb() {
		Player player = map.getPlayer();
        Bomb bomb = new Bomb(player.getX(), player.getY(), player.getRange());

        map.addObject(bomb);
        if (!player.isBombControllable())
            bombTimers.put(bomb, BOMB_TIME);

	}

	public void changeSound() {
        souMan.changeSound();
	}

	public void options() {
        //TODO - what should this method do AMK?
	}

	public void nextLevel() {
		this.stopGame();
  //      map.constructLevel(++currentLevel);
        this.startGameLoop();
	}

    public void healthDecrease() throws Exception {
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
	public void destroyObjects(ArrayList<MapObject> objects) {
		map.removeObjects((MapObject[]) objects.toArray());
	}

	/**
	 * 
	 * @param bonus
	 */
	public void takeBonus(Bonus bonus) {
        map.getPlayer().takeBonus(bonus);
	}


	public static GameEngine getInstance() {
        if (uniqueInstance == null){
            uniqueInstance = new GameEngine();
        }
        return uniqueInstance;
	}

    public void moveMonsters() throws Exception {
        ArrayList<Monster> monsters = map.getMonsters();
        for (Monster monster : monsters){
            int t_x = monster.getX();
            int t_y = monster.getY();
            int direction = ((int)(Math.random() * 4)) % 4;

            while(checkPossible(monster, direction)){
                direction = ((int)(Math.random() * 4)) % 4;
            }


            int x = t_x;
            int y = t_y;

            if (direction == 0)
                x++;
            else if (direction == 1)
                y++;
            else if (direction == 2)
                x--;
            else if (direction == 3)
                y--;

            int collision = colMan.checkCollision(x, y, map.getMap());

            if (collision != 1) {
                monster.move(direction);
                map.setObject(null, t_x, t_y);
            }

            if (collision == -1){
                map.setObject(monster);
                healthDecrease();
                break;
            }
            else {
                map.setObject(monster);
            }
        }
    }

    private boolean checkPossible(Monster m, int direction){
        int x = m.getX();
        int y = m.getY();

        if (direction == 0)
            x++;
        else if (direction == 1)
            y++;
        else if (direction == 2)
            x--;
        else if (direction == 3)
            y--;

        ArrayList<MapObject> slot = map.getMap()[x][y];
        if (slot == null)
            return true;
        for (MapObject aSlot : slot) {
            if (aSlot instanceof Wall)
                return false;
        }
        return true;
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

    public void update() throws Exception {
        if(!paused){
            time++;
            if (!movements.isEmpty()){
                for (int i = 0;movements.size()!= 0;){
                    movePlayer(movements.get(i));
                    movements.remove(i);
                }
            }
            if (time % 2 == 1)
                moveMonsters();

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
