package cs221.GP01.main.java.model;

import cs221.GP01.main.java.ui.Settings;
import cs221.GP01.main.java.ui.UI;
import cs221.GP01.main.java.ui.controllers.GameView;
import cs221.GP01.main.java.ui.controllers.LoadGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The backend main controller
 * @author Samuel Jones - srj12@aber.ac.uk
 * @author Nathan Williams - naw21@aber.ac.uk
 * @version 0.8
 */
public class JoggleCube implements IJoggleCube {

    private static JoggleCube joggleCube;

    private Dictionary dictionary;

    private HashMap<String, Dictionary> loadedDictionaries = new HashMap<>();

    private Cube cube;
    
    private GameTimer timer;

    private ArrayList<String> storedWords;

    private IHighScores currentCubeHighScores;

    private IHighScores overallHighScores;

    private String name;

    //en_dictionary was taken from an open source scrabble bot at
    //Currently American English
    //URL: https://github.com/jonbcard/scrabble-bot/blob/master/src/dictionary.txt
    //en = English (American)
    //cy = Cymraeg (Welsh)
    private String language;

    private HashMap<String, String> scores;

    private int currentScore = 0;

    public HashMap<String, Dictionary> getLoadedDictionaries() {
        return loadedDictionaries;
    }


    private JoggleCube(){
        findDocumentFolder();
        loadOverallScores();
        setLanguage();
    }

    public static JoggleCube getInstance(){
        if(joggleCube == null){
            synchronized (UI.class){
                if(joggleCube == null){
                    joggleCube = new JoggleCube();
                }
            }
        }
        return joggleCube;
    }

    //Start Random Game
    public void generateRandomGrid() {
        //Populate the cube randomly
        cube.populateCube();
        storedWords = new ArrayList<>();
        currentCubeHighScores = new HighScores();
    }

