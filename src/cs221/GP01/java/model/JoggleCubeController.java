package cs221.GP01.java.model;

import cs221.GP01.java.ui.IUIController;
import cs221.GP01.java.ui.controllers.GameController;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The backend main controller
 * @author Samuel Jones - srj12@aber.ac.uk
 * @version 0.3
 */
public class JoggleCubeController implements IJoggleCubeController{

    private IUIController ui;

    private Dictionary dictionary;
    private Cube cube;

    private ArrayList<String> storedWords;

    private GameController theGameController;

    //en = English (American)
    private String language = "en";

    //dictionary.txt was taken from an open source scrabble bot at
    //Currently American English
    //URL: https://github.com/jonbcard/scrabble-bot/blob/master/src/dictionary.txt
    private String dictionaryFileName = language + "_dictionary";

    private HashMap<String, String> scores;

    public JoggleCubeController(){
        //Load dictionary on creation of JoggleCubeController
        cube = new Cube(language + "_letters");
        dictionary = new Dictionary();
        dictionary.loadDictionary(dictionaryFileName);
        storedWords = new ArrayList<>();
        scores = cube.getScores();
    }

    @Override
    public void setUI(IUIController controller) {
        ui = controller;
    }

    public void generateRandomGrid() { cube.populateCube(language + "_letters"); }

    public void loadGrid(File file) {
        //load this file into grid and high scores
        //Load save game from the file stream given
        String input;
        ArrayList<String> letters = new ArrayList<>();
        try{
            Scanner in = new Scanner(file);
            //Load in all of the letters
            while(in.hasNext() && letters.size() < 27) {
                input = in.next();
                letters.add(input);
            }
            //Load in all of the high scores
            /*
            while(in.hasNext()){
                input = in.next()
            }
             */

        } catch(FileNotFoundException e){
            //An error in file name
            System.out.println("Game Save not found");
        }

        if(!(letters.size() == 27)){
            System.out.println("Cube that is loaded is corrupt");
        }

        //Letters contains all of the cube in order from 0,0,0 to 2,2,2
        int index = 0;
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3; j++){
                for(int k = 0; k<3; k++){
                    cube.setBlock(i,j,k,new Block(letters.get(index)));
                    index++;
                }
            }
        }
        //At this point the cube has been loaded in
    }

    //todo Question whether if return true should get the score and then add to the score in a private instance variable
    public boolean testWordValidity(String word) {
        //Test if already used
        if (storedWords.contains(word)){return false;}

        //Test if valid dictionary word
        if(dictionary.searchDictionary(word)){
            storedWords.add(word);
            return true;
        }
        return false;
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

    //Need to look into how the HighScore classes are built from Lampros
    public ObservableList<HighScore> getOverallHighScores() { return null; }

    //Need to look into the same thing
    public ObservableList<HighScore> getCurrentCubeHighScores() { return null; }

    //Get the grids from a saved file
    public ObservableList<String> getRecentGrids() { return null; }


    public void saveGrid(File file, String name) {

    }

    @Override
    public void setName(String name) {
        System.out.println(name);
    }

    @Override
    public void startTimer(Label timer) {
        
    }

    /**
     * Using the first to letters as an example set the language by using "en" for american english
     * @param lang a string representing the language you wanted to create
     */
    public void setLanguage(String lang){
        language = lang;
    }

    /**
     * Generate the word score for this word using scrabble score * 3
     * @param word the word to get the score for
     * @return returns an int that is the score
     */
    //Scores are stored in the file next to the letter seperated by a ':' e.g. A:3
    public int getWordScore(String word){
        //Split the word up into the different letters including 'Qu' and then search the hashmap for each and
        //return a sum of the scores
        int sumOf = 0;
        for(int i = 0; i<word.length(); i++){
            if(scores.containsKey(String.valueOf(word.charAt(i)))){
                //If scores contains the word continue else check for double letters
                sumOf += Integer.getInteger(scores.get(word.charAt(i) + ""));
            } else if(scores.containsKey(word.charAt(i) + word.charAt(i+1) + "")){
                //Else if scores contains word[i] + word[i+1] then handle
                sumOf += Integer.getInteger(scores.get(word.charAt(i) + word.charAt(i+1)+ ""));
                i++;
            } else{
                System.out.println("Score is broken for this letter" + word.charAt(i));
            }
        }
        //Return * 3 scores
        return sumOf * sumOf;
    }


    /**
     * gets the score for the current game.
     *
     * @return the score for the current game
     */
    public int getScore() {
        return 0;
    }

    /**
     * returns the top highscore
     *
     * @return the top high score.
     */
    public int getHighScore() {
        return 0;
    }

    /**
     * Based on the current language will rebuild the backend dictionary object with the correct language
     */
    public void reloadDictionary() {
        dictionary = new Dictionary();
        dictionary.loadDictionary(language + "_dictionary");
    }
}
