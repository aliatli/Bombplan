package ModelSubsystem;

import java.util.ArrayList;

public class GameMap {

	private ArrayList<MapObject>[][] map;
	private int remainingTime;
	private GameMap uniqueInstance;

	/**
	 * 
	 * @param level
	 */
	public void constructLevel(int level) {
		// TODO - implement GameMap.constructLevel
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param object
	 */
	public boolean addObject(MapObject object) {
		// TODO - implement GameMap.addObject
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param object
	 */
	public void removeObject(MapObject object) {
		// TODO - implement GameMap.removeObject
		throw new UnsupportedOperationException();
	}

	public void randomObjectPlanter() {
		// TODO - implement GameMap.randomObjectPlanter
		throw new UnsupportedOperationException();
	}

	public void resetTimer() {
		// TODO - implement GameMap.resetTimer
		throw new UnsupportedOperationException();
	}

	public void startTimer() {
		// TODO - implement GameMap.startTimer
		throw new UnsupportedOperationException();
	}

	public void explodeBomb() {
		// TODO - implement GameMap.explodeBomb
		throw new UnsupportedOperationException();
	}


	public GameMap getInstance() {
		// TODO - implement GameMap.getInstance
		throw new UnsupportedOperationException();
	}

	public ArrayList<MapObject>[][] getMap(){
		return map;
	}
	/**
	 * 
	 * @param mapObjects
	 */
	public void removeObjects(MapObject[] mapObjects) {
		// TODO - implement GameMap.removeObjects
		throw new UnsupportedOperationException();
	}

	public Player getPlayer(){
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				for (int k = 0; k < map[i][j].size(); k++)
					if (map[i][j].get(k) instanceof Player){
						return (Player) map[i][j].get(k);
					}
			}
		}
		return null;
	}

	public ArrayList<Monster> getMonsters(){
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				for (int k = 0; k < map[i][j].size(); k++)
					if (map[i][j].get(k) instanceof Monster){
						monsters.add((Monster) map[i][j].get(k));
					}
			}
		}
		return monsters;
	}

	public ArrayList<MapObject> getObj(int x, int y){
		return map[x][y];
	}

	public void setObject(MapObject obj){
		int x = obj.getX();
		int y = obj.getY();
		if (map[x][y] == null){
			map[x][y] = new ArrayList<MapObject>();
		}
		map[obj.getX()][obj.getY()].add(obj);
	}

	public void setObject(MapObject obj, int x, int y){
		if (obj == null)
			map[x][y] = null;
		else if (map[x][y] == null)
			map[x][y] = new ArrayList<MapObject>();
			map[x][y].add(obj);
		else{
			map[x][y].add(obj);
		}
	}
}