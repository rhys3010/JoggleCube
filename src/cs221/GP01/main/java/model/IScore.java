package cs221.GP01.main.java.model;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

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
