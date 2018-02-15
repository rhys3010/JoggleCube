/*
   * @(#) StartController.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.UIController;
import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

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
     * The root Node in this scene.
     */
    @FXML
    private Node root;

    /**
     * An instance of the UIController object to interface with backend
     */
    private UIController UIController;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public StartController(UIController UIController){
        this.UIController = UIController;
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
    private void btnStartNewGridClicked() {
        UIController.getScreenController().show(ScreenType.GAME);
        UIController.getJoggleCube().startRandomGame();
        UIController.initalizeController(ScreenType.GAME);
    }

    /**
     * When the Load Grid button is clicked it will load the LoadGrid scene.
     *
     */
    @FXML
    private void btnLoadGridClicked() {
        UIController.getScreenController().show(ScreenType.LOAD);
        UIController.initalizeController(ScreenType.LOAD);
    }

    /**
     *
     */
    @FXML
    public void btnSettingsClicked() {
        UIController.getScreenController().show(ScreenType.SETTINGS);
    }

    /**
     * When the HighScore button is clicked it will load the Highscore scene.
     */
    @FXML
    public void btnHighScoreClicked() throws IOException{
        UIController.getScreenController().show(ScreenType.HIGH_SCORES);
        UIController.initalizeController(ScreenType.HIGH_SCORES);
    }

    /**
     * Get the root node of the FXML
     * @return root - the root node
     */
    public Node getRoot(){
        return root;
    }

}