    //Start loaded game
    public boolean loadGrid(String filename) {
        //load this file into grid and high scores
        //Load save game from the file stream given
        try{
            try {
                //todo fix the handleMouseClick() in the LoadGrid.java as it's calling using null.grid
                String loadPath = System.getProperty("user.home") + "/Documents/JoggleCube/saves/" + filename + ".grid";

                File loadFile = new File(new URI(loadPath.replace("\\", "/")
                        .trim().replaceAll("\\u0020", "%20")).getPath());
                System.out.println(loadFile.getAbsolutePath());
                Scanner in = new Scanner(loadFile);
                //overrides the language settings
                Settings.setCurrLang(in.next());
                //loads in the cube letters
                cube.loadCube(in);
                currentCubeHighScores = new HighScores();
                currentCubeHighScores.loadScores(in);
            }catch(URISyntaxException e){
                System.err.println(e.toString());
            }
        } catch(FileNotFoundException | NoSuchElementException e){
            // Print error depending on caught exception
            if(e instanceof FileNotFoundException){
                // An error in file name
                LoadGrid.getInstance().showError("No Grid Selected");
            }else{
                // Error with the file format
                LoadGrid.getInstance().showError("Invalid or Corrupted Grid File");
            }


            return false;
        }
        //At this point the cube has been loaded in
        storedWords = new ArrayList<>();
        return true;
    }
    public boolean testWordValidity(String word) {
        //Test if already used
        if (storedWords.contains(word)){return false;}

        //Test if valid dictionary word
        if(dictionary.searchDictionary(word)){
            storedWords.add(word);
            currentScore += getWordScore(word);
            if(GameView.getInstance().getScoreLabel() != null)
                GameView.getInstance().getScoreLabel().setText(currentScore + "");
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

    public ObservableList<IScore> getOverallHighScores() {
        if(overallHighScores != null){
            return FXCollections.observableArrayList(overallHighScores.getScores());
        } else {
            return null;
        }
    }

    public ObservableList<IScore> getCurrentCubeHighScores() {
        if(currentCubeHighScores != null){
            return FXCollections.observableArrayList(currentCubeHighScores.getScores());
        } else {
            return null;
        }

    }

    //Get the grids from a saved file
    public ObservableList<String> getRecentGrids() {
        ArrayList<String> results = new ArrayList<>();
        try {
            //Finding the save folder
            String savePath = System.getProperty("user.home") + "/Documents/JoggleCube/saves";
            File saveFolder = new File(new URI(savePath.replace("\\", "/")
                    .trim().replaceAll("\\u0020", "%20")).getPath());

            //Using the folder we get all of the
            File[] listOfFiles = saveFolder.listFiles();
            try {
                //The NullPointerException is fine because it is caught in a try catch.
                for (File listOfFile : listOfFiles) {
                    results.add(listOfFile.getName());
                }
            }catch(NullPointerException e){
                System.err.println("No recent grids found!");
            }
        } catch(URISyntaxException e){
            System.err.println("Issue with the Syntax of URI in getRecentGrids()");
            System.err.println(e.toString());
        }

        //Remove the .grid from the file name
        ArrayList<String> newResults = new ArrayList<>();
        //Foreach loops suck and thus I didn't use one because it just broke everything when I used it
        for (int i = 0; i<results.size(); i++){
            String currentString = results.get(i);
            if(currentString.contains(".grid")){
                StringBuilder newC = new StringBuilder();
                //Remove .grid
                //i = 5 because .grid is 5 charecters
                for(int j = 5; j<currentString.length(); j++){
                    newC.append(currentString.charAt(j - 5));
                }
                newResults.add(newC.toString());
            }
        }
        return FXCollections.observableArrayList(newResults);
    }


    public boolean saveGrid(String filename) {
        try{
            //todo write an actual path, to the documents folder
            try {
                String savePath = System.getProperty("user.home") + "/Documents/JoggleCube/saves/" + filename + ".grid";
                File saveFile = new File(new URI(savePath.replace("\\", "/")
                        .trim().replaceAll("\\u0020", "%20")).getPath());
                PrintWriter out = new PrintWriter(saveFile);
                cube.saveCube(out);
                currentCubeHighScores.saveScores(out);
                out.close();
            }catch(URISyntaxException e){
                    System.out.println();
            }
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
        //todo implement this
        try {
            try {
                String highScore = System.getProperty("user.home") + "/Documents/JoggleCube/highscores/overAll.highscores";
                URI highScores = new URI(highScore.replace("\\", "/")
                        .trim().replaceAll("\\u0020", "%20"));
                File highScoresFile = new File(highScores.getPath());

                //Empty the file first
                PrintWriter out = new PrintWriter(highScoresFile);
                out.print("");
                out.close();

                //Create printwriter and pass to save method in highscores
                PrintWriter outFeed = new PrintWriter(highScoresFile);
                overallHighScores.saveScores(outFeed);
                outFeed.close();
            } catch (URISyntaxException e) {
                System.out.println(e.toString());
            }
        }catch(FileNotFoundException e){
            System.out.println(e.toString());
        }

    }

    /**
     * loads the overall scores from file
     */
    private void loadOverallScores(){
        try {
            try {
                String highScore = System.getProperty("user.home") + "/Documents/JoggleCube/highscores/overAll.highscores";
                URI highScores = new URI(highScore.replace("\\", "/")
                        .trim().replaceAll("\\u0020", "%20"));

                File overallHighScoresFile = new File(highScores.getPath());

                Scanner in = new Scanner(overallHighScoresFile);
                overallHighScores = new HighScores();
                overallHighScores.loadScores(in);

            } catch (URISyntaxException e) {
                System.out.println(e.toString());
            }
        }catch(FileNotFoundException e){
            System.out.println(e.toString());
        }
    }

    /**
     * returns the top highscore
     *
     * @return the top high score.
     */
    public int getHighestScore() {
        return overallHighScores.getHighestScore().getScore();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void startTimer() {
        timer = new GameTimer();
        Thread t = new Thread(timer);
        t.start();
    }

    @Override
    public void interruptTimer() {
        timer.interrupt();
    }

    /**
     * Using the first to letters as an example set the language by using "en" for american english
     */
    public void setLanguage(){
        language = Settings.getCurrLangPrefix();
        if (loadedDictionaries.containsKey(language)) {
            dictionary = loadedDictionaries.get(language);
        } else {
            loadNewDictionary();
        }
        cube = new Cube(language + "_letters");
        cube.setLanguage(language);
        scores = cube.getScores();
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
        word = word.toUpperCase();
        int sumOf = 0;
        for(int i = 0; i<word.length(); i++){
            if(i<word.length()-1 && scores.containsKey((word.charAt(i)+ "") + (word.charAt(i+1) + ""))){
                //If scores contains word[i] + word[i+1] then handle the doule letters
                sumOf += Integer.parseInt(scores.get((word.charAt(i)+ "") + (word.charAt(i+1) + "")));
                i++;
            } else if(scores.containsKey(String.valueOf(word.charAt(i)))){
                //If scores the letter and it's not possible that it was originally a double letter
                //handle the single letter addition of score.
                sumOf += Integer.parseInt(scores.get(word.charAt(i) + ""));
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
            System.err.println("URI Issue probably an OS issue trying to create Documents folder");
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
            String highScores = getClass().getResource("../../data/highscores").getFile();

            try {
                //Find grids
                File grid_1 = new File(new URI((savedGrids+ "/grid_1.grid").trim().replaceAll("\\u0020", "%20")).getPath());
                File grid_2 = new File(new URI((savedGrids+ "/grid_2.grid").trim().replaceAll("\\u0020", "%20")).getPath());
                File grid_3 = new File(new URI((savedGrids+ "/grid_3.grid").trim().replaceAll("\\u0020", "%20")).getPath());

                //Find highscores
                File overAllHighScores = new File(new URI((highScores+ "/overAll.highscores").trim().replaceAll("\\u0020", "%20")).getPath());


                //Then create them in the new directory
                FileUtils.copyFileToDirectory(grid_1, savesDir);
                FileUtils.copyFileToDirectory(grid_2, savesDir);
                FileUtils.copyFileToDirectory(grid_3, savesDir);

                FileUtils.copyFileToDirectory(overAllHighScores, highScoresDir);
            } catch(URISyntaxException e){
                System.out.println(e.toString());
            }
        } catch (IOException e){
            System.err.println("Failed creating Directories: " + e.toString());
        }
    }

    public void resetGameState(){
        if(currentScore > 0){
            IScore score = new Score(currentScore,name);
            currentCubeHighScores.addScore(score);
            overallHighScores.addScore(score);
        }
        storedWords = new ArrayList<>();
        currentScore = 0;
        if(GameView.getInstance().getScoreLabel() != null)
            GameView.getInstance().getScoreLabel().setText(currentScore + "");
        if(timer!=null)
            timer.resetTime();
    }
}
