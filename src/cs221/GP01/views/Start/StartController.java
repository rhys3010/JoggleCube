/*
   * @(#) StartGUIController.java 1.1 2018/02/04
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.views.Start;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.Game.GameController;
import cs221.GP01.views.HighScore.HighScoreController;
import cs221.GP01.views.LoadGrid.LoadGridController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * StartController - A class that does something.
 * <p>
 * How it is used
 * todo add a Highscore button
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */

public class StartController {
    /**
     * The parent VBox in this scene.
     */
    @FXML
    private VBox parent;

    /**
     * a link to the backend
     */
    private JoggleCube joggleCube;

    /**
     * method to set a link to the backend
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see JoggleCube
     * @param joggleCube the backend
     */
    public void setJoggleCube(JoggleCube joggleCube){
        this.joggleCube = joggleCube;
    }


    /**
     * When the Start New Grid button is clicked it will load the Game scene with a new grid.
     *
     * @author Nathan Williams (naw21)
     * @version 0.2 DRAFT
     * @see GameController
     * @throws IOException if the Game.fxml is not found.
     */
    @FXML
    private void btnStartNewGridClicked() throws IOException {
        //load a game screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Game/Game.fxml"));
        Parent root = fxmlLoader.load();
        //get its controller
        GameController gameController = fxmlLoader.getController();

        //Tell the backend where to find the controller
        gameController.setJoggleCube(joggleCube);

        //generate a new random grid
        joggleCube.newRandomGrid(gameController);

        //Display the GUI
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }

    /**
     * When the Load Grid button is clicked it will load the LoadGrid scene.
     *
     * @author Nathan Williams (naw21)
     * @version 0.2 DRAFT
     * @see LoadGridController
     * @throws IOException if the LoadGrid.fxml is not found.
     */
    @FXML
    private void btnLoadGridClicked() throws IOException {
        //load a game screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../LoadGrid/LoadGrid.fxml"));
        Parent root = fxmlLoader.load();
        //get the controller
        LoadGridController loadGridController = fxmlLoader.getController();
        //Tell the backend where to find the controller and vise versa
        loadGridController.setJoggleCube(joggleCube);
        loadGridController.setListViewRecents(joggleCube.getRecentGrids());
        //display the GUI
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }

    /**
     * When the HighScore button is clicked it will load the Highscore scene.
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see HighScoreController
     * @throws IOException
     */
    public void btnHighScoreClicked() throws IOException{
        //load a game screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../HighScore/HighScore.fxml"));
        Parent root = fxmlLoader.load();
        //get the controller
        HighScoreController highScoreController = fxmlLoader.getController();
        //Tell the backend where to find the controller and vise versa
        highScoreController.setJoggleCube(joggleCube);
        //tells joggleCube to load
        joggleCube.getHighScores("overall",highScoreController);
        //display the GUI
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }
}