package cs221.GP01.java.model;

import javafx.collections.ObservableList;

import java.io.File;

/**
 * @author Samuel Jones - srj12
 * @author Nathan - naw21
 * @version 0.2
 */
public interface JoggleCube {


    void startRandomGame();


    void startGame(File file);

    /**
     * pauses
     */
    void pauseGame();

    /**
     * resumes the timer
     */
    void resumeGame();

    /**
     * ends the game
     */
    void endGame();

    /**
     * checks if word is valid
     * @param word the word to check
     * @return the validity of the word
     */
    boolean testWordValidity(String word);

    /**
     *
     * @return the cubes data
     */
    String[][][] getCubeData();

    /**
     *
     * @return the loaded grid high scores
     */
    ObservableList<HighScore> getLoadedGridHighScores();

    /**
     *
     * @return the overall highscores
     */
    ObservableList<HighScore> getOverallHighScores();

    /**
     *
     * @return recently loaded grids
     */
    ObservableList<String> getRecentGrids();
}
