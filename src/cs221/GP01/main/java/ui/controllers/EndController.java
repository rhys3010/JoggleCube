/*
   * @(#) EndController.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.model.JoggleCubeController;
import cs221.GP01.main.java.ui.NavigationController;
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

    private static EndController endController;

    private EndController(){}

    public static EndController getInstance(){
        if(endController == null){
            synchronized (UIController.class){
                if(endController == null){
                    endController = new EndController();
                }
            }
        }
        return endController;
    }

    @FXML
    Label scoreLabel,highScoreLabel;




    @Override
    public void prepView() {
        scoreLabel.setText(JoggleCubeController.getInstance().getScore() + "");
        highScoreLabel.setText(JoggleCubeController.getInstance().getHighScore() + "");
    }


    /**
     * When the High Score button is pressed, change scene to high score screen
     * @see HighScoreController
     * @throws IOException - if FXML file could not be found/opened
     */
    @FXML
    void btnHighScoreClicked()  {
        NavigationController.getInstance().switchScreen(ScreenType.HIGH_SCORES);
        NavigationController.getInstance().hideOverlay(ScreenType.END, parentController);
    }


    /**
     * When the 'return to menu' button is clicked change scene to menu scene
     * @see StartController
     */
    @FXML
    void btnMenuClicked(){
        NavigationController.getInstance().switchScreen(ScreenType.START);
        NavigationController.getInstance().hideOverlay(ScreenType.END, parentController);
    }

    /**
     * When the 'replay' button is clicked restart a game
     */
    @FXML
    void btnReplayClicked() {
        NavigationController.getInstance().switchScreen(ScreenType.GAME);
        NavigationController.getInstance().hideOverlay(ScreenType.END, parentController);
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
            JoggleCubeController.getInstance().saveGrid(file);
        } else {
            //todo add try again pop-up
        }
    }
}
