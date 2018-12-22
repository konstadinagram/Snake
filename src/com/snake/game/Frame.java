package com.snake.game;

import javax.swing.JFrame;


public class Frame extends JFrame{
	

	public Frame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake. Move with WASD and Space for Turbo");
		setResizable(false);
		Panel s=new Panel();
		add(s);
		setVisible(true); 
		pack();
		setLocationRelativeTo(null);
	}
	

	public static void main(String[] args) {
		new Frame();
	}

}
