/*
   * @(#) LoadGridGUIController.java 1.0 2018/02/04
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
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    /**
     * List to store recently played cubes
     */
    @FXML
    private ListView<String> listViewRecents;


    /**
     * File to load the grid from
     */
    private File gridFile = null;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public LoadGridController(UIController UIController){
        super(UIController);
    }

    /**
     * Get recently played cubes from backend
     */
    public void prepView(){
        listViewRecents.setItems(UIController.getJoggleCube().getRecentGrids());
    }



    /**
     * When the Start Grid button is clicked it will load the Game scene.
     *
     * @see GameController
     */
    @FXML
    void btnStartGridClicked() {
        UIController.getJoggleCube().loadGrid(gridFile);
        UIController.getNavigationController().switchScreen(ScreenType.GAME);
    }

    /**
     * When the back button is clicked it will load the Start scene.
     *
     * @see StartController
     */
    @FXML
    private void btnBackClicked() {
        UIController.getNavigationController().switchScreen(ScreenType.START);
    }

    /**
     * When the Pick File button is clicked it opens a fileChooser.
     *
     */
    @FXML
    private void btnPickFileClicked() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("A file containing the date from a saved grid.",".grid"));
        gridFile = fileChooser.showOpenDialog(stage);
        if (gridFile != null) {
            //todo do more checks, check a valid gridFile etc
        } else {
            //todo add try again pop-up
        }
    }

    /**
     * Handle when a user clicks an option from the selection
     */
    @FXML
    public void handleMouseClick() {
        gridFile = new File(listViewRecents.getSelectionModel().getSelectedItem());
        if (gridFile != null) {
            //todo do more checks, check a valid gridFile etc
        } else {
            //todo add try again pop-up
        }
    }
}
