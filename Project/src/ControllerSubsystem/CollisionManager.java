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

        objs.add(bomb);

        for (int i = bomb.getX() + 1; i <= bomb.getX() + range && i < 15; i++){
            if (collisionAdd(objs, map, i, bomb.getY()))
                break;
        }
        for (int i = bomb.getY() + 1; i <= bomb.getY() + range && i < 13; i++){
            if (collisionAdd(objs, map, bomb.getX(), i))
                break;
        }
        for (int i = bomb.getX() - 1; i >= bomb.getX() - range && i >= 0; i--){
            if (collisionAdd(objs, map, i, bomb.getY()))
                break;
        }
        for (int i = bomb.getY() - 1; i >= bomb.getY() - range && i >= 0; i--){
            if (collisionAdd(objs, map, bomb.getX(), i))
                break;
        }

        return objs;
	}

    private boolean collisionAdd(ArrayList<MapObject> objs, ArrayList<MapObject>[][] map, int x, int y){
        if (map[y][x] == null)
            return false;

        for (int j = 0; j < map[y][x].size(); j++) {
            if (map[y][x].get(j) instanceof DestroyableWall){
                objs.add(map[y][x].get(j));
                return true;
            }
            else if (map[y][x].get(j) instanceof Bonus){
                objs.add(map[y][x].get(j));
                return true;
            }
            else if (map[y][x].get(j) instanceof Monster){
                objs.add(map[y][x].get(j));
                return true;
            }
            else if (map[y][x].get(j) instanceof Player){
                objs.add(map[y][x].get(j));
                return true;
            }
        }
        return true;
    }

	/**
	 * 
	 * @param map
	 */
	public int checkCollision(int x, int y, ArrayList<MapObject>[][] map) {
        boolean bonus = false;
        boolean monster = false;
        boolean player = false;
        if (map[y][x] != null) {
            for (int i = 0; i < map[y][x].size(); i++) {
                if (map[y][x].get(i) instanceof Wall || map[y][x].get(i) instanceof Bomb) {
                    return 1;
                } else if (map[y][x].get(i) instanceof Bonus) {
                    bonus = true;
                } else if (map[y][x].get(i) instanceof Monster) {
                    monster = true;
                } else if (map[y][x].get(i) instanceof Player) {
                    player = true;
                } else if (map[y][x].get(i) instanceof  Door && GameEngine.getInstance().isDone()){
                    return 3;
                }
            }

            if (monster || player)
                return -1;

            if (bonus)
                return 2;
        }
        return 0;
	}

}