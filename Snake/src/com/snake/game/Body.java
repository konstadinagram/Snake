package com.snake.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Body {
	
	private int x;
	private int y;
	private int size;
	
	public Body() {
		
	}
	
	public Body(int x, int y, int size) {
		this.x=x;
		this.y=y;
		this.size=size;	}
	
	
	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(this.x, this.y, getSize(), getSize());
	}
	
	public boolean isHitFrom(Body b) {
		if (getX()==b.getX()&&getY()==b.getY()) {
			return true;}
		else
			return false;
	}
		
}


