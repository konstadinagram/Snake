package com.snake.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Utility class for storing highscores
 *
 */

public class HighscoreManager {
  static File file = new File("Highscore");
  
  /**
   * Saves the score on the highscore file
   * @param score score to save
   */
  public static void save(int score) {
    try {
      PrintWriter out = new PrintWriter(file);
      out.write(String.valueOf(score));
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error 404: File not found");
    }
  }
  /**
   * Reads the highscore file and returns the highscore
   * @return Player's highests score
   */
  public static int loadHighscore() {
    Scanner in;
    int highscore;
    try {
      in = new Scanner(file);
      highscore = Integer.parseInt(in.nextLine());
    } catch (FileNotFoundException e) {
      // If there is no highscore file, assume the highscore is 0
      highscore = 0;
    }
    return highscore;
  }
}
