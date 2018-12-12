package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	/**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
     * từ ma trận bản đồ trong tệp cấu hình
     */
	private static char[][] _map;
	
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level){
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
        // TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		try {  
			int n = 0;
			String path = "res/levels/Level" + level + ".txt"; 
			Scanner sc = new Scanner(new File(path));
			
			
			_level = 1;//sc.nextInt();
			
			_height = 13;//sc.nextInt();
			
			_width = 31;//scanner.nextInt();
 
			
			
			System.out.println(_level); 
			
			
			
			_map = new char[_height][_width];
			sc.nextLine();
            for(int i=0;i<_height;i++) {
            		_map[i] = sc.nextLine().toCharArray();
            }
            
           	sc.close();
       	 }
		catch (IOException n) {
        		System.out.println(n);
        	}

	}

	@Override
	public void createEntities() {
//		// TODO: táº¡o cÃ¡c Entity cá»§a mÃ n chÆ¡i
//		// TODO: sau khi táº¡o xong, gá»�i _board.addEntity() Ä‘á»ƒ thÃªm Entity vÃ o game
//
//		// TODO: pháº§n code máº«u á»Ÿ dÆ°á»›i Ä‘á»ƒ hÆ°á»›ng dáº«n cÃ¡ch thÃªm cÃ¡c loáº¡i Entity vÃ o game
//		// TODO: hÃ£y xÃ³a nÃ³ khi hoÃ n thÃ nh chá»©c nÄƒng load mÃ n chÆ¡i tá»« tá»‡p cáº¥u hÃ¬nh
//		// thÃªm Wall
//		for (int x = 0; x < 31; x++) {
//			for (int y = 0; y < 13; y++) {
//				int pos = x + y * _width;
//				Sprite sprite = _map[y][x] == '#' ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, Sprite.grass));
//			}
//		}
		
		for(int x=0;x<_width;x++) {
			for(int y=0;y<_height;y++) {
				switch(_map[y][x]) {
					case '#': {
						//thêm wall
						_board.addEntity(x + y * _width, new Wall(x, y, Sprite.wall));
						break;
					}
					
					case '*': {
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
									new Grass(x, y, Sprite.grass),
									new Brick(x, y, Sprite.brick)
								)
						);
						break;
					}
					
					case 'x': {
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
									new Grass(x, y, Sprite.grass),
									new Portal(x, y, Sprite.portal),
									new Brick(x, y, Sprite.brick)
								)
						);
						break;
					}
					
					case 'p': {
						//thêm bomber
						_board.addCharacter( new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
						Screen.setOffset(0, 0);
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					}
					
					case '1': {
						_board.addCharacter( new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					}
					
					case '2': {
						_board.addCharacter( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					}
					
					case 'b': {
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x ,y, Sprite.grass),
										new SpeedItem(x, y, Sprite.powerup_bombs),
										new Brick(x, y, Sprite.brick)
										)
						);
						break;
					}
					
					case 'f': {
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x ,y, Sprite.grass),
										new SpeedItem(x, y, Sprite.powerup_flames),
										new Brick(x, y, Sprite.brick)
										)
						);
						break;
					}
					
					case 's': {
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x ,y, Sprite.grass),
										new SpeedItem(x, y, Sprite.powerup_speed),
										new Brick(x, y, Sprite.brick)
										)
						);
						break;
					}
					
					default: {
						//thêm grass
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					}
						
				}
			}
		}
		// thÃªm Bomber
//		int xBomber = 1, yBomber = 1;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));

		// thÃªm Enemy
//		int xE = 2, yE = 1;
//		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));

		// thÃªm Brick
//		int xB = 3, yB = 1;
//		_board.addEntity(xB + yB * _width,
//				new LayeredEntity(xB, yB,
//					new Grass(xB, yB, Sprite.grass),
//					new Brick(xB, yB, Sprite.brick)
//				)
//		);

		// thÃªm Item kÃ¨m Brick che phá»§ á»Ÿ trÃªn
//		int xI = 1, yI = 2;
//		_board.addEntity(xI + yI * _width,
//				new LayeredEntity(xI, yI,
//					new Grass(xI ,yI, Sprite.grass),
//					new SpeedItem(xI, yI, Sprite.powerup_flames),
//					new Brick(xI, yI, Sprite.brick)
//				)
//		);
	}
	

}
