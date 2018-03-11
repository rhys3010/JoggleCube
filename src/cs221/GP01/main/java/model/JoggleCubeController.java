package cs221.GP01.main.java.model;

import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.controllers.GameController;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The backend main controller
 * @author Samuel Jones - srj12@aber.ac.uk
 * @version 0.8
 */
public class JoggleCubeController implements IJoggleCubeController{

    private static JoggleCubeController joggleCube;

    private Dictionary dictionary;

    private HashMap<String, Dictionary> loadedDictionaries = new HashMap<>();

    private Cube cube;
    
    private IGameTimer timer;

    private ArrayList<String> storedWords;

    private IHighScores currentCubeHighScores;

    private IHighScores overallHighScores;

    //en_dictionary was taken from an open source scrabble bot at
    //Currently American English
    //URL: https://github.com/jonbcard/scrabble-bot/blob/master/src/dictionary.txt
    //en = English (American)
    //cy = Cymraeg (Welsh)
    private String language = "en";

    private HashMap<String, String> scores;

    private int currentScore = 0;

    public HashMap<String, Dictionary> getLoadedDictionaries() {
        return loadedDictionaries;
    }


    private JoggleCubeController(){
        //Load dictionary on creation of JoggleCubeController
        cube = new Cube(language + "_letters");
        loadNewDictionary();
        storedWords = new ArrayList<>();
        scores = cube.getScores();
        timer = new GameTimer();
        //todo during project start up write private method to check if folder for JoggleCube is in the User.home directory and create if not present, including the saves.
        findDocumentFolder();
    }

    public static JoggleCubeController getInstance(){
        if(joggleCube == null){
            synchronized (UIController.class){
                if(joggleCube == null){
                    joggleCube = new JoggleCubeController();
                }
            }
        }
        return joggleCube;
    }

    //Start Random Game
    public void generateRandomGrid() {
        //Reset game score
        currentScore = 0;
        GameController.getInstance().getScoreLabel().setText(currentScore + "");

        //Populate the cube randomly
        cube.populateCube(language + "_letters");
    }

