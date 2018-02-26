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
import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;


/**
 * GameController - A class that controls the Game scene that is defined in Game.fxml
 * <p>
 * Used with Game.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Samuel Jones - srj12@aber.ac.uk
 * @version 0.2  DRAFT
 */
public class GameController extends BaseScreenController implements IGameController, INeedPrep {

    private GridDisplayer gridDisplayer;

    @FXML
    private TabPane cubeContainer;

    @FXML
    private ListView<String> foundWordsList;

    @FXML
    private Label scoreLabel, timerLabel;

    private ObservableList<String> foundWords = FXCollections.observableArrayList();


    @FXML
    private Button btnSubmit;

    @FXML
    private TextField textField;

    @FXML
    private GridPane top2d,middle2d,bottom2d,top25d,middle25d,bottom25d;
    @FXML
    private SubScene subScene;
    @FXML
    private Group groupy;
    @FXML
    private BorderPane back;

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
                btnSubmit.setStyle("-fx-background-color: -fx-valid-color;");
            } else {
                btnSubmit.setStyle("-fx-background-color: -fx-invalid-color;");
            }
        } else {
            btnSubmit.setStyle("-fx-background-color: -fx-invalid-color;");
            textField.setStyle("-fx-background-color: -fx-invalid-color;");
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        btnSubmit.setStyle("-fx-background-color:-fx-tertiary-color;");
                        textField.setStyle("-fx-background-color: white;");
                    }
                },
                1000
        );
    }

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
        //todo stop timer add up score etc
        UIController.getNavigationController().showOverlay(ScreenType.END, this);
    }


    /**
     * Initialize game screen
     */
    @Override
    public void prepView(){
        GridPane[] twoDGrid = {top2d, middle2d, bottom2d};
        GridPane[] twoFiveDGrid = {top25d, middle25d, bottom25d};
        gridDisplayer = new GridDisplayer(textField,twoDGrid,twoFiveDGrid,subScene,groupy,back);
        gridDisplayer.buildGrids(UIController.getJoggleCube().getCubeData());
        foundWordsList.setItems(foundWords);
    }

    @Override
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }

    @Override
    public Label getTimerLabel() {
        return timerLabel;
    }

    @Override
    public Label getScoreLabel() {
        return scoreLabel;
    }

    @Override
    public TabPane getCubeContainer() {
        return cubeContainer;
    }


    /**
     * Returns the list of found words so it can be used in the backend as it is currently only stored in the frontend
     * @author Samuel Jones - srj12
     * @return returns an observableList of strings of all currently found words
     */
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }


    /**
     * Returns the list of found words so it can be used in the backend as it is currently only stored in the frontend
     * @author Samuel Jones - srj12
     * @return returns an observableList of strings of all currently found words
     */
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }


    /**
     * Returns the list of found words so it can be used in the backend as it is currently only stored in the frontend
     * @author Samuel Jones - srj12
     * @return returns an observableList of strings of all currently found words
     */
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }


    /**
     * Returns the list of found words so it can be used in the backend as it is currently only stored in the frontend
     * @author Samuel Jones - srj12
     * @return returns an observableList of strings of all currently found words
     */
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }
}
