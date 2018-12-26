package com.snake.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Apple will eventually become a Body part of the Snake. It is placed on a
 * random position of the board
 *
 */
public class Apple extends Body {

  private Random rand;
  private int applesize;

  /**
   * Makes a new Apple on a random position within the board's limits.
   * 
   * @param width
   *          Width of the board
   * @param height
   *          Height of the board
   * @param applesize
   *          Size of the Apple
   */
  public Apple(int width, int height, int applesize) {
    super();
    this.applesize = applesize;
    rand = new Random();
    setX(rand.nextInt(width / applesize) * applesize);
    setY(rand.nextInt(height / applesize) * applesize);
  }

  /**
   * Draws this Apple as a Red square
   */
  public void draw(Graphics g) {
    /*
     * g.setColor(Color.RED); g.fillRect(getX(), getY(), applesize, applesize);
     */
    Image apple = null;
    try {
      apple = ImageIO.read(new File("apple.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    g.drawImage(apple, getX(), getY(), getX() + applesize, getY() + applesize,
        0, 0, apple.getWidth(null), apple.getHeight(null), null);
  }

}
