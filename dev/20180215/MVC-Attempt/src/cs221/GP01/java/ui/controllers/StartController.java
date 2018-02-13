/*
   * @(#) StartController.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.Mediator;
import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * StartController - A class that does something.
 * <p>
 * How it is used
 * todo add a Highscore button
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */

public class StartController implements Initializable{
    /**
     * The parent VBox in this scene.
     */
    @FXML
    private VBox parent;

    /**
     * An instance of the mediator object to interface with backend
     */
    private Mediator mediator;

    /**
     * Constructor to ensure mediator object is passed
     * @param mediator
     */
    public StartController(Mediator mediator){
        this.mediator = mediator;
    }

    /**
     * todo Do initialization stuff here
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
    }


    /**
     * When the Start New Grid button is clicked it will load the Game scene with a new grid.
     *
     */
    @FXML
    private void btnStartNewGridClicked() throws IOException {
        mediator.getScreenController().show(ScreenType.GAME);

    }

    /**
     * When the Load Grid button is clicked it will load the LoadGrid scene.
     *
     */
    @FXML
    private void btnLoadGridClicked() throws IOException {
        mediator.getScreenController().show(ScreenType.LOAD);
    }

    /**
     * When the HighScore button is clicked it will load the Highscore scene.
     */

    public void btnHighScoreClicked() throws IOException{
        mediator.getScreenController().show(ScreenType.HIGH_SCORES);

    }

}