/*
   * @(#) LoadGridGUIController.java 1.0 2018/02/04
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LoadGridController - A class that controls the LoadGrid scene that is defined in Load.fxml
 * <p>
 * Used with Load.fxml
 *
 *
 * todo add a recents list
 *
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class LoadGridController extends BaseScreenController implements INeedPrep {


    private static LoadGridController loadGridController;

    private LoadGridController(){}

    public static LoadGridController getInstance(){
        if(loadGridController == null){
            synchronized (LoadGridController.class){
                if(loadGridController == null){
                    loadGridController = new LoadGridController();
                }
            }
        }
        return loadGridController;
    }

    /**
     * List to store recently played cubes
     */
    @FXML
    private ListView<String> listViewRecents;


    /**
     * File to load the grid from
     */
    private String fileName = null;


    /**
     * Get recently played cubes from backend
     */
    public void prepView(){
        listViewRecents.setItems(JoggleCubeController.getInstance().getRecentGrids());
    }



    /**
     * When the Start Grid button is clicked it will load the Game scene.
     *
     * @see GameController
     */
    @FXML
    void btnStartGridClicked() {
        if(JoggleCubeController.getInstance().loadGrid(fileName)){
            NavigationController.getInstance().switchScreen(ScreenType.GAME);
        } else {
            //todo file not loaded message
        }


    }

    /**
     * When the back button is clicked it will load the Start scene.
     *
     * @see StartController
     */
    @FXML
    private void btnBackClicked() {
        NavigationController.getInstance().switchScreen(ScreenType.START);
    }


    /**
     * Handle when a user clicks an option from the selection
     */
    @FXML
    public void handleMouseClick() {

        fileName = listViewRecents.getSelectionModel().getSelectedItem();
    }

    /**
     * Error handling method to show the user an error alert when something goes wrong with file loading
     * @param message - the error message to display on the popup
     */
    public void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Error Loading File: \n\n" + message);

        alert.showAndWait();
    }
}
