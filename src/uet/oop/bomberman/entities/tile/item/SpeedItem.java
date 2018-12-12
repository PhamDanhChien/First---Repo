package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.effect;
import uet.oop.bomberman.Game;

public class SpeedItem extends Item {

	public SpeedItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý bomber ăn item
		if (e instanceof Bomber) {
            if (!this.isRemoved()) {
                System.out.println("speed");
                Game.addBomberSpeed(0.3);
                remove();
                effect.item();
            }
            
        }
		return false;
	}
}
