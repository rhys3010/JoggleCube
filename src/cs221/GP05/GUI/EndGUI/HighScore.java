package cs221.GP05.GUI.EndGUI;

import javafx.beans.property.SimpleStringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  TEMPORARY CLASS
 *  Represent a Given High Score Entry -> Date/time of Score, Score
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1  DRAFT
 */
public class HighScore {

    /**
     * Achieved score
     */
    private final String score;

    /**
     * The Date of the achieved time
     */
    private final String date;

    /**
     * Constructor for a high score
     * Convert score and date value to string
     * @param score as an int
     */
    public HighScore(int score){
        // Convert Score to String
        this.score = Integer.toString(score);

        // Get Current Date
        Date currDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("[yyyy/MM/dd] - HH:mm:ss");

        // Display date as string
        this.date = dateFormat.format(currDate);
    }

    /**
     * @return Date of the score
     */
    public String getDate(){
        return date;
    }

    /**
     * @return The score
     */
    public String getScore(){
        return score;
    }

}
