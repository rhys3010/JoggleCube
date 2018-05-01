/*
   * @(#) HighScores.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.main.java.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * HighScores - The purpose of this class is to be able to store and load high scores from file
 * Used by JoggleCube
 *
 * @author Lampros Petridis (lap12)
 * @version 1.1
 * @see cs221.GP01.main.java.model.IHighScores
 */
public class HighScores implements IHighScores {
    private ArrayList <IScore> scores;

    /**
     * Creates a arraylist in order to store the high scores
     */
    public HighScores() {
        scores = new ArrayList<>();
    }

    /**
     * Loads an arraylist containing the high scores
     *
     * @param file the file to load the scores from.
     */
    @Override
    public void loadScores(Scanner file) {
        while(file.hasNext()){
            scores.add(new Score(file));
        }
    }

    /**
     * Saves an arraylist containg the high scores to a file
     *
     * @param file the file to save the scores to.
     */
    @Override
    public void saveScores(PrintWriter file) {
        scores.forEach(iScore -> iScore.saveScore(file));
    }

    /**
     * Adds high scores to the arraylist
     *
     * @param score the score to be added to this list of HighScores
     */
    @Override
    public void addScore(IScore score) {
        scores.add(score);
    }

    /**
     * Sorts the arraylist and returns the high score
     *
     * @return high score
     */
    @Override
    public IScore getHighestScore() {
        scores.sort((o1, o2) -> {
            if(o1.getScore() > o2.getScore()){
                return -1;
            }else if(o1.getScore() < o2.getScore()){
                return  1;
            } else {
                return 0;
            }
        });
        if(!scores.isEmpty()){
            return scores.get(0);
        } else {
            return new Score(0,"");
        }
    }

    /**
     * Returns all the scores in the arraylist
     *
     * @return scores
     */
    @Override
    public ArrayList<IScore> getScores() {
        return scores;
    }

    /**
     * To string method used for testing
     *
     * @return highScores in string
     */
    @Override
    public String toString() {
        return "HighScores:"  + scores ;
    }
}
