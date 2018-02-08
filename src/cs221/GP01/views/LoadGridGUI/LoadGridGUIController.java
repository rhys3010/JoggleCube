/*
   * @(#) LoadGridGUIController.java 1.1 2018/02/04
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.views.LoadGridGUI;

import cs221.GP01.views.GameGUI.GameGUIController;
import cs221.GP01.views.StartGUI.StartGUIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * LoadGridGUIController - A class that controls the LoadGridGUI scene that is defined in LoadGridGUI.fxml
 * <p>
 * Used with LoadGridGUI.fxml
 *
 * @author Nathan Williams (naw21)
 * @version 0.1  DRAFT
 */
public class LoadGridGUIController {
    /**
     * the parent VBox in this scene.
     */
    @FXML
    VBox parent;

    /**
     * When the Start Grid button is clicked it will load the GameGUI scene.
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see GameGUIController
     * @throws IOException if the GameGUI.fxml is not found.
     */
    @FXML
    void btnStartGridClicked() throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("../GameGUI/GameGUI.fxml"));
            Stage stage = (Stage) parent.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 600));
            //todo add a to check a file has been loaded in.
    }

    /**
     * When the back button is clicked it will load the StartGUI scene.
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see StartGUIController
     * @throws IOException if the StartGUI.fxml is not found.
     */
    @FXML
    void btnBackClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../StartGUI/StartGUI.fxml"));
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }

    /**
     * When the Pick File button is clicked it opens a fileChooser.
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     */
    @FXML
    void btnPickFileClicked() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            //openFile(file);
            //todo do more stuff, check a valid file etc
        } else {
            //todo add try again pop-up
        }
    }
}
