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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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
        highScoreLabel.setText(JoggleCubeController.getInstance().getHighestScore() + "");
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
        Stage stage = new Stage();
        TextInputDialog dialog = new TextInputDialog("untitled");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter a filename:");

        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        // todo: make this the correct icon
        // todo: make this call using proper URI to allow for those dodgy PCs
        dialog.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../../../resource/img/icon/person_icon.png"))));
        dialog.initStyle(StageStyle.UNDECORATED);
        boolean done = false;
        // todo: add more in-depth validation checking
        while(!done) {
            dialog.showAndWait();
            // Remove spaces from input
            String result = dialog.getResult().replace(" ", "");

            if (result.matches("(\\w*)")) {
                if(JoggleCubeController.getInstance().saveGrid(result)){
                    //confirm file has been saved.
                } else {
                    //tell user to try saving again with a different file name.
                };

                done = true;
            } else {
                dialog.setHeaderText("Invalid Name Entry, Please try again");
            }
        }
    }
}
