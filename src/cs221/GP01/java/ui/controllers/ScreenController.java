/*
   * @(#) ScreenController.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;

import java.util.HashMap;

/**
 * ScreenController - Control the screens being displayed
 * <p>
 * All screens are stored in a HashMap and can be activated, added or removed. The screen are stored as an FXML Loader with
 * an FXML file and root pane 'pre-loaded'
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1
 */

public class ScreenController {

    /**
     * All screens to be stored, store Type as Key and an FXML loader as value
     */
    private HashMap<ScreenType, FXMLLoader> screens = new HashMap<>();

    /**
     * The main scene of the program
     */
    private Scene main;

    /**
     * Constructor to get main Scene
     */
    public ScreenController(Scene main){
        this.main = main;
    }


    /**
     * Add screen to the hashmap
     * @param name
     * @param loader = the FXML loader
     */
    public void add(ScreenType name, FXMLLoader loader){
        screens.put(name, loader);

    }

    /**
     * Remove  a Screen from the HashMap
     * @param name
     */
    public void remove(ScreenType name){
        screens.remove(name);
    }

    /**
     * Switch to a given screen
     * @param name
     */
    public void show(ScreenType name){
        // Special Cases for Overlay screens
        if(name == ScreenType.PAUSE || name == ScreenType.END){

            // Get game screen controller
            GameController controller = screens.get(ScreenType.GAME).getController();

            // Get root node of game screen and inject FXML
            controller.getRootNode().getChildren().add(screens.get(name).getRoot());

            // Disable Background
            controller.getGameScreen().setDisable(true);
        }else{
            // Get the FXML loader of a given name and get it's root pane
            main.setRoot(screens.get(name).getRoot());
        }
    }

    /**
     * Hide a screen type (only applicable for overlays)
     * @param name - the screen to hide
     */
    public void hide(ScreenType name){

        GameController gameController = screens.get(ScreenType.GAME).getController();

        // Remove pause overlay and enable background
        if(name == ScreenType.PAUSE){

            // Remove Overlay
            gameController.getRootNode().getChildren().remove(screens.get(name).getRoot());

            // Enable background
            gameController.getGameScreen().setDisable(false);

            // Remove End overlay and Re-enable game screen
        }else if(name == ScreenType.END){

            if(gameController.getRootNode().getChildren().contains(screens.get(name).getRoot())){
                gameController.getRootNode().getChildren().remove(screens.get(name).getRoot());
            }

            gameController.getGameScreen().setDisable(false);
        }
    }
}
