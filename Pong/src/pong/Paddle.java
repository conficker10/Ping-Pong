package pong;

import java.awt.Graphics;

public interface Paddle {										//Paddle Interface containing General Properties.
	public void draw(Graphics g);
	public void move();
	public int getY();
}
