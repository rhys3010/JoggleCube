package cs221.GP01.main.java.model;

import java.io.File;
import java.util.ArrayList;

public interface IHighScores {

    /**
     * Loads scores in from file.
     *
     * @param file the file to load the scores from.
     */
    void loadScores(File file);


    /**
     * Saves the scores to this file.
     *
     * @param file the file to save the scores to.
     */
    void saveScores(File file);

    /**
     * add an individual score
     *
     * @param score the score to be added to this list of HighScores
     */
    void addScore(Score score);

    /**
     * get the Highest score.
     *
     * @return the highest score
     */
    Score getHighScore();

    /**
     *
     *
     * @return a list of the scores
     */
    ArrayList<Score> getScores();
}
