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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * GameController - A class that controls the Game scene that is defined in Game.fxml
 * <p>
 * Used with Game.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class GameController extends BaseScreenController implements IGameController, INeedPrep {

    private GridDisplayer gridDisplayer;

    @FXML
    private TabPane cubeContainer;


    @FXML
    private Label scoreLabel, timerLabel;

    private ObservableList<String> foundWords = FXCollections.observableArrayList();


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
        if(!textField.getText().equals("")) {
            if (UIController.getJoggleCube().testWordValidity(textField.getText())) {
                foundWords.add(textField.getText());
                btnSubmit.setStyle("-fx-background-color: -fx-valid-color;");
                textField.setStyle("-fx-background-color: -fx-valid-color; -fx-text-fill: white;");
                gridDisplayer.setAllActive();
                textField.setText("");
            } else {
                btnSubmit.setStyle("-fx-background-color: -fx-invalid-color;");
                textField.setStyle("-fx-background-color: -fx-invalid-color; -fx-text-fill: white;");
            }
        } else {
            btnSubmit.setStyle("-fx-background-color: -fx-invalid-color;");
            textField.setStyle("-fx-background-color: -fx-invalid-color; -fx-text-fill: white;");
        }

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

    /**
     * Handles the hamburger menu being clicked
     */
    @FXML
    private void btnMenuClicked(ActionEvent event) {
        // Get the coordinates of the menu button
        Point2D screenPos = menuButton.localToScreen(menuButton.getLayoutX(), menuButton.getLayoutY());

        // Show the context menu at the X, Y co-ordinates
        hamburgerContext.show(menuButton, screenPos.getX(), screenPos.getY());
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
        //todo stop timer add up score etc
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

        createCellFactory();

        GridPane[] twoDGrid = {top2d, middle2d, bottom2d};
        GridPane[] twoFiveDGrid = {top25d, middle25d, bottom25d};
        gridDisplayer = new GridDisplayer(textField,twoDGrid,twoFiveDGrid,subScene,groupy,back);
        gridDisplayer.buildGrids(UIController.getJoggleCube().getCubeData());
        foundWordsList.setItems(foundWords);
    }

    /**
     * Helper function to create listview cell factory
     */
    private void createCellFactory(){
        // Create cell factory for found words list
        foundWordsList.setCellFactory(lv -> {

            // Create context menu and cell
            ListCell<String> cell = new ListCell<>();
            ContextMenu contextMenu = new ContextMenu();

            // Create a menu item for looking up a word
            MenuItem lookupItem = new MenuItem();
            lookupItem.textProperty().bind(Bindings.format("Lookup \"%s\" in Dictionary", cell.itemProperty()));

            // Handle item menu click
            lookupItem.setOnAction(event -> {
                // Launch dictionary URL in user's default browser

                // Don't uncomment this - it'll brick your PC :(
                // todo fix.

                /*
                try{
                    Desktop.getDesktop().browse(new URI("http://www.dictionary.com/browse/" + cell.textProperty().get()));
                }catch (IOException ex1){
                    ex1.printStackTrace();

                }catch (URISyntaxException ex2){
                    ex2.printStackTrace();
                }*/
            });


            // Create copy option in menu
            MenuItem copyItem = new MenuItem();
            copyItem.textProperty().bind(Bindings.format("Copy", cell.itemProperty()));

            // Behaviour of copy item
            copyItem.setOnAction(event -> {

                // Create clipboard object and add the cell contents to it
                Clipboard clipboard = Clipboard.getSystemClipboard();

                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(cell.textProperty().get());

                clipboard.setContent(clipboardContent);
            });


            // Add items to context menu
            contextMenu.getItems().addAll(lookupItem, copyItem);
            cell.textProperty().bind(cell.itemProperty());

            // Add context menu to each added cell
            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if(isNowEmpty){
                    cell.setContextMenu(null);
                }else{
                    cell.setContextMenu(contextMenu);
                }
            });

            return cell;
        });
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
}
