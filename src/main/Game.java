package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -1997325596863821774L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; // Gives 16x9 ratio

	private Thread thread; // in how many threads the game will run
	private boolean running = false;

	private Random r;
	private Handler handler; // Through the render method it updates all objects inside the game
	private HUD hud;
	private Spawn spawner;
	

	public Game() {
		handler = new Handler();

		// gets the key code (ASCII)
		this.addKeyListener(new KeyInput(handler));

		new Window(WIDTH, HEIGHT, "Let's build a game!", this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);

		r = new Random();

		// 50 is the number of objects in the game
		// for(int i = 0; i < 50; i++) {
		// handler.addObject(new Player(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.Player));
		// }

		handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));

		//for (int i = 0; i < 20; i++)
		handler.addObject(new BasicEnemy(r.nextInt( WIDTH ), r.nextInt( HEIGHT ), ID.BasicEnemy, handler));
	

	}

	/**
	 * 
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * 
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Game();
	}

	/**
	 * game loop = heat beat of the game
	 * this is a popular game loop:
	 */
	@Override
	public void run() {
		
		//this gives you control keys without need to click the screen
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * 
	 */
	private void tick() {
		handler.tick();
		hud.tick();
		spawner.tick();
	}

	/**
	 * 
	 */
	// clamp method
	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	/**
	 * 
	 */
	private void render() {
		// renders all frames through BufferStrategy class
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);

		hud.render(g);
		
		g.dispose();
		bs.show();
	}
}
