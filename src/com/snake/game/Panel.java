package com.snake.game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Most of the game is drawn in this Panel. This class also contains the game's
 * logic.
 *
 */
public class Panel extends JPanel implements Runnable {

  private static final long serialVersionUID = 1L;
  // W is the Width of the Panel and H is the Height
  private static final int W = 800, H = 800;
  private Thread thread;
  private boolean isSnakeAlive;
  private final int TILESIZE = 40;
  // The Snake is a List of Bodies
  private ArrayList<Body> snake;
  // Starting snake coordinates
  private int x = 40, y = 40;
  // The directions the snake can go
  private boolean right = false, left = false, up = false, down = true;
  // Starting snake size is 3 Bodies, it grows by eating Apples
  private int size = 3;
  // a is the Apple that the snake must chase in order to grow
  private Apple a;
  // This is used to control the speed of the game
  private int millis = 80;
  private int score;

  /**
   * Upon making the Panel, the game starts
   */
  public Panel() {
    setPreferredSize(new Dimension(W, H));
    // setFocusable for the controls to work
    setFocusable(true);
    // Add the controls
    addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {

      }

      /*
       * Every time one of the WASD keys are pressed, the snake's direction
       * changes. However, the snake cannot make a 180 degree turn, so the
       * direction changes only when the snake is not already going on the
       * opposite direction of the direction the player requests the snake to
       * go.
       */
      @Override
      public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_D && !left) {
          right = true;
          left = false;
          up = false;
          down = false;
        }
        if (key == KeyEvent.VK_A && !right) {
          right = false;
          left = true;
          up = false;
          down = false;
        }
        if (key == KeyEvent.VK_W && !down) {
          right = false;
          left = false;
          up = true;
          down = false;
        }
        if (key == KeyEvent.VK_S && !up) {
          right = false;
          left = false;
          up = false;
          down = true;
        }
        /* If the players holds down the space key the game goes faster */
        if (key == KeyEvent.VK_SPACE)
          millis = 40;
      }

      @Override
      /*
       * If the player releases the Space key the game returns to normal speed
       */
      public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE)
          millis = 80;

      }

    });
    // Create the snake
    snake = new ArrayList<Body>();

    start();
  }

  /**
   * Checks for collisions, and updates the snake's position
   */
  public void gameplay() {
    // When the snake size is 0 (at the start of the game), creates the snake
    if (snake.size() == 0) {
      Body b = new Body(x, y, TILESIZE);
      snake.add(b);
      a = new Apple(W, H, TILESIZE);
    }
    // If the Apple isHitFrom the head of the snake, the snakes size increases
    if (a.isHitFrom(snake.get(snake.size() - 1))) {
      size++;
      score = (int) (size * size * 0.7);
      // After the old Apple is eaten new Apple is made
      a = new Apple(W, H, TILESIZE);
    }
    // This checks if the Snake eats itself.
    for (int i = 0; i < snake.size() - 1; i++) {
      if (snake.get(i).isHitFrom(snake.get(snake.size() - 1))) {
        isSnakeAlive = false;
        // Snake becomes small again
        size = 3;
      }
    }
    crawl();
  }

  /* Failed attempt to prevent apples from being created on the snakes body */
  public void putAppleOnFreeSpace() {
    boolean isNotOnFreeSpace = false;
    do {
      a = new Apple(W, H, TILESIZE);
      for (int i = 0; i < snake.size() - 1; i++) {
        if (a.isHitFrom(snake.get(i))) {
          isNotOnFreeSpace = true;
          break;
        }

      }
    } while (isNotOnFreeSpace);

  }

  public void paint(Graphics g) {
    // First make the background
    makeGrid(g);
    // Then the snake
    for (int i = 0; i < snake.size(); i++) {
      snake.get(i).draw(g);
    }
    // Then the apple
    a.draw(g);
    /*
     * Then the score, so it is always on top
     */
    drawScore(g);
    // If the game is over print highscore information
    if (!isSnakeAlive) {
      g.setColor(Color.yellow);
      if (score > HighscoreManager.loadHighscore()) {
        g.drawString("You scored " + score + " points. It's a new record !!!",
            50, 400);
        // Save new highscore
        HighscoreManager.save(score);
      } else {
        g.drawString(
            "You didn't manage to break your "
                + HighscoreManager.loadHighscore() + " point highscore",
            0, 400);

      }
    }
  }

  public void start() {
    isSnakeAlive = true;
    thread = new Thread(this, "game loop");
    thread.start();
  }

  public void stop() {

  }

  @Override
  public void run() {
    while (isSnakeAlive) {
      // run logic
      gameplay();
      // draw Graphics
      repaint();
      // wait
      try {
        Thread.sleep(millis);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      /*
       * If snake dies, wait for player to read message and make the snake alive
       * again so the game can continue
       */
      if (!isSnakeAlive) {
        try {
          Thread.sleep(2000);
          isSnakeAlive = true;
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

  }

  /**
   * Makes a black background for the game to be played on
   * 
   * @param g
   *          Graphics Object
   */
  public void makeGrid(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, W, H);
    /*
     * This code makes helping lines for testing purposes
     * g.setColor(Color.WHITE); for (int i=0;i<W/TILESIZE;i++) {
     * g.drawLine(i*TILESIZE, 0, i*TILESIZE, H); } for (int
     * i=0;i<H/TILESIZE;i++) { g.drawLine(0, i*TILESIZE, W, i*TILESIZE); }
     */
  }

  public void drawScore(Graphics g) {
    g.setColor(Color.blue);
    g.setFont(new Font("Arial", Font.BOLD, 30));
    g.drawString("Score: " + score, 10, 30);
  }

  /**
   * This method creates a crawl illusion, by removing at every frame the tail
   * of the snake and making a new head
   */
  public void crawl() {
    // Moves the current snake coordinates depending on the current direction
    if (right)
      x += TILESIZE;
    if (left)
      x -= TILESIZE;
    if (up)
      y -= TILESIZE;
    if (down)
      y += TILESIZE;
    // If the snake hits a wall it comes out from the other side
    if (x == W)
      x = 0;
    if (x < 0)
      x = W - TILESIZE;
    if (y < 0)
      y = H - TILESIZE;
    if (y == H)
      y = 0;
    // Make a new Body at the new coordinates
    Body b = new Body(x, y, TILESIZE);
    snake.add(b);
    // However keep the snake size within the size cap
    int i = 0;
    for (;;) {
      if (snake.size() > size) {
        snake.remove(i);
      } else {
        break;
      }
    }

  }

}
