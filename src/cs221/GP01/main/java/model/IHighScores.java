package cs221.GP01.main.java.model;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
     * add an individual score
     *
     * @param score the score to be added to this list of HighScores
     */
    void addScore(IScore score);

    /**
     * get the Highest score.
     *
     * @return the highest score
     */
    IScore getHighScore();

    /**
     *
     *
     * @return a list of the scores
     */
    ArrayList<IScore> getScores();
}
