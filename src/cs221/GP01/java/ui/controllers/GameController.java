/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.UIController;
import cs221.GP01.java.ui.ScreenType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.PickResult;
import javafx.scene.layout.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GameController - A class that controls the Game scene that is defined in Game.fxml
 * <p>
 * Used with Game.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class GameController extends BaseScreenController implements Initializable{

    private GridDisplayer gridDisplayer;
    /**
     * The tab pane that contains the cube representations
     */
    @FXML
    private TabPane cubeContainer;

    @FXML
    private ListView<String> foundWordsList;

    private ObservableList<String> foundWords = FXCollections.observableArrayList();


    @FXML
    private Button btnSubmit;

    @FXML
    private TextField textField;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public GameController(UIController UIController){
        super(UIController);
    }


    @FXML
    private void btnClearClicked() {
        textField.setText("");
        gridDisplayer.setAllActive();
    }


    @FXML
    private void btnSubmitClicked() {
        if(!textField.getText().equals("")) {
            if (UIController.getJoggleCube().testWordValidity(textField.getText())) {
                foundWords.add(textField.getText());
                textField.setText("");
                gridDisplayer.setAllActive();
                //todo make these css variables
                btnSubmit.setStyle("-fx-background-color:#006600;");
            } else {
                btnSubmit.setStyle("-fx-background-color:#880000;");
            }
        } else {
            btnSubmit.setStyle("-fx-background-color:#880000;");
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        btnSubmit.setStyle("-fx-background-color:-fx-tertiary-color;");
                    }
                },
                1000
        );
    }

    //todo add method to change the submit buttons colour back when the the mouse is next clicked maybe?


    /**
     * Handle the settings button being clicked
     */
    @FXML
    private void btnSettingsClicked(){
        UIController.getNavigationController().showOverlay(ScreenType.SETTINGS, this);
    }

    /**
     * When the End Game button is clicked it will load the EndGui scene.
     */
    @FXML
    private void btnEndGameClicked() {
        UIController.getNavigationController().showOverlay(ScreenType.END, this);
        UIController.getJoggleCube().endGame();
    }

    @FXML
    private GridPane top2d,middle2d,bottom2d,top25d,middle25d,bottom25d;
    @FXML
    private SubScene subScene;
    @FXML
    private Group groupy;
    @FXML
    private BorderPane back;

    /**
     * Initialize game screen
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        GridPane[] twoDGrid = {top2d, middle2d, bottom2d};
        GridPane[] twoFiveDGrid = {top25d, middle25d, bottom25d};
        gridDisplayer = new GridDisplayer(textField,twoDGrid,twoFiveDGrid,subScene,groupy,back);
        gridDisplayer.buildGrids(UIController.getJoggleCube().getCubeData());
        foundWordsList.setItems(foundWords);
    }


    /**
     * Get the cube container tab pane
     * @return cubeContainer - the FXML node for tabpane
     */
    public TabPane getCubeContainer(){
        return cubeContainer;
    }
}
