package ModelSubsystem;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
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
			scanner = new Scanner(new File("src/sources/txts/level1.txt"));
		else if(level == 2)
			scanner = new Scanner(new File("src/sources/txts/level2.txt"));
		else if(level == 3)
			scanner = new Scanner(new File("src/sources/txts/level3.txt"));
		else
			scanner = new Scanner(new File("src/sources/txts/level1.txt"));	// default
		// read file
		for( int i = 0; i<map.length; i++){
			for( int j = 0; j<map[i].length; j++){
				map[i][j] = new ArrayList<MapObject>();

				if( scanner.hasNextInt()){
					number = scanner.nextInt();
					if( number == 1){
						wall = new DestroyableWall( j, i);
						map[i][j].add(wall);
					}
					else if( number == 2){
						wall = new NondestroyableWall( j, i);
						map[i][j].add(wall);
					}
					else
						map[i][j]= null;
				}
				else
					map[i][j]=null;
			}
		}
		// player, door, bonus, monster
		randomObjectPlanter( level);
	}

	public boolean addObject(MapObject object) {
		if (map[object.getY()][object.getX()] == null){
			map[object.getY()][object.getX()] = new ArrayList<MapObject>();
		}
		map[object.getY()][object.getX()].add(object);
		for(int i= 0; i < map[object.getY()][object.getX()].size(); i++)
			if( map[object.getY()][object.getX()].get(i) == object)
				return true;
		map[object.getY()][object.getX()] = null;
		return false;
	}

	public void removeObject(MapObject object) {
		map[object.getY()][object.getX()].remove(object);
		if (map[object.getY()][object.getX()].size() == 0)
			map[object.getY()][object.getX()] = null;
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
					map[i][j] = new ArrayList<MapObject>();
					player = new Player( j, i);
					map[i][j].add(player);
					control = true;
					break;
				}
			}
			if( control)
				break;
		}
		// door: behind a random destroyable wall
		// TODO: YA NERDEN BILIYON AMK HEP 72 DUVAR OLACAGINI??
		randNum = rand.nextInt(72) + 1;
		Door door = null;
		control = false;
		for(int i=0; i< map.length; i++){
			for(int j=0; j< map[i].length; j++){
				if(map[i][j] != null && map[i][j].get(0) instanceof DestroyableWall) {
					randNum--;
					if( randNum == 0) {
						door = new Door( j, i);
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
			if (map[4][2] == null)
				map[4][2] = new ArrayList<MapObject>();
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
				if((map[i][j] != null &&  map[i][j].size() == 1 && map[i][j].get(0) instanceof DestroyableWall) ){

					randNum--;
					if( randNum == 0){
						if( randBonus == 1)
							bonus = new RandomBonus( j, i);
						else if( randBonus == 2)
							bonus = new BombNumberExtender( j, i);
						else if( randBonus == 3)
							bonus = new BombTimerCanceller( j, i);
						else if( randBonus == 4)
							bonus = new RangeExtender( j, i);
						else if( randBonus == 5)
							bonus = new TimerReset( j, i);
						else	// default
							bonus = new RandomBonus( j, i);
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
				if((map[i][j] != null &&  map[i][j].size() == 1 && map[i][j].get(0) instanceof DestroyableWall)){
					randNum--;
					if( randNum == 0){
						if( randBonus == 1)
							bonus = new RandomBonus( j, i);
						else if( randBonus == 2)
							bonus = new BombNumberExtender( j, i);
						else if( randBonus == 3)
							bonus = new BombTimerCanceller( j, i);
						else if( randBonus == 4)
							bonus = new RangeExtender( j, i);
						else if( randBonus == 5)
							bonus = new TimerReset( j, i);
						else	// default
							bonus = new RandomBonus( j, i);
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
				if(map[i][j] == null){
					map[i][j] = new ArrayList<MapObject>();
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( j, i);
						else if( level == 3)
							monster = new FastMonster( j, i);
						else{
							if( randMonster == 0 || randMonster == 1 || randMonster == 4)
								monster = new SlowMonster( j, i);
							else
								monster = new FastMonster( j, i);
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
				if( map[i][j] == null ){
					map[i][j] = new ArrayList<MapObject>();
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( j, i);
						else if( level == 3)
							monster = new FastMonster( j, i);
						else{
							if( randMonster == 1 || randMonster == 2 || randMonster == 5)
								monster = new SlowMonster( j, i);
							else
								monster = new FastMonster( j, i);
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
				if( map[i][j] == null){
					map[i][j] = new ArrayList<MapObject>();
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( j, i);
						else if( level == 3)
							monster = new FastMonster( j, i);
						else{
							if( randMonster == 0 || randMonster == 3 || randMonster == 5)
								monster = new SlowMonster( j, i);
							else
								monster = new FastMonster( j, i);
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
				if( map[i][j] == null){
					map[i][j] = new ArrayList<MapObject>();
					randNum--;
					if( randNum == 0){
						if( level == 1)
							monster = new SlowMonster( j, i);
						else if( level == 3)
							monster = new FastMonster( j, i);
						else{
							if( randMonster == 2 || randMonster == 3 || randMonster == 4)
								monster = new SlowMonster( j, i);
							else
								monster = new FastMonster( j, i);
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
		map = new ArrayList[13][15];

		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				map[i][j] = null;
			}
		}
	}

	public ArrayList<MapObject>[][] getMap(){
		return map;
	}

	public void removeObjects(ArrayList<MapObject> mapObjects) {
		for( int i=0; i < mapObjects.size(); i++)
			removeObject(mapObjects.get(i));
	}

	public Player getPlayer(){
		for (int i = 1; i < map.length; i++){
			for (int j = 1; j < map[i].length; j++){
				if (map[i][j] != null)
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
				if (map[i][j] != null)
					for (int k = 0; k < map[i][j].size(); k++)
						if (map[i][j].get(k) instanceof Monster){
							monsters.add((Monster) map[i][j].get(k));
						}
			}
		}
		return monsters;
	}

	public ArrayList<MapObject> getObj(int x, int y){
		return map[y][x];
	}

	public void setObject(MapObject obj){
		int x = obj.getX();
		int y = obj.getY();
		try{
			ArrayList<MapObject> o = map[y][x];
		}catch(Exception e){
			e.printStackTrace();
		}
		if (map[y][x] == null){
			map[y][x] = new ArrayList<MapObject>();
		}
		map[obj.getY()][obj.getX()].add(obj);
	}

	public void setObject(MapObject obj, int x, int y){
		if (obj == null)
			map[y][x] = null;
		else if (map[y][x] == null) {
			map[y][x] = new ArrayList<MapObject>();
			map[y][x].add(obj);
		}
		else{
			map[y][x].add(obj);
		}
	}
	
	public void drawAll(Graphics g)
	{
		for(int i = 0; i < 13; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				if( map[i][j] != null && map[i][j].size() != 0)
				{
					((map[i][j]).get(0)).draw(g);
				}
			}
		}
				
	}
}