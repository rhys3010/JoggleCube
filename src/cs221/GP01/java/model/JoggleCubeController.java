package cs221.GP01.java.model;

import cs221.GP01.java.ui.IUIController;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class JoggleCubeController implements IJoggleCubeController {

    private IUIController ui;

    private Dictionary dictionary = new Dictionary();
    private Cube cube = new Cube();

    private ArrayList<String> storedWords;

    //en = English (American)
    private String language = "en";

    //dictionary.txt was taken from an open source scrabble bot at
    //Currently American English
    //URL: https://github.com/jonbcard/scrabble-bot/blob/master/src/dictionary.txt
    //todo adding in functionality for different dictionaries, as well as loading letters in different languages
    private final String dictionaryFileName = language + "_dictionary";

    public JoggleCubeController(){
        //Load dictionary on creation of JoggleCubeController
        dictionary.loadDictionary(dictionaryFileName);
    }


    @Override
    public void setUI(IUIController controller) {
        ui = controller;
    }


    public void generateRandomGrid() { cube.populateCube(language + "_letters"); }

    public void loadGrid(File file) {
        //load this file into grid and highscores

        //cube.loadCube(file);
    }

    public void endGame(){ }


    public boolean testWordValidity(String word) {
        //Test if already used
        //if (storedWords.contains(word)){return false;}

        //Test if valid dictionary word
        return dictionary.searchDictionary(word);
    }

    //todo check data in cube before loading
    public String[][][] getCubeData() {
        String[][][] stringCube = new String[3][3][3];

        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                for(int k = 0; k<3; k++){
                    stringCube[i][j][k] = cube.getBlock(i,j,k).getLetter();
                }
            }
        }

        return stringCube;
    }
    public ObservableList<HighScore> getOverallHighScores() { return null; }
    public ObservableList<HighScore> getCurrentCubeHighScores() { return null; }
    public ObservableList<String> getRecentGrids() { return null; }
    public void saveGrid(File file, String name) {

    }

    /**
     * Using the first to letters as an example set the language by using "en" for american english
     * @param lang a string representing the language you wanted to create
     */
    public void setLanguage(String lang){
        language = lang;
    }

    private void loadSavedCube(){}
}
