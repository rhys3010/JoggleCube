/*
   * @(#) LoadGridGUIController.java 1.1 2018/02/04
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.views.LoadGrid;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.Game.GameController;
import cs221.GP01.views.Start.StartController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LoadGridController - A class that controls the LoadGrid scene that is defined in LoadGrid.fxml
 * <p>
 * Used with LoadGrid.fxml
 *
 *
 * todo add a recents list
 *
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class LoadGridController {

    @FXML
    private ListView<String> listViewRecents;
    /**
     * the file a grid can be loaded from
     */

    public void setListViewRecents(ObservableList<String> items) {
        listViewRecents.setItems(items);
    }



    private File gridFile = null;

    /**
     * a link to the backend
     */
    private JoggleCube joggleCube;

    /**
     * method to set a link to the backend
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see JoggleCube
     * @param joggleCube the backend
     */
    public void setJoggleCube(JoggleCube joggleCube) {
        this.joggleCube = joggleCube;
    }

    /**
     * the parent VBox in this scene.
     */
    @FXML
    VBox parent;

    /**
     * When the Start Grid button is clicked it will load the Game scene.
     *
     * @author Nathan Williams (naw21)
     * @version 0.2 DRAFT
     * @see GameController
     * @throws IOException if the Game.fxml is not found.
     */
    @FXML
    void btnStartGridClicked() throws IOException {
        //create an fxmlLoader with a game screen and launch it
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Game/Game.fxml"));
        Parent root = fxmlLoader.load();
        //retrive the controller
        GameController gameController = fxmlLoader.getController();
        //tell the controller about the backend
        gameController.setJoggleCube(joggleCube);


        //todo add a to check a file has been loaded in.
        joggleCube.loadGrid(gameController,gridFile);

        //display the gui
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }

    /**
     * When the back button is clicked it will load the Start scene.
     *
     * @author Nathan Williams (naw21)
     * @version 0.2 DRAFT
     * @see StartController
     * @throws IOException if the Start.fxml is not found.
     */
    @FXML
    void btnBackClicked() throws IOException {
        //create an fxmlLoader with a game screen and launch it
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Start/Start.fxml"));
        Parent root = fxmlLoader.load();
        //retrive the controller
        StartController startController = fxmlLoader.getController();
        //tell the controller about the backend
        startController.setJoggleCube(joggleCube);
        //display the gui
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
        gridFile = fileChooser.showOpenDialog(stage);
        if (gridFile != null) {
            //todo do more checks, check a valid gridFile etc
        } else {
            //todo add try again pop-up
        }
    }

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
