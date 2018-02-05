/*
   * @(#) LoadGridGUIController.java 1.1 2018/02/04
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP05.GUI.GameGUI;

import cs221.GP05.GUI.StartGUI.StartGUIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GameGUIController - A class that controls the GameGUI scene that is defined in GameGUI.fxml
 * <p>
 * Used with GameGUI.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1  DRAFT
 */
public class GameGUIController {
    /**
     * the parent StackPane in this scene.
     */
    @FXML
    StackPane parent;

    /**
     * The pause overlay
     */
    @FXML
    VBox pauseOverlay;

    /**
     * The main game screen content (cube I/O etc)
     */
    @FXML
    VBox gameScreen;

    /**
     * All the pause menu buttons
     */
    @FXML
    Button resumeBtn, exitBtn, helpBtn;


    /**
     * When the End Game button is clicked it will load the startGUI scene.
     *
     * todo display score and save options instead of startGUI
     * todo change to new 'end scene'
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see StartGUIController
     * @throws IOException if the StartGUI.fxml is not found.
     */
    @FXML
    void btnEndGameClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../StartGUI/StartGUI.fxml"));
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root,600,600));
        //todo call a method to stop the game running.
    }


    /**
     * When the pause button is clicked it will display an overlay and pause the game
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1 DRAFT
     */
    @FXML
    void btnPauseGameClicked(){

        // Disable game screen and show overlay
        pauseOverlay.setVisible(true);
        gameScreen.setDisable(true);
    }

    /**
     * When the resume button is pressed, remove overlay and enable game screen
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1 DRAFT
     */
    @FXML
    void btnResumeClicked(){

        // Enable game screen and hide overlay
        pauseOverlay.setVisible(false);
        gameScreen.setDisable(false);
    }

    /**
     * When the resume button is pressed, remove overlay and enable game screen
     * todo fix dodgy model overlaying
     * todo add prompt for exiting
     *
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1 DRAFT
     * @throws IOException if the StartGUI.fxml is not found.
     */
    @FXML
    void btnExitClicked() throws IOException{

        // Copied code right over from other method, will refine and change when I add a END Scene


        Parent root = FXMLLoader.load(getClass().getResource("../StartGUI/StartGUI.fxml"));
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root,600,600));
    }



}
