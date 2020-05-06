package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	} 

	/**
	 * tick allows the objects to move
	 */
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 38);
		y = Game.clamp(y, 0, Game.HEIGHT - 61);
		
		collision();
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.08f, handler));

	}
	
	public void collision() {
		//handler has the list of GameObjects, the size method will return the amount of objs in the list
		for(int i = 0; i < handler.object.size(); i++) {
			
			//the method get will return the current obj in the list
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy) {			
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//cast the g object of Graphics into g2d (Graphics2D object)
		//Graphics2D has a method that we need
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBounds());
		
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
		
		
	}

}
