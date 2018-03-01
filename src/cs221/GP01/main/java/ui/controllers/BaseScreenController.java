/*
 * @(#) BaseScreenController.java 0.1 2018/02/23
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

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
     * An instance of the UIController object to interface with backend
     */
    protected UIController UIController;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public BaseScreenController(UIController UIController){
        this.UIController = UIController;
    }


    /**
     * The root node of a given screen
     */
    @FXML
    private StackPane root;

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
        UIController.getNavigationController().switchScreen(ScreenType.START);
    }

    /**
     * Handle the highscore button being pressed
     */
    @FXML
    private void btnHighScoreClicked(){
        UIController.getNavigationController().switchScreen(ScreenType.HIGH_SCORES);
    }

    /**
     * Handle the help button being pressed
     */
    @FXML
    private void btnHelpClicked(){
        UIController.getNavigationController().showOverlay(ScreenType.HELP, this);
    }

    /**
     * Handle the settings button being pressed
     */
    @FXML
    private void btnSettingsClicked(){
        UIController.getNavigationController().showOverlay(ScreenType.SETTINGS, this);
    }

}
