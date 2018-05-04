/*
   * @(#) LoadGridGUIController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package uk.ac.aber.cs221.GP01.main.java.ui.controllers;

import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.Dialog;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * LoadGrid - A class that controls the LoadGrid scene that is defined in Load.fxml
 * Used with Load.fxm
 *
 * @author Nathan Williams (naw21)
 * @version 1.1
 */
public class LoadGrid extends BaseScreen implements INeedPrep {

    private static LoadGrid loadGridView;
    private boolean fileLoaded = false;

    private LoadGrid(){}

    public static LoadGrid getInstance(){
        if(loadGridView == null){
            synchronized (LoadGrid.class){
                if(loadGridView == null){
                    loadGridView = new LoadGrid();
                }
            }
        }
        return loadGridView;
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
        listViewRecents.setItems(JoggleCube.getInstance().getRecentGrids());
    }

    /**
     * When the Start Grid button is clicked it will load the Game scene.
     *
     * @see GameView
     */
    @FXML
    public void btnStartGridClicked() {

        if(fileLoaded){
            // Switch the screen
            Navigation.getInstance().switchScreen(ScreenType.GAME);
        }else{
            Dialog dialog = new Dialog();
            dialog.showInformationDialog("Error", "No File Selected, Please Try Again");
        }
    }

    /**
     * When the back button is clicked it will load the Start scene.
     *
     * @see Start
     */
    @FXML
    public void btnBackClicked() {
        Navigation.getInstance().switchScreen(ScreenType.START);
    }

    /**
     * Handle when a user clicks an option from the selection
     */
    @FXML
    public void handleMouseClick(MouseEvent event) {

        fileName = listViewRecents.getSelectionModel().getSelectedItem();
        loadGrid();

        // Detect double click
        if(event.getClickCount() > 1){
            btnStartGridClicked();
        }
    }

    /**
     * Utility function to load the grid into the game
     */
    private void loadGrid(){
        if(JoggleCube.getInstance().loadGrid(fileName)){
            fileLoaded = true;
        }else{
            fileLoaded = false;
            Dialog dialog = new Dialog();
            dialog.showInformationDialog("Error", "No File Selected, Please Try Again");
        }
    }

    /**
     * Get fileName
     *
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }
}
