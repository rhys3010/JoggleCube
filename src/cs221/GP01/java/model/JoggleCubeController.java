package cs221.GP01.java.model;

/**
 * @author Samuel Jones - srj12
 * @version 0.1
 */
public interface JoggleCubeController {

    /**
     * This handles the loading of the dictionary
     * @param DictionaryFileName The file-name of the dictionary that is saved with the program
     */
    private void loadDictionary(String DictionaryFileName){}

    /**
     * This handles the loading of the high scores
     * @param highScoreFileName The file-name of the high scores that is saved with the program
     */
    private void loadHighScores(String highScoreFileName){}

    /**
     * This handles the saving of the High scores to a text file
     * @param highScoreFileName The file-name of the high scores that is loaded with the program
     */
    private void saveHighScores(String highScoreFileName){}

    /**
     * This is the main application and handles all of the running of the program for the the main.java file including
     * loading and saving of all data and the interaction of the backend with the UI controllers
     */
    public void runJoggleCube();
}
