//Main Class

package pong;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")														//To Remove Warning.
public class Tennis extends Applet implements Runnable , KeyListener , ActionListener{
	final int WIDTH = 700 , HEIGHT = 530;
	Thread thread;
	HumanPaddle p1;
	HumanPaddle2 p2;
	AIPaddle a1;
	Ball b1;
	String score1 = "";
	int scount1 = 1;														//To Keep Count Of Scores.
	String score2 = "";
	int scount2 = 1;
	Graphics gfx;															//To Act as a Buffer to stop Flickering.
	Image img;
	
	Button single = new Button("Single Player Game");
	Button multi = new Button(" Multi-Player Game ");
	Button exit = new Button("            Exit            ");
	
	boolean gameStartedSingle;
	boolean gameStartedMulti;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);											//Setting Size of Frame.
		gameStartedSingle = false;
		gameStartedMulti = false;
		this.addKeyListener(this);
		this.setFocusable(true);
		p1 = new HumanPaddle(1);
		p2 =new HumanPaddle2(2);
		b1 = new Ball();
		a1 = new AIPaddle(2 , b1);
		img = createImage(WIDTH , HEIGHT);
		gfx = img.getGraphics();
		add(single);														//Adding Buttons.
		single.addActionListener(this);
		add(multi);
		multi.addActionListener(this);
		add(exit);
		exit.addActionListener(this);
		thread = new Thread(this);
		thread.start();														//Starting Thread.
	}
		
	public void paint(Graphics g) {																	
		Font font = new Font ("", Font.BOLD, 11);
		single.setFont(font);
		multi.setFont(font);
		exit.setFont(font);
		single.setLocation(300, 250);
		multi.setLocation(300, 280);
		exit.setLocation(300, 310);
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT - 30);
		g.setFont(font);
		if(b1.getX() < -10) {												//Ball goes Outside.
			if(scount2 < 3)
			{
				score2 = score2 + "*";										//Increments Score.
				b1.restart();
				scount2++;
			}
			else {
				g.setColor(Color.red);
				g.drawString("Game Over.  Player Red Wins." , 270 , 180);
				terminate();
			}
		}
		else if(b1.getX() > 710) {
			
			if(scount1 < 3)
			{
				score1 = score1 + "*";
				b1.restart();
				scount1++;
			}
			else {
				if(gameStartedSingle) {
					g.setColor(Color.green);
					g.drawString("Game Over.  CPU wins." , 270 , 180);
					terminate();
				}
				else if(gameStartedMulti) {
					g.setColor(Color.blue);
					g.drawString("Game Over.  Player Blue Wins." , 270 , 180);
					terminate();
				}
			}
		}
		if(!gameStartedSingle && !gameStartedMulti) {
			gfx.setColor(Color.white);
			Font myFont = new Font ("TimesRoman", 3, 94);
			gfx.setFont(myFont);
			gfx.drawString("Ping Pong", 100 , 160);
			gfx.setFont(getFont());
			gfx.setColor(Color.black);
			gfx.drawString("Rules: The First Player to Score Three Points Wins!!!", 10 , 520);
			single.setVisible(true);
			multi.setVisible(true);
			exit.setVisible(true);
		}
		else {
			gfx.setColor(Color.white);
			gfx.drawLine(350, 0, 350, 500);
			gfx.drawOval(315 , 215 , 70 , 70);
			single.setVisible(false);
			multi.setVisible(false);
			exit.setVisible(false);
			p1.draw(gfx);
			b1.draw(gfx);
			if(gameStartedSingle) {
				a1.draw(gfx);
				gfx.setColor(Color.green);
				gfx.fillRect(0, 500, 300 , 30);
				gfx.setColor(Color.CYAN);
				gfx.fillRect(300, 500 , 50 , 30);
				gfx.setColor(Color.black);
				gfx.drawString(score1, 305 , 520);
				gfx.drawString("CPU", 50 , 520);
			}
			else if(gameStartedMulti) {
				p2.draw(gfx);
				gfx.setColor(Color.blue);
				gfx.fillRect(0, 500, 300 , 30);
				gfx.setColor(Color.CYAN);
				gfx.fillRect(300 , 500 , 50 , 30);
				gfx.setColor(Color.black);
				gfx.drawString(score1, 305 , 520);
				gfx.drawString("Player Blue: Use W and S to Control Paddle.", 10 , 520);	
			}
			gfx.setColor(Color.orange);
			gfx.fillRect(350 , 500 , 50 , 30);
			gfx.setColor(Color.red);
			gfx.fillRect(400, 500, 300 , 30);
			gfx.setColor(Color.black);
			gfx.drawString(score2, 355 , 520);
			gfx.drawString("Player Red: Use Arrow Keys to Control Paddle.", 410 , 520);
		}
		g.drawImage(img, 0, 0, this); 
	}
	
	public void terminate()                                           		//To Terminate the Execution after 4 Seconds.
	{
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		for(;;)
		{
			if(gameStartedMulti) {
				p1.move();
				p2.move();
				b1.move();
				b1.checkPaddleCollision(p1, p2);
			}
			if(gameStartedSingle) {
				p1.move();
				a1.move();
				b1.move();
				b1.checkPaddleCollision(p1, a1);
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			p2.setUpAccel(true);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			p2.setDownAccel(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			p2.setUpAccel(false);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			p2.setDownAccel(false);
		}		
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("Single Player Game"))
			gameStartedSingle = true;
		else if(s.contentEquals(" Multi-Player Game "))
			gameStartedMulti = true;
		else
			System.exit(0);
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}