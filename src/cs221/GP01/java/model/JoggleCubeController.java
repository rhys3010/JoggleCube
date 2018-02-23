package cs221.GP01.java.model;

import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class JoggleCubeController implements IJoggleCubeController{

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

        //Populate the cube when JoggleCubeController is called so there ia already a cube there
        //Just overwrite this cube if needed
        cube.populateCube(language + "_letters");


    }

    public void startRandomGame() { }

    public void startGame(File file) {

    }

    //handle later
    public void pauseGame() { }

    //handle later
    public void resumeGame() { }


    public void endGame() { }

    //DOne
    public boolean testWordValidity(String word) {
        /*
        //Test if valid word in the grid
        //Is this even nessercary at this point???? No it isn't.
        ArrayList<String> indLetters = new ArrayList<>();
        for(int i = 0; i<word.length();i++){
            if(word.charAt(i) == 'Q'){
                //Then handle Q and u after it
                indLetters.add("Qu");
                i++
                continue;
            }
            indLetters.add(String.valueOf(word.charAt(i)));
        }

        //Discover potential locations of the word
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                for(int k = 0; k<3; k++){
                    //If
                }
            }
        }
        */
        //Test if already used
        if (storedWords.contains(word)){return false;}

        //Test if valid dictionary word
        return dictionary.searchDictionary(word);
    }

    //Done
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

    /**
     * Using the first to letters as an example set the language by using "en" for american english
     * @param lang a string representing the language you wanted to create
     */
    public void setLanguage(String lang){
        language = lang;
    }

    private void loadSavedCube(){}

    //Stopped working on this because it only handles english and takes too long to write anyways.
    /*
    private int calculateValueOfWord(String word){
        int sumOfWord = 0;

        //Handle the word by having all characters confirmed to be upper case
        word = word.toUpperCase();

        //For the switch statement I will assume that the Q and u are handled together and just increment i by 1
        for(int i = 0; i<word.length(); i++){
            switch(word.charAt(i)) {
                case 'A':
                    sumOfWord += 1;
                    break;
                case 'B':
                    sumOfWord += 3;
                    break;
                case 'C':
                    sumOfWord += 3;
                    break;
                case 'D':
                    sumOfWord += 2;
                    break;
                case 'E':
                    sumOfWord += 1;
                    break;
                case 'F':
                    sumOfWord += 4;
                    break;
                case 'G':
                    sumOfWord += 2;
                    break;
                case 'H':
                    sumOfWord += 4;
                    break;
                case 'I':
                    sumOfWord += 1;
                    break;
                case 'J':
                    sumOfWord += 8;
                    break;
                case 'K':
                    sumOfWord += 5;
                    break;
                case 'L':
                    sumOfWord += 1;
                    break;
                case 'M':
                    sumOfWord += 3;
                    break;
                case 'N':
                    sumOfWord += 6;
                    break;

            }
        }

        //Return 3 times the value of the scrabble points
        return sumOfWord*3;
    }
    */
}
