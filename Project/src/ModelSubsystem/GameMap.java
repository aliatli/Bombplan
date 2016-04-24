package ModelSubsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap {

	private ArrayList<MapObject>[][] map;
	private static GameMap uniqueInstance = null;

	public void constructLevel(int level) throws FileNotFoundException {
		Scanner scanner;
		int number;
		Wall wall;
		// get file
		if( level == 1)
			scanner = new Scanner(new File("level1.txt"));
		else if(level == 2)
			scanner = new Scanner(new File("level2.txt"));
		else if(level == 3)
			scanner = new Scanner(new File("level3.txt"));
		else
			scanner = new Scanner(new File("level1.txt"));	// default
		// read file
		for( int i = 0; i<13; i++){
			for( int j = 0; j<15; j++){
				if( scanner.hasNextInt()){
					number = scanner.nextInt();
					if( number == 1){
						wall = new DestroyableWall( i, j);
						map[i][j].add(wall);
					}
					else if( number == 2){
						wall = new NondestroyableWall( i, j);
						map[i][j].add(wall);
					}
					else
						map[i][j].add(null);
				}
				else
					map[i][j].add(null);
			}
		}
	}

	public boolean addObject(MapObject object) {
		map[object.getX()][object.getY()].add(object);
		for(int i= 0; i < map[object.getX()][object.getY()].size(); i++)
			if( map[object.getX()][object.getY()].get(i) == object)
				return true;
		return false;
	}

	public void removeObject(MapObject object) {
		map[object.getX()][object.getY()].remove(object);
	}

	public void randomObjectPlanter() {
		// TODO - implement GameMap.randomObjectPlanter
		throw new UnsupportedOperationException();
	}

	public void explodeBomb(Bomb bomb) {
		removeObject( bomb);
		bomb.destroy();
	}

	public static GameMap getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new GameMap();
		}
		return uniqueInstance;
	}

	private GameMap(){
		map = null;
	}

	public ArrayList<MapObject>[][] getMap(){
		return map;
	}

	public void removeObjects(MapObject[] mapObjects) {
		for( int i=0; i < mapObjects.length; i++)
			removeObject(mapObjects[i]);
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
		else if (map[x][y] == null) {
			map[x][y] = new ArrayList<MapObject>();
			map[x][y].add(obj);
		}
		else{
			map[x][y].add(obj);
		}
	}
}