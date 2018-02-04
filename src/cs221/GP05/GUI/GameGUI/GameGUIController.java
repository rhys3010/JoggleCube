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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GameGUIController - A class that controls the GameGUI scene that is defined in GameGUI.fxml
 * <p>
 * Used with GameGUI.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @version 0.1  DRAFT
 */
public class GameGUIController {
    /**
     * the parent VBox in this scene.
     */
    @FXML
    VBox parent;

    /**
     * When the End Game button is clicked it will load the startGUI scene.
     *
     * todo display score and save options instead of startGUI
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
}
