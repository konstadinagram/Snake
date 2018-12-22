package com.snake.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Panel extends JPanel implements Runnable{
	
	private static final int W=800,H=800;
	private Thread thread;
	private boolean running=false;
	private final int TILESIZE=40;
	private ArrayList<Body> snake;
	private int x=40,y=40;
	private boolean right=false, left=false, up=false, down=true;
	private int size=3;
	private Apple a;
	private int millis=80;
	private JLabel label;

	
	public Panel() {
		setPreferredSize(new Dimension(W,H));
		setFocusable(true);
		label= new JLabel("Score: " + Math.pow(2, size));
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if (key==KeyEvent.VK_D && !left) {
					right=true;
					left=false;
					up=false;
					down=false;					
				}
				if (key==KeyEvent.VK_A && !right) {
					right=false;
					left=true;
					up=false;
					down=false;
				}
				if (key==KeyEvent.VK_W && !down) {
					right=false;
					left=false;
					up=true;
					down=false;
				}
				if (key==KeyEvent.VK_S && !up) {
					right=false;
					left=false;
					up=false;
					down=true;
				}
				
				if (key==KeyEvent.VK_SPACE)
					millis=40;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if (key==KeyEvent.VK_SPACE)
					millis=80;
				
			}
			
		});
		snake=new ArrayList<Body>();
		
		start();
	}

	public void gameplay() {
		if (snake.size()==0) {
			Body b=new Body(x,y,TILESIZE);
			snake.add(b);
			a=new Apple(W,H,TILESIZE);
			//putAppleOnFreeSpace();
		}
		
		if (a.isHitFrom(snake.get(snake.size()-1))) {
			size++;
			a=new Apple(W,H,TILESIZE);
			//putAppleOnFreeSpace();
		}
		
		
		
		for (int i=0;i<snake.size()-1;i++) {
			if (snake.get(i).isHitFrom(snake.get(snake.size()-1))){
				System.out.println("gg");
				size=3;
			}
		}
		crawl();
	}
	




	public void putAppleOnFreeSpace() {
		boolean isNotOnFreeSpace=false;
		do {
			a=new Apple(W,H,TILESIZE);
			for (int i=0;i<snake.size()-1; i++) {
				if (a.isHitFrom(snake.get(i))){
					isNotOnFreeSpace=true;
					break;
				}
				
			}
		}while (isNotOnFreeSpace);
		
		
	}

	public void paint(Graphics g) {
		makeGrid(g);
		for (int i=0; i<snake.size();i++) {
			snake.get(i).draw(g);
		}
		a.draw(g);
	}

	public void start() {	
		running=true;
		thread=new Thread(this,"game loop");
		thread.start();
	}
	
	public void stop() {
		
		
	}
	
	@Override
	public void run() {
		while(running) {
			gameplay();
			repaint();
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void makeGrid(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, W, H);
		//g.setColor(Color.WHITE);
		//for (int i=0;i<W/TILESIZE;i++) {
			//g.drawLine(i*TILESIZE, 0, i*TILESIZE, H);
		//}
		//for (int i=0;i<H/TILESIZE;i++) {
			//g.drawLine(0, i*TILESIZE, W, i*TILESIZE);
		//}
	}
	
	public void crawl() {
			if (right)
				x+=TILESIZE;
			if (left)
				x-=TILESIZE;
			if (up)
				y-=TILESIZE;
			if (down)
				y+=TILESIZE;
			if (x==W)
				x=0;
			if (x<0)
				x=W-TILESIZE;
			if (y<0)
				y=H-TILESIZE;
			if (y==H)
				y=0;
			Body b=new Body(x,y,TILESIZE);
			snake.add(b);
			int i=0;
			for(;;) {
				if (snake.size()>size) {
					snake.remove(i);
				}
				else {
					break;
				}
			}
				
			
			
	}
		
}


