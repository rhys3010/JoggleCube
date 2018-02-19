package cs221.GP01.java.model;

import javafx.collections.ObservableList;

import java.io.File;

public class JoggleCubeController implements IJoggleCubeController{

    private Dictionary dictionary = new Dictionary();

    //dictionary.txt was taken from an open source scrabble bot at
    //Currently American English
    //URL: https://github.com/jonbcard/scrabble-bot/blob/master/src/dictionary.txt
    //todo adding in functionality for different dictionaries, as well as loading letters in different languages
    private final String dictionaryFileName = "dictionary.txt";

    public JoggleCubeController(){
        //etc
        dictionary.loadDictionary(dictionaryFileName);
        System.out.println(dictionary.searchDictionary("AA"));
        System.out.println(dictionary.searchDictionary("ZYZZYVAS"));
    }

    public void startRandomGame() { }
    public void startGame(File file) { }
    public void pauseGame() { }
    public void resumeGame() { }
    public void endGame() { }
    public boolean testWordValidity(String word) { return false; }
    public String[][][] getCubeData() { return new String[0][][]; }
    public ObservableList<HighScore> getLoadedGridHighScores() { return null; }
    public ObservableList<HighScore> getOverallHighScores() { return null; }
    public ObservableList<String> getRecentGrids() { return null; }
}
