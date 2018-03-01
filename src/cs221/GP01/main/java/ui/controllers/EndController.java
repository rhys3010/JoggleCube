/*
   * @(#) EndController.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
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
public class EndController extends BaseOverlayController implements INeedPrep {

    @FXML
    Label scoreLabel,highScoreLabel;



    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public EndController(UIController UIController){
        super(UIController);
    }


    @Override
    public void prepView() {
        scoreLabel.setText(UIController.getJoggleCube().getScore() + "");
        highScoreLabel.setText(UIController.getJoggleCube().getHighScore() + "");
    }


    /**
     * When the High Score button is pressed, change scene to high score screen
     * @see HighScoreController
     * @throws IOException - if FXML file could not be found/opened
     */
    @FXML
    void btnHighScoreClicked()  {

        UIController.getNavigationController().switchScreen(ScreenType.HIGH_SCORES);
        UIController.getNavigationController().hideOverlay(ScreenType.END, parentController);
    }


    /**
     * When the 'return to menu' button is clicked change scene to menu scene
     * @see StartController
     */
    @FXML
    void btnMenuClicked(){
        UIController.getNavigationController().switchScreen(ScreenType.START);
        UIController.getNavigationController().hideOverlay(ScreenType.END, parentController);
    }

    /**
     * When the 'replay' button is clicked restart a game
     */
    @FXML
    void btnReplayClicked() {
        UIController.getNavigationController().switchScreen(ScreenType.GAME);
        UIController.getNavigationController().hideOverlay(ScreenType.END, parentController);
    }

    /**
     * When the 'save' button is clicked prompt user to chose a save location
     */
    @FXML
    void btnSaveClicked(){
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Cube");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("A file containing grid data",".grid"));
        File file = fileChooser.showSaveDialog(newStage);
        if (file != null) {
            //todo get the users name
            String name = "bob";
            UIController.getJoggleCube().saveGrid(file,name);
        } else {
            //todo add try again pop-up
        }
    }
}
