/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.views.Game;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.End.EndController;
import cs221.GP01.views.Pause.PauseController;
import cs221.GP01.views.Start.StartController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GameController - A class that controls the Game scene that is defined in Game.fxml
 * <p>
 * Used with Game.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class GameController {
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
     * a link to the backend
     */
    private JoggleCube joggleCube;

    /**
     * method to set a link to the backend
     *
     * @see JoggleCube
     * @param joggleCube the backend
     */
    public void setJoggleCube(JoggleCube joggleCube) {
        this.joggleCube = joggleCube;
    }

    /**
     * When the End Game button is clicked it will load the EndGui scene.
     *
     * @author Nathan Williams (naw21)
     * @see StartController
     * @throws IOException if the HighScore.fxml is not found.
     */
    @FXML
    private void btnEndGameClicked() throws IOException {

        // Load End-Game overlay
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../End/End.fxml"));
        VBox overlay = fxmlLoader.load();

        // Pass controller and JoggleCube instance
        EndController endController = fxmlLoader.getController();
        joggleCube.endGame(endController);
        endController.setJoggleCube(joggleCube);

        // Disable the game
        gameScreen.setDisable(true);

        // Add to the stackpane
        parent.getChildren().add(overlay);
    }


    /**
     * When the pause button is clicked it will display an overlay and pause the game
     * todo consider efficiency of injecting the overlay's fxml when button is pressed (maybe do this at the start?)AFT
     * @throws IOException if End.fxml doesn't exist
     */
    @FXML
    private void btnPauseGameClicked() throws IOException{
        // Load pause overlay
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Pause/Pause.fxml"));
        VBox overlay = fxmlLoader.load();

        // Get Game controller
        PauseController pauseController = fxmlLoader.getController();
        pauseController.setJoggleCube(joggleCube);
        pauseController.setGameController(this);

        // Disable background
        gameScreen.setDisable(true);

        joggleCube.pause();
        // Add overlay to the stackpane as child
        parent.getChildren().add(overlay);
    }


    /**
     * Re-enables the game screen after game is resumed
     */
    public void setScreenDisabled(Boolean status){
        gameScreen.setDisable(status);

    }
}
