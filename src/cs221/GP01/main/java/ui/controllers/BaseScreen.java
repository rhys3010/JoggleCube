/*
 * @(#) BaseScreenController.java 0.1 2018/02/23
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * The Parent Class of any screen controller
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21@aber.ac.uk)
 * @version 1.1
 */
public class BaseScreen {

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
     * Handle the menu button press
     */
    @FXML
    public void btnMenuClicked(){
        Navigation.getInstance().switchScreen(ScreenType.START);
    }

    /**
     * Handle the highscore button being pressed
     */
    @FXML
    public void btnHighScoreClicked(){
        Navigation.getInstance().switchScreen(ScreenType.HIGH_SCORES);
    }

    /**
     * Handle the help button being pressed
     */
    @FXML
    public void btnHelpClicked(){
        Navigation.getInstance().showOverlay(ScreenType.HELP, this);
    }

    /**
     * Handle the settings button being pressed
     */
    @FXML
    public void btnSettingsClicked(){
        Navigation.getInstance().showOverlay(ScreenType.SETTINGS, this);
    }

    /**
     * Get the root node of a given screen
     *
     * @return root
     */
    public StackPane getRoot(){
        return root;
    }

    /**
     * Get the main node of the FXML
     *
     * @return mainNode
     */
    public Node getMainNode(){
        return mainNode;
    }
}
