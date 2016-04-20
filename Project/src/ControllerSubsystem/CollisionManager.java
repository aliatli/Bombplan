package ControllerSubsystem;

import ModelSubsystem.Bomb;
import ModelSubsystem.Bonus;
import ModelSubsystem.Door;
import ModelSubsystem.MapObject;

import java.util.ArrayList;

public class CollisionManager {

	/**
	 * 
	 * @param range
	 * @param bomb
	 * @param map
	 */
	public ArrayList checkCollision(int range, Bomb bomb, MapObject[][] map) {
		ArrayList<MapObject> objs = new ArrayList<MapObject>();

        int x1 = bomb.getX() - range;
        int x2 = bomb.getX() + range;

        int y1 = bomb.getY() - range;
        int y2 = bomb.getY() + range;

        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if (!(map[i][j] instanceof Door) && !(map[i][j] instanceof Bonus &&
                        (map[i][j].getX() > x1 && map[i][j].getX() < x2) &&
                        (map[i][j].getX() > y1 && map[i][j].getX() < y2))){
                    objs.add(map[i][j]);
                }
            }
        }

        return objs;
	}

	/**
	 * 
	 * @param map
	 */
	public int checkCollision(int x, int y, MapObject[][] map) {
		int count = 0;
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				if (!(map[i][j] instanceof Door) && !(map[i][j] instanceof Bonus)
						&& (map[i][j].getX() == x) && (map[i][j].getY() == y)){
					count++;
				}
                if (map[i][j] instanceof Bonus && ((Bonus)map[i][j]).isActive()){
                    return 2;
                }
			}
		}

        if (count >= 2)
		    return 1;
        return 0;
	}

}