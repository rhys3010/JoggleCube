/*
   * @(#) PauseController.java 1.0 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.views.Pause;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.Game.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
public class PauseController {

    /**
     * The pause overlay
     */
    @FXML
    VBox pauseOverlay;

    /**
     * a link to the backend
     */
    private JoggleCube joggleCube;

    /**
     * The game controller
     */
    private GameController gameController;

    /**
     * method to set a link to the backend
     *
     * @see JoggleCube
     * @param joggleCube the backend
     */
    public void setJoggleCube(JoggleCube joggleCube){
        this.joggleCube = joggleCube;
    }

    /**
     * When the resume button is pressed, remove overlay and resume the game.
     * @see GameController
     * @throws IOException
     */
    @FXML
    void btnResumeClicked() throws IOException{
        // Remove pause overlay
        pauseOverlay.setVisible(false);

        // Enable background scene
        gameController.setScreenDisabled(false);

        joggleCube.resume();
    }

    /**
     * When the exit button is pressed, go to home screen.
     * todo add prompt?
     */
    @FXML
    void btnExitClicked(){


    }

    /**
     * Retrieve and store the game controller to get access to its elements
     * @see GameController
     * @param gameController
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
