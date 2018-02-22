/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.UIController;
import cs221.GP01.java.ui.ScreenType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GameController - A class that controls the Game scene that is defined in Game.fxml
 * <p>
 * Used with Game.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class GameController implements Initializable{
    /**
     * the parent StackPane in this scene.
     */
    @FXML
    private StackPane root;

    /**
     * The tab pane that contains the cube representations
     */
    @FXML
    private TabPane cubeContainer;

    @FXML
    private ListView<String> foundWordsList;

    private ObservableList<String> foundWords = FXCollections.observableArrayList();

    @FXML
    private GridPane top,middle,bottom;


    /**
     * The main game screen content (cube I/O etc)
     */
    @FXML
    private VBox gameScreen;

    @FXML
    private TextField textField;

    /**
     * An instance of the UIController object to interface with backend
     */
    private UIController UIController;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public GameController(UIController UIController){
        this.UIController = UIController;
    }


    private Label[][][] labelCube;


    void CubeClicked(int k,int i,int j,Label label){
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    if(x == k && y == i && z == j){
                        setSelected(x,y,z);
                    } else if(neighbour(k,i,j,x,y,z)){
                        setActive(x,y,z,false);
                    } else {
                        setInActive(x,y,z);
                    }
                }
            }
        }
        textField.appendText(label.getText());
    }

    private void setSelected(int x, int y, int z) {
        labelCube[x][y][z].setStyle("-fx-background-color:#ff0000;");
        labelCube[x][y][z].setOnMouseClicked(null);
    }

    private void setInActive(int x, int y, int z) {
        if(!labelCube[x][y][z].getStyle().contains("-fx-background-color:#550000;")){
            labelCube[x][y][z].setStyle("-fx-background-color:#566377;");
            labelCube[x][y][z].setOnMouseClicked(null);
        }
    }

    private void setActive(int x, int y, int z, boolean overide) {
        if(labelCube[x][y][z].getStyle().contains("-fx-background-color:#ff0000;") && !overide){
            labelCube[x][y][z].setStyle("-fx-background-color:#550000;");
        } else if(!labelCube[x][y][z].getStyle().contains("-fx-background-color:#550000;") || overide) {
            labelCube[x][y][z].setStyle("-fx-background-color:#2980b9;");
            labelCube[x][y][z].setOnMouseClicked(e -> CubeClicked(x, y, z, labelCube[x][y][z]));
        }
    }

    /**
     * Initialize game screen
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        labelCube = new Label[3][3][3];
        //
        foundWordsList.setItems(foundWords);


        String[][][] letters = UIController.getJoggleCube().getCubeData();
        if(letters[0][0][0] != null) {
            GridPane[] twoDGrid = {top, middle, bottom};
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Label label = new Label(letters[k][i][j]);
                        labelCube[k][i][j] = label;
                        setActive(k,i,j,true);
                        twoDGrid[k].add(label, i, j);
                    }
                }
            }
        }
        //todo load this data into the other grids for display


        //todo create an instance of timer and start it to keep updating
    }

    private boolean neighbour(int f,int g, int h, int a, int b, int c) {
        int x,y,z;
        for(int i = -1; i<2; i++) {
            x = f;
            x += i;
            for (int j = -1; j < 2; j++) {
                y = g;
                y += j;
                for (int k = -1; k < 2; k++) {
                    z = h;
                    z += k;
                    if(a == x && b == y && c == z)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * When the End Game button is clicked it will load the EndGui scene.
     */
    @FXML
    private void btnEndGameClicked() {
        UIController.getScreenController().show(ScreenType.END);

        // Backend Example
        UIController.getJoggleCube().endGame();
    }

    @FXML
    private void btnSubmitClicked() {
        if(!textField.getText().equals("")) {
            if (UIController.getJoggleCube().testWordValidity(textField.getText())) {
                foundWords.add(textField.getText());
                textField.setText("");
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        for (int z = 0; z < 3; z++) {
                            setActive(x,y,z,true);
                        }
                    }
                }



                //todo change the colour of the button to green
            } else {
                //todo change the colour of the button to red
            }
        } else {
            //todo change the colour of the button to red
        }


    }

    //todo add method to change the submit buttons colour back when the the mouse is next clicked maybe?


    /**
     * When the pause button is clicked it will display an overlay and pause the game
     */
    @FXML
    private void btnPauseGameClicked(){
        UIController.getScreenController().show(ScreenType.PAUSE);

        // Backend Example
        UIController.getJoggleCube().pauseGame();
    }

    /**
     * Handle the settings button being clicked
     */
    @FXML
    private void btnSettingsClicked(){
        UIController.getScreenController().show(ScreenType.SETTINGS);
    }


    /**
     * Get the root node of the FXML
     * @return root - the root node
     */
    public StackPane getRootNode(){
        return root;
    }

    /**
     * Get the game screen
     * @return gameScreen - the FXML node of the game screen
     */
    public VBox getGameScreen(){
        return gameScreen;
    }

    /**
     * Get the cube container tab pane
     * @return cubeContainer - the FXML node for tabpane
     */
    public TabPane getCubeContainer(){
        return cubeContainer;
    }
}
