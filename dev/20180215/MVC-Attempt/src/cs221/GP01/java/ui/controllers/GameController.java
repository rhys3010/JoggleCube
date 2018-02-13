/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.Mediator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
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
    private StackPane parent;

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
     * todo Do initialization stuff here
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
    }



    /**
     * When the End Game button is clicked it will load the EndGui scene.
     */
    @FXML
    private void btnEndGameClicked() throws IOException {
    }


    /**
     * When the pause button is clicked it will display an overlay and pause the game
     */
    @FXML
    private void btnPauseGameClicked() throws IOException{
    }


    /**
     * Re-enables the game screen after game is resumed
     */
    public void setScreenDisabled(Boolean status){
        gameScreen.setDisable(status);
    }
}
