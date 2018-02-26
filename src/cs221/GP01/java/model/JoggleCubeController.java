package cs221.GP01.java.model;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import cs221.GP01.java.ui.IUIController;
=======
import cs221.GP01.java.ui.controllers.GameController;
>>>>>>> Stashed changes
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The backend main controller
 * @author Samuel Jones - srj12@aber.ac.uk
 * @version 0.3
 */
public class JoggleCubeController implements IJoggleCubeController{
>>>>>>> Stashed changes

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
    //todo adding in functionality for different dictionaries, as well as loading letters in different languages
    private final String dictionaryFileName = language + "_dictionary";

    public JoggleCubeController(){
        //Load dictionary on creation of JoggleCubeController
        cube = new Cube();
        dictionary = new Dictionary();
        dictionary.loadDictionary(dictionaryFileName);
        storedWords = new ArrayList<>();
    }

<<<<<<< Updated upstream

    @Override
    public void setUI(IUIController controller) {
        ui = controller;
    }
        //todo Find a way to point to theGameController
    }

<<<<<<< Updated upstream

    public void generateRandomGrid() { cube.populateCube(language + "_letters"); }

    public void loadGrid(File file) {
        //load this file into grid and highscores
=======
=======
>>>>>>> Stashed changes
        //todo Find a way to point to theGameController
    //Ditto with startGame()
    public void startRandomGame() {
        //Create a random cube
        cube.populateCube(language + "_letters");
    }

    public void startRandomGame() { }

    public void startGame(File file) {
        //Load save game from the file stream given
        String input;
        ArrayList<String> letters = new ArrayList<>();
        try{
            Scanner in = new Scanner(file);
            while(in.hasNext()){
                input = in.nextLine();
                String next;
                int forLoopLength = 3;
                for(int i = 0; i<forLoopLength; i++){
                    if(input.charAt(i) == 'Q'){
                        //Handle Qu for the english dictionary
                        next = "Qu";
                        //Happens at the end to skip the u part
                        forLoopLength++;
                    } else {
                        next = String.valueOf(input.charAt(i));
                    }
                    letters.add(next);
                }
            }
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
>>>>>>> Stashed changes

        //cube.loadCube(file);

        //temp
        cube.populateCube(language + "_letters");
    }

    //Needs clarification on structure of this method
    public void endGame() { }
>>>>>>> Stashed changes

    public boolean testWordValidity(String word) {
        //Add all of the stored words to the arrayList
        for (int i = 0; i<theGameController.getFoundWords().size(); i++){
            String newWord = theGameController.getFoundWords().get(i);
            //If not already in the array add to the array else do nothing
            //Don't necessarily need to check the arraylist for original contents as it would work with duplicates anyways
            if(!storedWords.contains(newWord)){
                storedWords.add(newWord);
            }
        }

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

    //Get the grids from a saved
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
