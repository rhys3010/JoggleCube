/*
   * @(#) StartGUIController.java 1.1 2018/02/04
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP05.GUI.StartGUI;

import cs221.GP05.GUI.GameGUI.GameGUIController;
import cs221.GP05.GUI.LoadGridGUI.LoadGridGUIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * SomeClass - A class that does something.
 * <p>
 * How it is used
 *
 * @author Nathan Williams (naw21)
 * @version 0.1  DRAFT
 */

public class StartGUIController {
    /**
     * The parent VBox in this scene.
     */
    @FXML
    VBox parent;

    /**
     * When the Start New Grid button is clicked it will load the GameGUI scene with a new grid.
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see GameGUIController
     * @throws IOException if the GameGUI.fxml is not found.
     */
    @FXML
    void btnStartNewGridClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GameGUI/GameGUI.fxml"));
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
        //todo add a method call to create a new random grid
    }

    /**
     * When the Load Grid button is clicked it will load the LoadGridGUI scene.
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see LoadGridGUIController
     * @throws IOException if the LoadGridGUI.fxml is not found.
     */
    @FXML
    void btnLoadGridClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../LoadGridGUI/LoadGridGUI.fxml"));
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }
}