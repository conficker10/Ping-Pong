//Human Paddle controlled by User in Single and MultiUser Game.

package pong;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle implements Paddle{
	double y , yVel;
	boolean upAccel , downAccel;						//To Control Movement.
	final double GRAVITY = 0.9;							//To stop the Paddle.
	int player , x;
	
	public HumanPaddle(int Player){
		upAccel = false;
		downAccel = false;
		y = 210;
		yVel = 0;
		if(Player == 1)
			x = 660;
		else
			x = 20;
	}
	
	public void draw(Graphics g) {          			//Size and Color of Paddle.
		g.setColor(Color.red);
		g.fillRect(x, (int)y, 20, 80);
	}
	
	public void move() {								//To move Paddle.
		if(upAccel) {
			yVel -= 2;
		}
		else if(downAccel) {
			yVel += 2;
		}
		else if(!upAccel && !downAccel) {
			yVel *= GRAVITY;
		}
		
		if(yVel >= 5)
			yVel = 5;
		else if(yVel <= -5)
			yVel = -5;
		
		y += yVel;
		
		if(y < 0)
			y = 0;
		else if(y > 420)
			y = 420;
	}
	
	public void setUpAccel(boolean input) {
		upAccel = input;
	}
	
	public void setDownAccel(boolean input) {
		downAccel = input;
	}

	public int getY() {
		return (int)y;
	}

}
