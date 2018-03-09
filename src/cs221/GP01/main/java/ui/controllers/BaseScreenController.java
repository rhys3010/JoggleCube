/*
 * @(#) BaseScreenController.java 0.1 2018/02/23
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.ui.NavigationController;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * The Parent Class of any screen controller
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1
 */
public class BaseScreenController {

    /**
     * The root node of a given screen
     */
    @FXML
    protected StackPane root;

    /**
     * The main node of a given screen
     */
    @FXML
    private Node mainNode;


    /**
     * Get the root node of a given screen
     * @return root
     */
    public StackPane getRoot(){
        return root;
    }

    /**
     * Get the main node of the FXML
     * @return mainNode
     */
    public Node getMainNode(){
        return mainNode;
    }

    /**
     * Handle the menu button press
     */
    @FXML
    private void btnMenuClicked(){
        NavigationController.getInstance().switchScreen(ScreenType.START);
    }

    /**
     * Handle the highscore button being pressed
     */
    @FXML
    private void btnHighScoreClicked(){
        NavigationController.getInstance().switchScreen(ScreenType.HIGH_SCORES);
    }

    /**
     * Handle the help button being pressed
     */
    @FXML
    private void btnHelpClicked(){
        NavigationController.getInstance().showOverlay(ScreenType.HELP, this);
    }

    /**
     * Handle the settings button being pressed
     */
    @FXML
    private void btnSettingsClicked(){
        NavigationController.getInstance().showOverlay(ScreenType.SETTINGS, this);
    }

}
