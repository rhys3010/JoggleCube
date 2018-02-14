/*
   * @(#) JoggleCube.java 1.1 2018/02/13
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui;

import cs221.GP01.java.model.HandleInput;
import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.controllers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Mediator - A class to behave as a mediator between UI and Backendl
 * <p>
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1
 */
public class Mediator {

    /**
     * The Path to the views package
     */
    private final String VIEWS_PATH_PREFIX = "../../resource/view/";

    /**
     * The ScreenController object
     */
    private ScreenController screenController;

    /**
     * All screen names (used to create and add to screenController)
     */
    private static final ScreenType SCREENS[] = {ScreenType.START, ScreenType.LOAD, ScreenType.GAME, ScreenType.PAUSE, ScreenType.END, ScreenType.HIGH_SCORES};

    /**
     * The Handle Input object to handle backend logic
     */
    private HandleInput handleInput = new HandleInput();


    /**
     * Initialize the game by creating the necessary scenes and starting the JavaFx
     * @throws IOException If the fxml file can't be found or opened
     */
    public void initialize(Scene main) throws IOException{

        // Initialize the screen controller
        screenController = new ScreenController(main);

        // Iteratively create screens and add to screenController
        for(int i = 0; i < SCREENS.length; i++){
            // Create FXML loader and populate with root and controller
            screenController.add(SCREENS[i], createScreen(SCREENS[i]));
        }

        // Show the Start Screen
        screenController.show(ScreenType.START);
    }


    /**
     * Retrieve the Screen Controller
     * @return ScreenController
     */
    public ScreenController getScreenController(){

        return screenController;
    }

    /**
     * Utility function to create a scene and store in a loader
     * @param screenType - The type of screen to create
     * @return loader - the created loader
     * @throws IOException - if FXML file can't be found
     */
    private FXMLLoader createScreen(ScreenType screenType) throws IOException{

        // The loader to be returned
        FXMLLoader loader;

        // A switch case depending on enum
        switch(screenType){

            case START:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Start.fxml"));
                loader.setController(new StartController(this));
                break;

            case LOAD:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Load.fxml"));
                loader.setController(new LoadGridController(this));
                break;

            case GAME:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Game.fxml"));
                loader.setController(new GameController(this));
                break;

            case PAUSE:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Pause.fxml"));
                loader.setController(new PauseController(this));
                break;

            case END:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "End.fxml"));
                loader.setController(new EndController(this));
                break;

            case HIGH_SCORES:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "HighScore.fxml"));
                loader.setController(new HighScoreController(this));
                break;

            default:
                loader = null;
        }

        loader.setRoot(loader.load());

        return loader;
    }

    /**
     * Get recently played/loaded grids
     * @return a list of the recently opened grids
     */
    public ObservableList<String> getRecentGrids() {
        ObservableList<String> recentGrids = FXCollections.observableArrayList (
                "something/something/grid01.grid", "grid02.grid", "grid03.grid", "grid04.grid");
        return recentGrids;
    }


    /**
     * Get the handle input object
     * @return handleInput
     */
    public HandleInput getHandleInput() {
        return handleInput;
    }
}
