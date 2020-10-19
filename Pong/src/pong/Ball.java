//Creating Ball for game.

package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	double xVel, yVel , x , y;
	
	public Ball() {												//Setting Starting Position(x,y) and Speed(xVel , yVel).
		x = 350;
		y = 250;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}
	
	public void restart() {										//Used to set Ball Position every round.
		x = 350;
		y = 250;
		xVel = getRandomSpeed() * getRandomDirection();
		yVel = getRandomSpeed() * getRandomDirection();
	}
	
	public double getRandomSpeed() {							//To get Random Ball Speed every time.
		return (Math.random() * 2.5 + 2);
	}
	
	public int getRandomDirection() {							//To get Random Ball Direction every time.
		int rand = (int)(Math.random() * 2);
		if(rand == 1)
			return 1;
		else 
			return -1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x-10 , (int)y-10 , 20 , 20);
	}
	
	public void checkPaddleCollision(Paddle p1 , Paddle p2) {	//Checking for Pad Collision with Ball.
		if(x >= 650) {
			if(y >= p1.getY() && y <= p1.getY() + 80) {
				xVel = -xVel;									//Reverse Direction in Case of Collision.
			}
		}
		else if(x <= 50) {
			if(y >= p2.getY() && y <= p2.getY() + 80) {			//Reverse Direction in Case of Collision.
				xVel = -xVel;
			}
		}
	}
	
	public void move() {
		x += xVel;
		y += yVel;
		
		if(y < 10)												//To Stop Ball from going out of Screen.
			yVel = -yVel;
		if(y > 490)
			yVel = -yVel;
	}
	
	public int getX(){
		return (int)x;
	}
	
	public int getY(){
		return (int)y;
	}
}
