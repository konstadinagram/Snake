package com.snake.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple extends Body{
	
	private Random rand;
	private int applesize;
	


	public Apple(int width, int height, int applesize) {
		super();
		this.applesize=applesize;
		rand=new Random();
		setX(rand.nextInt(width/applesize)*applesize);
		setY(rand.nextInt(height/applesize)*applesize);
		//System.out.println(("x= " + getX() + " y= " + getY()));
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), applesize, applesize);
	}


}
