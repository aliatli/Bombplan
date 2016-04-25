package ControllerSubsystem;

import ModelSubsystem.*;

import java.util.ArrayList;

public class CollisionManager {

	/**
	 * 
	 * @param range
	 * @param bomb
	 * @param map
	 */
	public ArrayList checkCollision(int range, Bomb bomb, ArrayList<MapObject>[][] map) {
		ArrayList<MapObject> objs = new ArrayList<MapObject>();

        for (int i = bomb.getX() + 1; i <= bomb.getX() + range && i < map.length; i++){
            if (collisionAdd(objs, map, i, bomb.getY()))
                break;
        }
        for (int i = bomb.getY() + 1; i <= bomb.getY() + range && i < map[0].length; i++){
            if (collisionAdd(objs, map, bomb.getX(), i))
                break;
        }
        for (int i = bomb.getX() - 1; i >= bomb.getX() - range && i >= 0; i++){
            if (collisionAdd(objs, map, i, bomb.getY()))
                break;
        }
        for (int i = bomb.getY() - 1; i >= bomb.getX() - range && i >= 0; i++){
            if (collisionAdd(objs, map, bomb.getX(), i))
                break;
        }

        return objs;
	}

    private boolean collisionAdd(ArrayList<MapObject> objs, ArrayList<MapObject>[][] map, int x, int y){
        if (map[x][y] == null)
            return false;

        boolean bonus = false;
        boolean monster = false;
        boolean wall = false;
        boolean player = false;
        int index = 0;

        for (int j = 0; j < map[x][y].size(); j++) {
            if (map[x][y].get(j) instanceof Wall){
                wall = true;
                objs.add(map[x][y].get(j));
            }
            else if (map[x][y].get(j) instanceof Player){
                player = true;
                objs.add(map[x][y].get(j));
            }
            else if (map[x][y].get(j) instanceof Monster){
                monster = true;
                objs.add(map[x][y].get(j));
            }
            else if (map[x][y].get(j) instanceof Bonus){
                bonus = true;
                index = j;
            }
        }
        if (!player && !wall && !monster && bonus){
            objs.add(map[x][y].get(index));
        }
        return wall || bonus || monster || player;
    }

	/**
	 * 
	 * @param map
	 */
	public int checkCollision(int x, int y, ArrayList<MapObject>[][] map) {
        boolean bonus = false;
        boolean monster = false;
        boolean player = false;
        for (int i = 0; i < map[x][y].size(); i++){
            if (map[x][y].get(i) instanceof Wall){
                return 1;
            }
            else if (map[x][y].get(i) instanceof Bonus){
                bonus = true;
            }
            else if (map[x][y].get(i) instanceof  Monster){
                monster = true;
            }
            else if (map[x][y].get(i) instanceof  Monster){
                player = true;
            }
        }

        if (monster && player)
            return -1;

        if (bonus && player && !monster)
            return 2;

        return 0;
	}

}