package cs221.GP01.java.model;

import javafx.collections.ObservableList;

import java.io.File;

public class JoggleCubeController implements JoggleCube{
    public JoggleCubeController(){
        //loadDictionary();
        //etc
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
    public ObservableList<HighScore> getCurrentCubeHighScores() { return null; }
    public ObservableList<String> getRecentGrids() { return null; }
}
