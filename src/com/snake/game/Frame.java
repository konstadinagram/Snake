package com.snake.game;

import javax.swing.JFrame;

/**
 * This is the JFrame of the game. This JFrame, has only a Panel and terminates
 * the app when closed
 *
 */
public class Frame extends JFrame {

  private static final long serialVersionUID = 1L;

  /**
   * Makes the JFrame and adds the panel
   */
  public Frame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Snake. Move with WASD and Hold Space for Turbo");
    setResizable(false);
    Panel s = new Panel();
    add(s);
    setVisible(true);
    pack();
    // Move the Frame to the center of the screen
    setLocationRelativeTo(null);
  }

  public static void main(String[] args) {
    new Frame();
  }

}
