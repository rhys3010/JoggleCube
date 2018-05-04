/*
   * @(#) IHighScores.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.main.java.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * HighScores - The purpose of this Interface is to be able to store and load high scores from file
 * <p>
 * Used by JoggleCube
 *
 * @author Lampros Petridis (lap12)
 * @version 1.1
 */
public interface IHighScores {

    /**
     * Loads scores in from file.
     *
     * @param file the file to load the scores from.
     */
    void loadScores(Scanner file);

    /**
     * Saves the scores to this file.
     *
     * @param file the file to save the scores to.
     */
    void saveScores(PrintWriter file);

    /**
     * Add an individual score
     *
     * @param score the score to be added to this list of HighScores
     */
    void addScore(IScore score);

    /**
     * Get the Highest score.
     *
     * @return the highest score
     */
    IScore getHighestScore();

    /**
     * Get the list of high scores
     *
     * @return a list of the scores
     */
    ArrayList<IScore> getScores();
}
