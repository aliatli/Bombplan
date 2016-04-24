package ModelSubsystem;

import java.util.ArrayList;

public class GameMap {

	private MapObject[][] map;
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

	public MapObject[][] getMap(){
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
				if (map[i][j] instanceof Player){
					return (Player) map[i][j];
				}
			}
		}
		return null;
	}

	public ArrayList<Monster> getMonsters(){
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				if (map[i][j] instanceof Monster){
					monsters.add((Monster) map[i][j]);
				}
			}
		}
		return monsters;
	}

	public MapObject getObj(int x, int y){
		return map[x][y];
	}

	public void setObject(MapObject obj){
		map[obj.getX()][obj.getY()] = obj;
	}

	public void setObject(MapObject obj, int x, int y){
			map[x][y] = obj;
	}
}