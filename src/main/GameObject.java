
package main;

import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * 
 * @author Windows 10
 * 
 *         Holds information of all game objects, E.g. players, coins ,and
 *         powers.
 *         
 *         
 *
 */
public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;

	}

	// abstract methods
	public abstract void tick();

	public abstract void render(Graphics g);

	//it handles if two rectangles intercept each other. Returns true.
	public abstract Rectangle getBounds();
	
	public Rectangle getBound() {
		
		return new Rectangle();
	
	}
	
	
	// setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public void setVelX(int velX) {
		this.velX = velX;

	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	// getters
	public int getX() {
		return x;

	}

	public int getY() {
		return y;

	}

	public ID getId() {
		return id;

	}

	public int getVelX() {
		return velX;

	}

	public int getVelY() {
		return velY;
	}
}
