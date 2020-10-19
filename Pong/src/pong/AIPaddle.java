// To Create a Paddle that moves on its own.

package pong;

import java.awt.Color;
import java.awt.Graphics;

public class AIPaddle implements Paddle{
	double y , yVel;
	boolean upAccel , downAccel;
	final double GRAVITY = 0.9;
	int player , x;
	Ball b1;
	
	public AIPaddle(int Player , Ball b){
		upAccel = false;
		downAccel = false;
		b1 = b;
		y = 210;
		yVel = 0;
		if(Player == 1)
			x = 660;
		else
			x = 20;
	}
	
	public void draw(Graphics g) {							//Setting Color and Size of CPU Paddle
		g.setColor(Color.green);
		g.fillRect(x, (int)y, 20, 80);
	}
	
	public void move() {
		y = b1.getY() - 1.005;							 	//Moving the Paddle by Tracing Ball.
		
		if(y < 0)											//Restricting Paddle to go outside the frame.
			y = 0;
		else if(y > 420)
			y = 420;
	}
	
	public int getY() {
		return (int)y;
	}

}