    //Start loaded game
    public boolean loadGrid(String filename) {
        //Reset game score
        currentScore = 0;
        GameController.getInstance().getScoreLabel().setText(currentScore + "");
        //load this file into grid and high scores
        //Load save game from the file stream given
        String input;
        ArrayList<String> letters = new ArrayList<>();
        try{
            //todo write an actual path to the documents/saves directory
            File file = new File("path" + filename);
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
            return false;
        }

        if(!(letters.size() == 27)){
            System.out.println("Cube that is loaded is corrupt");
            return false;
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
        return true;
    }
    public boolean testWordValidity(String word) {
        //Test if already used
        if (storedWords.contains(word)){return false;}

        //Test if valid dictionary word
        if(dictionary.searchDictionary(word)){
            storedWords.add(word);
            currentScore += getWordScore(word);
            GameController.getInstance().getScoreLabel().setText(currentScore + "");
            return true;
        }
        return false;
    }

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

    //Need to look into how the Score classes are built from Lampros
    public ObservableList<Score> getOverallHighScores() { return null; }

    //Need to look into the same thing
    public ObservableList<Score> getCurrentCubeHighScores() { return null; }

    //Get the grids from a saved file
    public ObservableList<String> getRecentGrids() { return null; }


    public boolean saveGrid(String filename) {

        try{
            //todo write an actual path, to the documents folder
            File file = new File("path" + filename);
            PrintWriter out = new PrintWriter(file);
            //Print cube to a single array for output
            String flatCube[] = new String[27];
            int c = 0;
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    for(int k = 0; k<3; k++){
                        //flatCube[c] = "";
                        flatCube[c] = cube.getBlock(i, j, k).getLetter();
                        c++;
                    }
                }
            }

            //Use the Flat cube to output in the correct format
            int b = 0;
            for(int i = 0; i<9; i++){
                out.print(flatCube[b] + " ");
                out.print(flatCube[b+1] + " ");
                out.print(flatCube[b+2]);
                if(b != 25){
                    out.print("\n");
                }
                b += 3;
            }

            //Output the highscores
            // todo wait for highscores
            out.close();
        } catch (FileNotFoundException e){
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    /**
     * saves the overall scores to file
     */
    @Override
    public void saveOverallScores() {

    }

    @Override
    public void setName(String name) {
        System.out.println(name);
    }

    @Override
    public void startTimer() {
        //todo start this in a separate thread
        //timer.startTimer();
    }

    @Override
    public void interruptTimer() {
        //timer.interrupt();
        timer.resetTime();
    }

    /**
     * Using the first to letters as an example set the language by using "en" for american english
     * @param lang a string representing the language you wanted to create
     */
    public void setLanguage(String lang){
        language = lang;
        if (loadedDictionaries.containsKey(lang)) {
            dictionary = loadedDictionaries.get(lang);
        } else {
            loadNewDictionary();
        }
        cube = new Cube(language + "_letters");
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
                sumOf += Integer.parseInt(scores.get(word.charAt(i) + ""));
            } else if(scores.containsKey((word.charAt(i)+ "") + (word.charAt(i+1) + ""))){
                //Else if scores contains word[i] + word[i+1] then handle
                sumOf += Integer.parseInt(scores.get((word.charAt(i)+ "") + (word.charAt(i+1) + "")));
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
        return currentScore;
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
    public void loadNewDictionary() {
        dictionary = new Dictionary();
        loadedDictionaries.put(language,dictionary);
        dictionary.loadDictionary(language + "_dictionary");
    }

    private void findDocumentFolder(){
        //Works this way for windows but is fixed for the URI later
        //todo test on Linux @Rhys
        String documents = System.getProperty("user.home") + "\\Documents\\JoggleCube";
        try {
            URI uri = new URI(documents.replace("\\", "/").trim().replaceAll("\\u0020", "%20"));
            File file = new File(uri.toString());
            //If Folder does not exist create the whole directory
            if(!file.exists() || !file.isDirectory()) {
                createDirectory(uri);
            }
        }catch(URISyntaxException e){
            System.out.println("URI Issue probably an OS issue trying to create Documents folder");
        }
    }

    private void createDirectory(URI uri){
        //Create the folder + saves folder + 3 hard coded saved files
        //todo confirm whether or not the uri.toString() + directory name works in all circumstances
        File file = new File(uri.toString());
        try{
            //Create the JoggleCube folder
            Files.createDirectory(file.toPath());

            //Create the saves directory in JoggleCube
            File savesDir = new File (uri.toString()+"/saves");
            Files.createDirectory(savesDir.toPath());

            //todo discuss the use of savings settings in it's own directory/main joggle cube directory
            //Create the highscores directory in JoggleCube
            File highScoresDir = new File(uri.toString()+"/highscores");
            Files.createDirectory(highScoresDir.toPath());

            //Move the saved grids into this saves directory
            //Open the saved grids as files and highscores
            String savedGrids = getClass().getResource("../../data/savedgrids").getFile();
            System.out.println(new File(savedGrids).getAbsolutePath());
            String highScores = getClass().getResource("../../data/highscores").getFile();

            //Find grids
            File grid_1 = new File(savedGrids+"\\grid_1.grid");
            File grid_2 = new File(savedGrids+"\\grid_2.grid");
            File grid_3 = new File(savedGrids+"\\grid_3.grid");

            //Find highscores
            File hgrid_1 = new File(highScores+"/grid_1.highscores");
            File hgrid_2 = new File(highScores+"/grid_2.highscores");
            File hgrid_3 = new File(highScores+"/grid_3.highscores");

            //Then create them in the new directory
            FileUtils.copyFileToDirectory(grid_1, savesDir);
            FileUtils.copyFileToDirectory(grid_2, savesDir);
            FileUtils.copyFileToDirectory(grid_3, savesDir);

            FileUtils.copyFileToDirectory(hgrid_1, highScoresDir);
            FileUtils.copyFileToDirectory(hgrid_2, highScoresDir);
            FileUtils.copyFileToDirectory(hgrid_3, highScoresDir);

        } catch (IOException e){
            System.out.println("Failed creating Directories: " + e.toString());
        }
    }
}
