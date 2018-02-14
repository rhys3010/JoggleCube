/*
   * @(#) PauseController.java 1.0 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.Mediator;
import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * PauseController - A class that controls the Pause subscene that is defined in Pause.fxml
 * <p>
 * todo fix dodgy model overlaying
 * todo add prompt for exiting
 *
 * todo add save button
 * Used with Pause.fxml
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class PauseController implements Initializable{

    /**
     * The pause overlay
     */
    @FXML
    VBox pauseOverlay;


    /**
     * An instance of the mediator object to interface with backend
     */
    private Mediator mediator;

    /**
     * Constructor to ensure mediator object is passed
     * @param mediator
     */
    public PauseController(Mediator mediator){
        this.mediator = mediator;
    }

    /**
     * todo Do initialization stuff here
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
    }


    /**
     * When the resume button is pressed, remove overlay and resume the game.
     * @see GameController
     * @throws IOException
     */
    @FXML
    void btnResumeClicked() throws IOException{
        mediator.getScreenController().hide(ScreenType.PAUSE);

        // Backend Example
        mediator.getHandleInput().resumeGame();
    }

    /**
     * When the exit button is pressed, go to home screen.
     */
    @FXML
    void btnExitClicked(){
        mediator.getScreenController().show(ScreenType.START);
        // Backend Example
        mediator.getHandleInput().loadMenu();
    }

    /**
     * Get the root node of the scene
     * @return VBox - the root node
     */
    public VBox getRootNode(){
        return pauseOverlay;
    }
}
