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
     * saves the current score to grid file and overall.
     *
     * @param filename
     */
    boolean saveGrid(String filename);

    /**
     * saves the overall scores to file
     */
    void saveOverallScores();

    /**
     * Starts the in game timer in a new process thread
     */
    void startTimer();

    /**
     * Stop the timer
     */
    void interruptTimer();

    /**
     * Returns whether or not the game is a fresh not loaded game or not
     *
     * @return True if is a new game, false if it is a loaded game
     */
    boolean getGamesStateNew();

    /**
     * Returns a HashMap with String and Dictionary as the key, value pair, of all of the currently loaded dictionaries.
     *
     * @return a HashMap of String-Dictionary key-value pair
     */
    HashMap<String, Dictionary> getLoadedDictionaries();

    /**
     * Clears all highscores in the overall highscores variables as well as the stored files!
     */
    void clearHighScores();

    /**
     * Take all the variables and change it so that the game can be started again, if the grid has been saved previously
     * then save over file.
     */
    void resetGameState();

    /**
     * sets the current players name
     */
    void setName(String name);

    /**
     * Using the first to letters as an example set the language by using "en" for american english
     */
    void setLanguage();

    /**
     * Generate the word score for this word using scrabble score * 3
     *
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
     *
     * @return the top high score.
     */
    int getHighestScore();

    /**
     * allows the game Controller to get a grid.
     *
     * @return the cubes data
     */
    String[][][] getCubeData();

    /**
     * Return the current cube's highscores in a format usable by JavaFX
     *
     * @return ObservableList of IScore Interfaces
     */
    ObservableList<IScore> getCurrentCubeHighScores();

    /**
     * Returns the overall HighScores across all cubes in a format usable by JavaFX
     *
     * @return ObservableList of IScore Interfaces
     */
    ObservableList<IScore> getOverallHighScores();

    /**
     * Returns a list of all saved grids and returns them in a format expected by the front end
     *
     * @return an ObservableList of Strings of all the available saved grids
     */
    ObservableList<String> getRecentGrids();
}