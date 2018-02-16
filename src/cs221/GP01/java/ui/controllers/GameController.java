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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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

    /**
     * Initialize game screen
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){

        //
        foundWordsList.setItems(foundWords);


        String[][][] letters = UIController.getJoggleCube().getCubeData();
        GridPane[] twoDGrid =  {top,middle,bottom};
        for(int k = 0; k<3; k++){
            for(int i = 0; i<3;i++){
                for(int j = 0; j < 3; j++){
                    Label label = new Label(letters[k][i][j]);
                    label.setOnMouseClicked( e ->{
                        //todo write checks etc.
                        textField.appendText(label.getText());
                    });
                    twoDGrid[k].add(label,i,j);
                }
            }
        }

        //todo load this data into the other grids for display
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
        if(textField.getText().equals("")) {
            if (UIController.getJoggleCube().testWordValidity(textField.getText())) {
                foundWords.add(textField.getText());
                textField.setText("");
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
}
