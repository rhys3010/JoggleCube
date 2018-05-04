/*
   * @(#) Score.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package uk.ac.aber.cs221.gp01.main.java.model;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *  Score - Encapsulate and represent a given High Score entry
 *  Represent a Given High Score Entry  Date/time of Score, Score
 *
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Lampros Petridis (lap12)
 * @version 1.1
 * @see IScore
 */
public class Score implements IScore {

    /**
     * Achieved score
     */
    private final Integer score;

    /**
     * The Date of the achieved time
     */
    private final String date;

    /**
     * The name of the Highscore Holder
     */
    private final String name;

    /**
     * Constructor for a high score
     * Convert score and date value to string
     *
     * @param score as an int
     */
    public Score(Integer score, String name){
        // Convert Score to String
        this.score = score;

        // Assign name
        this.name = name;

        // Get Current Date
        Date currDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        // Display date as string
        this.date = dateFormat.format(currDate);
    }

    /**
     * Construct Score from the next entry in the file.
     *
     * @param file to load the scores from
     */
    public Score(Scanner file){
        date = file.next() + " " + file.next();
        score = new Integer(file.next());
        name = file.next();
    }

    /**
     * Save this score to the file
     *
     * @param file the file to save the score to.
     */
    @Override
    public void saveScore(PrintWriter file) {
        file.print(date + " ");
        file.print(score + " ");
        file.print(name + "\n");
    }

    /**
     * prints information about score
     *
     * @return toString information for score, date and name
     */
    @Override
    public String toString() {
        return "score =" + score +
                ", date ='" + date + '\'' +
                ", name ='" + name + '\'' ;
    }

    /**
     * Checks whether score is equal
     *
     * @param o score
     * @return True or false
     */
    @Override
    public boolean equals(Object o){
        Score otherScore = (Score)o;
        if(this.date.equals(otherScore.date) && this.name.equals(otherScore.name) && this.score.equals(otherScore.score)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Get the date of a given high score entry
     *
     * @return Date of the score
     */
    public String getDate(){
        return date;
    }

    /**
     * Get the score value of a given high score entry
     *
     * @return The score
     */
    public Integer getScore(){
        return score;
    }

    /**
     * Get the name of a given high score holder
     *
     * @return The highscore holder's name
     */
    public String getName(){
        return name;
    }
}
