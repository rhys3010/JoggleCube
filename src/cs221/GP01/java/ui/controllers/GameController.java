/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.Mediator;
import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
     * The main game screen content (cube I/O etc)
     */
    @FXML
    private VBox gameScreen;

    /**
     * An instance of the mediator object to interface with backend
     */
    private Mediator mediator;

    /**
     * Constructor to ensure mediator object is passed
     * @param mediator
     */
    public GameController(Mediator mediator){
        this.mediator = mediator;
    }

    /**
     * Initialize game screen
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ArrayList<String> letters = mediator.getHandleOutput().getCubeData();

        //todo load this data into grids for display
    }

    /**
     * When the End Game button is clicked it will load the EndGui scene.
     */
    @FXML
    private void btnEndGameClicked() throws IOException {
        mediator.getScreenController().show(ScreenType.END);

        // Backend Example
        mediator.getHandleInput().endGame();
    }


    /**
     * When the pause button is clicked it will display an overlay and pause the game
     */
    @FXML
    private void btnPauseGameClicked() throws IOException{
        mediator.getScreenController().show(ScreenType.PAUSE);

        // Backend Example
        mediator.getHandleInput().pauseGame();
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
