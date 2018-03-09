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
    private File gridFile = null;


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
        JoggleCubeController.getInstance().loadGrid(gridFile);
        NavigationController.getInstance().switchScreen(ScreenType.GAME);
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
     * When the Pick File button is clicked it opens a fileChooser.
     *
     */
    @FXML
    private void btnPickFileClicked() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".grid Files","*.grid"));

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
