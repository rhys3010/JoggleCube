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
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;



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
    private Label scoreLabel, timerLabel;

    private ObservableList<String> foundWords;


    @FXML
    private Button btnSubmit, menuButton;

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

    @FXML
    private ListView<String> foundWordsList;

    /**
     * The Context menu at the top right of the screen
     */
    @FXML
    private ContextMenu hamburgerContext;


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
        if (!textField.getText().equals("") && UIController.getJoggleCube().testWordValidity(textField.getText())) {
            foundWords.add(textField.getText());
            btnSubmit.setStyle("-fx-background-color: -fx-valid-color;");
            textField.setStyle("-fx-background-color: -fx-valid-color; -fx-text-fill: white;");
            gridDisplayer.setAllActive();
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            btnSubmit.setStyle("-fx-background-color:-fx-tertiary-color;");
                            textField.setStyle("-fx-background-color: white; -fx-text-fill: -fx-tertiary-color;");
                            textField.setText(""); //todo hmmmmmm
                        }
                    },
                    1000
            );
        } else {
            btnSubmit.setStyle("-fx-background-color: -fx-invalid-color;");
            textField.setStyle("-fx-background-color: -fx-invalid-color; -fx-text-fill: white;");
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            btnSubmit.setStyle("-fx-background-color:-fx-tertiary-color;");
                            textField.setStyle("-fx-background-color: white; -fx-text-fill: -fx-tertiary-color;");
                        }
                    },
                    1000
            );
        }
    }

    /**
     * Handles the hamburger menu being clicked
     */
    @FXML
    private void btnMenuClicked() {
        // Get the coordinates of the menu button
        Point2D screenPos = menuButton.localToScreen(menuButton.getLayoutX(), menuButton.getLayoutY());

        // Show the context menu at the X, Y co-ordinates with an offset
        hamburgerContext.show(menuButton, screenPos.getX()-100, screenPos.getY()+20);
    }

    /**
     * Handle the settings option from the hamburger context menu is being clicked
     */
    @FXML
    private void btnSettingsClicked(){
        UIController.getNavigationController().showOverlay(ScreenType.SETTINGS, this);
    }

    /**
     * When the End Game option is clicked it will load the EndGui scene.
     */
    @FXML
    private void btnEndGameClicked() {
        UIController.getJoggleCube().interruptTimer();
        UIController.getNavigationController().showOverlay(ScreenType.END, this);
    }

    /**
     * When the help option is clicked it will open the help overlay
     */
    @FXML
    private void btnHelpClicked(){
        UIController.getNavigationController().showOverlay(ScreenType.HELP, this);
    }


    /**
     * Initialize game screen
     */
    @Override
    public void prepView(){

        TextInputDialog dialog = new TextInputDialog("Walter");
        dialog.setTitle("Enter Name");
        dialog.setHeaderText("Name Input");
        dialog.setContentText("Please enter your name:");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        // todo: make this the correct icon
        // todo: make this call using proper URI to allow for those dodgy PCs
        dialog.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../../../resource/img/icon/person_icon.png"))));
        dialog.initStyle(StageStyle.UNDECORATED);


        boolean done = false;
        // todo: add more in-depth validation checking
        while(!done) {
            dialog.showAndWait();
            // Remove spaces from input
            String result = dialog.getResult().replace(" ", "");

            if (result.matches("(\\w*)")) {
                UIController.getJoggleCube().setName(result);
                done = true;
            } else {
                dialog.setHeaderText("Invalid Name Entry, Please try again");
            }
        }

        foundWords = FXCollections.observableArrayList();


        // Disable hamburger context on right click
        menuButton.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

        GridPane[] twoDGrid = {top2d, middle2d, bottom2d};
        GridPane[] twoFiveDGrid = {top25d, middle25d, bottom25d};
        gridDisplayer = new GridDisplayer(textField,twoDGrid,twoFiveDGrid,subScene,groupy,back);
        gridDisplayer.buildGrids(UIController.getJoggleCube().getCubeData());
        foundWordsList.setItems(foundWords);


       UIController.getJoggleCube().startTimer();
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
    @Override
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }
}
