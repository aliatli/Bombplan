package ModelSubsystem;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

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
		// player, door, bonus, monster
		randomObjectPlanter( level);
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

	private void randomObjectPlanter( int level) {
		Random rand = new Random();
		int randNum;
		int randBonus;
		int randMonster;
		boolean control;
		// player: 1st empty slot
		Player player;
		control = false;
		for( int i = 0; i< map.length; i++){
			for( int j = 0; j < map[i].length; j++){
				if( map[i][j] == null){
					player = new Player( i, j);
					map[i][j].add(player);
					control = true;
					break;
				}
			}
			if( control)
				break;
		}
		// door: behind a random destroyable wall
		randNum = rand.nextInt(72) + 1;
		Door door = null;
		control = false;
		for(int i=0; i< map.length; i++){
			for(int j=0; j< map[i].length; j++){
				if(map[i][j].get(0) instanceof DestroyableWall) {
					randNum--;
					if( randNum == 0) {
						door = new Door( i, j);
						map[i][j].add(door);
						control = true;
						break;
					}
				}
			}
			if( control)
				break;
		}
		if(!control){		// default
			door = new Door( 4,2);
			map[4][2].add(door);
		}
		// bonuses: behind random destroyable wall, not on door
		Bonus bonus;
		// bonus 1: left side
		randBonus = rand.nextInt(5) + 1;
		randNum = rand.nextInt(30) + 1;
		control = false;
		for( int i=0; i<( map.length/2); i++){
			for(int j=0; j < map[i].length; j++){
				if(( map[i][j].get(0) instanceof DestroyableWall) && (map[i][j].get(1) != door)){
					randNum--;
					if( randNum == 0){
						if( randBonus == 1)
							bonus = new RandomBonus( i, j);
						else if( randBonus == 2)
							bonus = new BombNumberExtender( i, j);
						else if( randBonus == 3)
							bonus = new BombTimerCanceller( i, j);
						else if( randBonus == 4)
							bonus = new RangeExtender( i, j);
						else if( randBonus == 5)
							bonus = new TimerReset( i, j);
						else	// default
							bonus = new RandomBonus( i, j);
						map[i][j].add(bonus);
						control = true;
						break;
					}
				}
			}
			if ( control)
				break;
		}
		// bonus 2: right side
		randBonus = rand.nextInt(5) + 1;
		randNum = rand.nextInt(30) + 1;
		control = false;
		for( int i = ( map.length/2); i < map.length; i++){
			for(int j=0; j < map[i].length; j++){
				if(( map[i][j].get(0) instanceof DestroyableWall) && (map[i][j].get(1) != door)){
					randNum--;
					if( randNum == 0){
						if( randBonus == 1)
							bonus = new RandomBonus( i, j);
						else if( randBonus == 2)
							bonus = new BombNumberExtender( i, j);
						else if( randBonus == 3)
							bonus = new BombTimerCanceller( i, j);
						else if( randBonus == 4)
							bonus = new RangeExtender( i, j);
						else if( randBonus == 5)
							bonus = new TimerReset( i, j);
						else	// default
							bonus = new RandomBonus( i, j);
						map[i][j].add(bonus);
						control = true;
						break;
					}
				}
			}
			if ( control)
				break;
		}
		// monsters: random empty slot, not on player
		Monster monster;
		randMonster = rand.nextInt(5);
		// monster 1: left top corner
		randNum = rand.nextInt(7) + 1;
		control = false;
		for( int i = 0; i < ( map.length / 2); i++){
			for( int j = 0; j < ( map[i].length / 2); j++){
				if( map[i][j].get(0) == null){
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( i, j);
						else if( level == 3)
							monster = new FastMonster( i, j);
						else{
							if( randMonster == 0 || randMonster == 1 || randMonster == 4)
								monster = new SlowMonster( i, j);
							else
								monster = new FastMonster( i, j);
						}
						map[i][j].add(monster);
						control = true;
						break;
					}
				}
			}
			if( control)
				break;
		}
		// monster 2: left bottom corner
		randNum = rand.nextInt(7) + 1;
		control = false;
		for( int i = 0; i < ( map.length / 2); i++){
			for( int j = ( map[i].length / 2); j < map[i].length; j++){
				if( map[i][j].get(0) == null){
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( i, j);
						else if( level == 3)
							monster = new FastMonster( i, j);
						else{
							if( randMonster == 1 || randMonster == 2 || randMonster == 5)
								monster = new SlowMonster( i, j);
							else
								monster = new FastMonster( i, j);
						}
						map[i][j].add(monster);
						control = true;
						break;
					}
				}
			}
			if( control)
				break;
		}
		// monster 3: right top corner
		randNum = rand.nextInt(7) + 1;
		control = false;
		for( int i = ( map.length / 2); i < map.length; i++){
			for( int j = 0; j < ( map[i].length / 2); j++){
				if( map[i][j].get(0) == null){
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( i, j);
						else if( level == 3)
							monster = new FastMonster( i, j);
						else{
							if( randMonster == 0 || randMonster == 3 || randMonster == 5)
								monster = new SlowMonster( i, j);
							else
								monster = new FastMonster( i, j);
						}
						map[i][j].add(monster);
						control = true;
						break;
					}
				}
			}
			if( control)
				break;
		}
		// monster 4: right bottom corner
		randNum = rand.nextInt(7) + 1;
		control = false;
		for( int i = ( map.length / 2); i < map.length; i++){
			for( int j = ( map[i].length / 2); j < map[i].length; j++){
				if( map[i][j].get(0) == null){
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( i, j);
						else if( level == 3)
							monster = new FastMonster( i, j);
						else{
							if( randMonster == 2 || randMonster == 3 || randMonster == 4)
								monster = new SlowMonster( i, j);
							else
								monster = new FastMonster( i, j);
						}
						map[i][j].add(monster);
						control = true;
						break;
					}
				}
			}
			if( control)
				break;
		}
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
	
	public void drawAll(Graphics g)
	{
		for(int i = 1; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				if( map[i][j] != null )
				{
					((map[i][j]).get(0)).draw(g);
				}
			}
		}
				
	}
}