package com.snake.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * This is a square, with x and y coordinates and a size, that acts as part of
 * the Snake's body. It has collision detection capabilities.
 *
 */
public class Body {

  private int x;
  private int y;
  private int size;

  public Body() {

  }

  /**
   * Makes a new Body
   * 
   * @param x
   *          x coordinate
   * @param y
   *          y coordinate
   * @param size
   *          size of the body
   */
  public Body(int x, int y, int size) {
    this.x = x;
    this.y = y;
    this.size = size;
  }

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

  /**
   * Draws this Body as a Green square
   * 
   * @param g
   *          Graphics Object
   */
  public void draw(Graphics g) {
    g.setColor(Color.GREEN);
    g.fillRect(this.x, this.y, getSize(), getSize());
  }

  /**
   * Checks if this Body is hit by another Body
   * 
   * @param b
   *          Body to check
   * @return true if the two Bodies collide, false if the don't
   */
  public boolean isHitFrom(Body b) {
    return (this.getX() == b.getX() && this.getY() == b.getY());
  }

}
