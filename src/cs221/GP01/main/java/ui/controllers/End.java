/*
   * @(#) EndController.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.UI;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

/**
 * End - A class that controls the Pause subscene that is defined in End.fxml
 * <p>
 * todo fix dodgy model overlaying
 * todo add prompt for exiting
 * Used with End.fxml
 *
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class End extends BaseOverlay implements INeedPrep {

    private static End endView;

    private End(){}

    public static End getInstance(){
        if(endView == null){
            synchronized (UI.class){
                if(endView == null){
                    endView = new End();
                }
            }
        }
        return endView;
    }

    @FXML
    Label scoreLabel,highScoreLabel;




    @Override
    public void prepView() {
        scoreLabel.setText(JoggleCube.getInstance().getScore() + "");
        highScoreLabel.setText(JoggleCube.getInstance().getHighestScore() + "");
        JoggleCube.getInstance().resetGameState();
    }


    /**
     * When the High Score button is pressed, change scene to high score screen
     * @see HighScore
     * @throws IOException - if FXML file could not be found/opened
     */
    @FXML
    public void btnHighScoreClicked()  {
        Navigation.getInstance().switchScreen(ScreenType.HIGH_SCORES);
        Navigation.getInstance().hideOverlay(ScreenType.END, parentController);
    }


    /**
     * When the 'return to menu' button is clicked change scene to menu scene
     * @see Start
     */
    @FXML
    public void btnMenuClicked(){
        Navigation.getInstance().switchScreen(ScreenType.START);
        Navigation.getInstance().hideOverlay(ScreenType.END, parentController);
    }

    /**
     * When the 'replay' button is clicked restart a game
     */
    @FXML
    public void btnReplayClicked() {
        Navigation.getInstance().switchScreen(ScreenType.GAME);
        Navigation.getInstance().hideOverlay(ScreenType.END, parentController);
    }

    /**
     * When the 'save' button is clicked prompt user to chose a save location
     */
    @FXML
    public void btnSaveClicked(){
        TextInputDialog dialog = new TextInputDialog("untitled");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Save Cube");
        dialog.setContentText("Please enter a filename:");
        dialog.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/cs221/GP01/main/resource/img/icon/save_icon_alt.png"))));
        dialog.initStyle(StageStyle.UNDECORATED);

        // get result from text box
        Optional<String> input = dialog.showAndWait();

        if(input.isPresent()){
            // Normalize input and save to regular java String
            String result = input.get().replace(" ", "");

            // todo: better validation
            if(result.matches("(\\w*)")){
                if(JoggleCube.getInstance().saveGrid(result)){

                }else{
                    dialog.setHeaderText("Error Saving File, Please try again");
                }
            }else{
                dialog.setHeaderText("Invalid Filename!, Please try again");
            }
        }
    }
}
