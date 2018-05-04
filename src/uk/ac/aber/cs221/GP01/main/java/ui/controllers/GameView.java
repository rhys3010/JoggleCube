/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package uk.ac.aber.cs221.GP01.main.java.ui.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.Dialog;
import uk.ac.aber.cs221.GP01.main.java.ui.*;

import java.util.Optional;

/**
 * GameView - A class that controls the Game scene that is defined in Game.fxml
 * Used with Game.fxml
 * This controller controller the game view it contains the timer update methods, score update methods and runs the front end logic changing the colours of the grid with the help of GridDisplayer
 *
 * @see GridDisplayer
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Samuel Jones - srj12@aber.ac.uk
 * @version 1.1
 */
public class GameView extends BaseScreen implements IGame, INeedPrep {

    // an Instance of this controller
    private static GameView gameController;

    /**
     *  a private constructor so the class can't be instantiated by any other class.
     */
    private GameView() {}
    // holds the grid displayer currently in use to help display the grids
    private GridDisplayer gridDisplayer;

    // holds an instance of the custom dialog class that handles the dialogs for us.
    private Dialog dialog;

    @FXML
    private TabPane cubeContainer;

    @FXML
    private Label scoreLabel, timerLabel;

    private ObservableList<String> foundWords;

    @FXML
    private Button btnSubmit, menuButton, explodeIcon, colorBlindIcon;

    @FXML
    private TextField textField;

    @FXML
    private GridPane top2d, middle2d, bottom2d, top25d, middle25d, bottom25d;

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

    @FXML
    public void btnClearClicked() {
        textField.setText("");
        gridDisplayer.setAllActive();
    }

    /**
     * handles the submit button begin clicked
     */
    @FXML
    public void btnSubmitClicked() {
        if (!textField.getText().equals("") && JoggleCube.getInstance().testWordValidity(textField.getText())) {
            foundWords.add(textField.getText());
            btnSubmit.setStyle("-fx-background-color: -fx-valid-color;");
            textField.setStyle("-fx-background-color: -fx-valid-color; -fx-text-fill: white;");
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            btnSubmit.setStyle("-fx-background-color:-fx-tertiary-color;");
                            textField.setStyle("-fx-background-color: white; -fx-text-fill: -fx-tertiary-color;");
                            Platform.runLater(() -> gridDisplayer.setAllActive());
                            textField.setText(""); //todo hmmmmmmmmmm
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
    public void btnMenuClicked() {
        // Get the coordinates of the menu button
        Point2D screenPos = menuButton.localToScreen(menuButton.getLayoutX(), menuButton.getLayoutY());

        // Show the context menu at the X, Y co-ordinates with an offset
        hamburgerContext.show(menuButton, screenPos.getX() - 100, screenPos.getY() + 20);
    }

    /**
     * Handles the explode button being clicked
     */
    @FXML
    public void btnExplodeClicked() {
        gridDisplayer.toggleExplode();
    }

    /**
     * When the End Game option is clicked it will load the EndGui overlay.
     */
    @FXML
    public void btnEndGameClicked() {
        // Display 'are you sure?' overlay for quitting
        dialog = new Dialog();
        Optional<ButtonType> result = dialog.showConfirmationDialog("Quit Game", "Are you sure you want to quit the game?");

        if(result.get() != ButtonType.OK){
            // Do nothing
        }else{
            // End the game
            JoggleCube.getInstance().interruptTimer();
            Navigation.getInstance().showOverlay(ScreenType.END, this);
        }
    }

    /**
     * Initialize game screen
     */
    @Override
    public void prepView() {
        scoreLabel.setText("0");
        timerLabel.setText("3:00");
        textField.setText("");
        timerLabel.setStyle("-fx-text-fill: white;");

        // Pop-up dialog to get user's name
        dialog = new Dialog();
        String result = dialog.showInputDialog("Name Input", "Please enter your name:", "Walter", new ImageView(new Image(getClass().getResourceAsStream("/uk/ac/aber/cs221/GP01/main/resource/img/icon/person_icon.png"))), false);

        // Normalize input
        result = result.replace(" ", "");

        if (dialog.isValidInput(result)) {
            JoggleCube.getInstance().setName(result);
        }

        foundWords = FXCollections.observableArrayList();

        // Disable hamburger context on right click
        menuButton.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

        // Show colorblind mode if enabled
        colorBlindIcon.setVisible(Settings.getInstance().isColorBlindEnabled());

        GridPane[] twoDGrid = {top2d, middle2d, bottom2d};
        GridPane[] twoFiveDGrid = {top25d, middle25d, bottom25d};
        gridDisplayer = new GridDisplayer(textField, twoDGrid, twoFiveDGrid, subScene, groupy, back, explodeIcon);
        gridDisplayer.buildGrids(JoggleCube.getInstance().getCubeData());
        foundWordsList.setItems(foundWords);

        JoggleCube.getInstance().startTimer();
    }

    /**
     * Getting instance of gameController
     *
     * @return returns the instance of this class object.
     */
    public static GameView getInstance() {
        if (gameController == null) {
            synchronized (UI.class) {
                if (gameController == null) {
                    gameController = new GameView();
                }
            }
        }
        return gameController;
    }

    /**
     * Returns the list of found words so it can be used in the backend as it is currently only stored in the frontend
     *
     * @return returns an observableList of strings of all currently found words
     * @author Samuel Jones - srj12
     */
    @Override
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }

    /**
     * Gets timer label FXML node
     *
     * @return timerLabel
     */
    @Override
    public Label getTimerLabel() {
        return timerLabel;
    }

    /**
     * Gets the dialog object to prompt user for name
     *
     * @return dialog
     */
    public Dialog getDialog() {
        return dialog;
    }

    /**
     * Gets the score label FXML node
     *
     * @return scoreLabel
     */
    @Override
    public Label getScoreLabel() {
        return scoreLabel;
    }

    /**
     * Gets cubeContainer FXML node
     *
     * @return cubeContainer
     */
    @Override
    public TabPane getCubeContainer() {
        return cubeContainer;
    }

    /**
     * Gets text within textField
     *
     * @return textField
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Gets gridDisplayer object to draw grid
     *
     * @return gridDisplayer
     */
    public GridDisplayer getGridDisplayer() {
        return gridDisplayer;
    }

    /**
     * Gets hamburger context menu FXML node
     *
     * @return hamburger Icon
     */
    public ContextMenu getHamburgerContext() {
        return hamburgerContext;
    }

    /**
     * Gets menuButton FXML node
     *
     * @return menuButton
     */
    public Button getMenuButton() {
        return menuButton;
    }

    /**
     * Gets color blind icon
     *
     * @return coloBlindIcon
     */
    public Button getColorBlindIcon(){ return colorBlindIcon; }

    /**
     * Sets string of text in to the textField variable
     *
     * @param text string to be set
     */
    public void setText(String text) {
        textField.setText(text);
    }

    /**
     * Displays time left in game
     *
     * @param timerLabel
     */
    public void setTimerLabel(Label timerLabel) {
        this.timerLabel = timerLabel;
    }
}
