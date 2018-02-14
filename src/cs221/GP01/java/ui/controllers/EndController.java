/*
   * @(#) EndController.java 1.1 2018/02/12
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
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * EndController - A class that controls the Pause subscene that is defined in End.fxml
 * <p>
 * todo fix dodgy model overlaying
 * todo add prompt for exiting
 * Used with End.fxml
 *
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class EndController implements Initializable{

    /**
     * The root node
     */
    @FXML
    Node root;

    /**
     * The End Overlay
     */
    @FXML
    Node endOverlay;

    /**
     * An instance of the mediator object to interface with backend
     */
    private Mediator mediator;

    /**
     * Constructor to ensure mediator object is passed
     * @param mediator
     */
    public EndController(Mediator mediator){
        this.mediator = mediator;
    }

    /**
     * todo Do initialization stuff here
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
    }


    /**
     * When the High Score button is pressed, change scene to high score screen
     * @see HighScoreController
     * @throws IOException - if FXML file could not be found/opened
     */
    @FXML
    void btnHighScoreClicked() throws IOException {
        mediator.getScreenController().show(ScreenType.HIGH_SCORES);
        mediator.initalizeController(ScreenType.HIGH_SCORES);
        // Unload End Overlay
        mediator.getScreenController().hide(ScreenType.END);


    }

    /**
     * When the 'save' button is clicked prompt user to chose a save location
     */
    @FXML
    void btnSaveClicked(){
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Save Cube");
        File file = fileChooser.showSaveDialog(newStage);
        if (file != null) {

        } else {
            //todo add try again pop-up
        }
    }


    /**
     * When the 'return to menu' button is clicked change scene to menu scene
     * @see StartController
     */
    @FXML
    void btnMenuClicked() throws IOException{
        mediator.getScreenController().show(ScreenType.START);

        // Unload End Overlay
        mediator.getScreenController().hide(ScreenType.END);
    }
}
