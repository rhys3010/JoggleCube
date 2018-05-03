/*
   * @(#) IJoggleCube.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.main.java.model;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.HashMap;

/**
 * @author Samuel Jones - srj12
 * @author Nathan - naw21
 * @version 1.1
 */
public interface IJoggleCube {



    /**
     * create a random grid
     */
    void generateRandomGrid();


    /**
     * load grid from file.
     *
     * @param filename
     */
    boolean loadGrid(String filename);

    /**
     * checks if the word is valid or not.
     *
     * @param word the word to check
     * @return the validity of the word
     */
    boolean testWordValidity(String word);

    /**
     * allows the game Controller to get a grid.
     *
     * @return the cubes data
     */
    String[][][] getCubeData();

    /**
     *
     * @return the loaded grid high scores, return null if no grid loaded
     */
    ObservableList<IScore> getCurrentCubeHighScores();

    /**
     *
     *
     * @return the overall highscores
     */
    ObservableList<IScore> getOverallHighScores();

    /**
     *
     * @return recently loaded grid files as strings
     */
    ObservableList<String> getRecentGrids();

    /**
     *
     * saves the current score to grid file and overall.
     *
     * @param filename
     */
    boolean saveGrid(String filename);

    /**
     *
     * saves the overall scores to file
     *
     */
    void saveOverallScores();

    /**
     * Generate the word score for this word using scrabble score * 3
     * @param word the word to get the score for
     * @return returns an int that is the socre
     */
    int getWordScore(String word);


    /**
     * gets the score for the current game.
     *
     * @return the score for the current game
     */
    int getScore();

    /**
     * returns the top highscore
     * @return the top high score.
     */
    int getHighestScore();

    /**
     * sets the current players name
     */
    void setName(String name);

    void startTimer();

    void interruptTimer();

    void setLanguage();

    void resetGameState();

    boolean getGamesStateNew();

    HashMap<String, Dictionary> getLoadedDictionaries();

    /**
     * Clears all highscores in the overall highscores variables as well as the stored files!
     */
    void clearHighScores();
}