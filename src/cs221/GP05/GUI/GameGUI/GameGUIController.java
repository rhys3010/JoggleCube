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
     * The main game screen content (cube I/O etc)
     */
    @FXML
    VBox gameScreen;


    /**
     * When the End Game button is clicked it will load the EndGui scene.
     *
     * @author Nathan Williams (naw21)
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1 DRAFT
     * @see StartGUIController
     * @throws IOException if the EndGUI.fxml is not found.
     */
    @FXML
    void btnEndGameClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../EndGUI/EndGUI.fxml"));
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root,600,450));
        //todo call a method to stop the game running.
    }


    /**
     * When the pause button is clicked it will display an overlay and pause the game
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * todo consider efficiency of injecting the overlay's fxml when button is pressed (maybe do this at the start?)
     * @version 0.1 DRAFT
     * @throws IOException if PauseGUI.fxml doesn't exist
     */
    @FXML
    void btnPauseGameClicked() throws IOException{

        // Load Pause FXML
        VBox pauseOverlay = FXMLLoader.load(getClass().getResource("../PauseGUI/PauseGUI.fxml"));

        // Add overlay to the stackpane as child
        parent.getChildren().add(pauseOverlay);
    }
}
