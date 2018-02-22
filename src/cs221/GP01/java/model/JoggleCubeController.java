package cs221.GP01.java.model;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class JoggleCubeController implements IJoggleCubeController{

    private Dictionary dictionary = new Dictionary();
    private Cube cube;

    private ArrayList<String> storedWords;

    //en = English (American)
    private String language = "en";

    //dictionary.txt was taken from an open source scrabble bot at
    //Currently American English
    //URL: https://github.com/jonbcard/scrabble-bot/blob/master/src/dictionary.txt
    //todo adding in functionality for different dictionaries, as well as loading letters in different languages
    private final String dictionaryFileName = language + "_dictionary.txt";

    public JoggleCubeController(){
        //etc
        /*
        dictionary.loadDictionary(dictionaryFileName);
        System.out.println(dictionary.searchDictionary("INVALIDATED"));
        System.out.println(dictionary.searchDictionary("AA"));
        System.out.println(dictionary.searchDictionary("ZYZZYVAS"));
        System.out.println(dictionary.searchDictionary("llllllllllllllllllllllllllllllllll"));
        System.out.println(dictionary.searchDictionary(""));
        */
        cube = new Cube();
        cube.populateCube(language + "_letters.txt");
        ArrayList<int[]> xyzNeighbours = cube.getNeighbours(2,2,2);

        int i = 0;
    }

    public void startRandomGame() { }

    public void startGame(File file) { }

    //handle later
    public void pauseGame() { }

    //handle later
    public void resumeGame() { }


    public void endGame() { }
    public boolean testWordValidity(String word) {
        //Test if valid word in the grid

        //Test if already used

        //Test if valid dictionary word
        return dictionary.searchDictionary(word);
    }
    public String[][][] getCubeData() { return new String[0][][]; }
    public ObservableList<HighScore> getOverallHighScores() { return null; }
    public ObservableList<HighScore> getCurrentCubeHighScores() { return null; }
    public ObservableList<String> getRecentGrids() { return null; }
}
