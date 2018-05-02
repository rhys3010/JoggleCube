/*
   * @(#) IScore.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.main.java.model;


import java.io.PrintWriter;

/**
 *  Score - Encapsulate and represent a given High Score entry
 *  Represent a Given High Score Entry  Date/time of Score, Score
 *
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Lampros Petridis (lap12)
 * @version 1.1
 */
public interface IScore {
    /**
     * Return the date of the Score.
     *
     * @return date of the score
     */
    String getDate();

    /**
     * Return the value of the Score.
     *
     * @return the value of this score
     */
    Integer getScore();

    /**
     * Return the name of the person who got this score.
     *
     * @return the name of the person that completed this score.
     */
    String getName();

    /**
     * Save this score to the file
     *
     * @param file the file to save the score to.
     */
    void saveScore(PrintWriter file);
}
