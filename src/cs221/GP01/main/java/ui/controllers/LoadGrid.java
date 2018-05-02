/*
   * @(#) LoadGridGUIController.java 1.0 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.Dialog;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * LoadGrid - A class that controls the LoadGrid scene that is defined in Load.fxml
 * <p>
 * Used with Load.fxml
 *
 *
 * todo add a recents list
 *
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class LoadGrid extends BaseScreen implements INeedPrep {


    private static LoadGrid loadGridView;

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

    public String getFileName() {
        return fileName;
    }

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
        if(JoggleCube.getInstance().loadGrid(fileName)){
            Navigation.getInstance().switchScreen(ScreenType.GAME);
        } else {
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

        // Detect double click
        if(event.getClickCount() > 1){
            btnStartGridClicked();
        }
    }
}
