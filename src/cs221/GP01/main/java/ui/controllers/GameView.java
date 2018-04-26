/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.UI;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
import javafx.scene.layout.*;
import javafx.stage.StageStyle;

import java.util.Optional;


/**
 * GameView - A class that controls the Game scene that is defined in Game.fxml
 * <p>
 * Used with Game.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Samuel Jones - srj12@aber.ac.uk
 * @version 0.2  DRAFT
 */
public class GameView extends BaseScreen implements IGame, INeedPrep {

    private static GameView gameController;

    private GameView() {
    }

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

    private GridDisplayer gridDisplayer;

    @FXML
    private TabPane cubeContainer;


    @FXML
    private Label scoreLabel, timerLabel;

    private ObservableList<String> foundWords;


    @FXML
    private Button btnSubmit, menuButton, explodeIcon;

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
     * When the End Game option is clicked it will load the EndGui scene.
     */
    @FXML
    public void btnEndGameClicked() {
        // Display 'are you sure' overlay
        Alert sureAlert = new Alert(Alert.AlertType.CONFIRMATION);
        sureAlert.setTitle("Quit Game");
        sureAlert.setHeaderText(null);
        sureAlert.setContentText("Are you sure you want to quit the current game?");
        Optional<ButtonType> result = sureAlert.showAndWait();


        if (result.get() == ButtonType.OK) {
            JoggleCube.getInstance().interruptTimer();
            Navigation.getInstance().showOverlay(ScreenType.END, this);
        } else {
            sureAlert.close();
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
        TextInputDialog dialog = new TextInputDialog("Walter");
        dialog.setTitle("Enter Name");
        dialog.setHeaderText("Name Input");
        dialog.setContentText("Please enter your name:");
        // todo: make this call using proper URI to allow for those dodgy PCs
        dialog.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/cs221/GP01/main/resource/img/icon/person_icon.png"))));
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);

        // Get result from text box
        Optional<String> input = dialog.showAndWait();

        if (input.isPresent()) {
            // Normalize input and save to regular string
            String result = input.get().replace(" ", "");

            // todo: better validation
            if (result.matches("(\\w*)")) {
                JoggleCube.getInstance().setName(result);
            } else {
                dialog.setHeaderText("Invalid Name Entry, Please try again");
            }
        }


        foundWords = FXCollections.observableArrayList();


        // Disable hamburger context on right click
        menuButton.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);

        GridPane[] twoDGrid = {top2d, middle2d, bottom2d};
        GridPane[] twoFiveDGrid = {top25d, middle25d, bottom25d};
        gridDisplayer = new GridDisplayer(textField, twoDGrid, twoFiveDGrid, subScene, groupy, back, explodeIcon);
        gridDisplayer.buildGrids(JoggleCube.getInstance().getCubeData());
        foundWordsList.setItems(foundWords);


        JoggleCube.getInstance().startTimer();
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
     *
     * @return returns an observableList of strings of all currently found words
     * @author Samuel Jones - srj12
     */
    @Override
    public ObservableList<String> getFoundWords() {
        return foundWords;
    }

    //agl6

    public void setText(String text) {
        textField.setText(text);
    }

    public String getText() {
        return textField.getText();
    }

    public GridDisplayer getGridDisplayer() {
        return gridDisplayer;
    }

    public ContextMenu getHamburgerContext() {
        return hamburgerContext;
    }

    public Button getMenuButton() {
        return menuButton;
    }
}
