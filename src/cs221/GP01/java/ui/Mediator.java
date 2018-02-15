/*
   * @(#) JoggleCube.java 1.1 2018/02/13
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui;

import cs221.GP01.java.model.HandleInput;
import cs221.GP01.java.model.HandleOutput;
import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.controllers.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

/**
 * Mediator - A class to behave as a mediator between UI and Backend.
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

    private HashMap<ScreenType, Initializable> controllers = new HashMap<>();

    /**
     * All screen names (used to create and add to screenController)
     */
    private static final ScreenType SCREENS[] = {ScreenType.START, ScreenType.SETTINGS, ScreenType.LOAD, ScreenType.GAME, ScreenType.PAUSE, ScreenType.END, ScreenType.HIGH_SCORES};

    /**
     * The Handle Input object to handle backend logic
     */
    private HandleInput handleInput = new HandleInput();
    private HandleOutput handleOutput = new HandleOutput();


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
     * allows the backend or GUI to re-initialise a controller so it gets updated data from the backend.
     *
     * @param screenType the type of screen that needs its controller updating
     */
    public void initalizeController(ScreenType screenType){
        controllers.get(screenType).initialize(null,null);
    };

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
                controllers.put(ScreenType.START,new StartController(this));
                loader.setController(controllers.get(ScreenType.START));
                break;

            case SETTINGS:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Settings.fxml"));
                controllers.put(ScreenType.SETTINGS,new SettingsController(this));
                loader.setController(controllers.get(ScreenType.SETTINGS));
                break;
            case LOAD:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Load.fxml"));
                controllers.put(ScreenType.LOAD,new LoadGridController(this));
                loader.setController(controllers.get(ScreenType.LOAD));
                break;

            case GAME:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Game.fxml"));
                controllers.put(ScreenType.GAME,new GameController(this));
                loader.setController(controllers.get(ScreenType.GAME));
                break;

            case PAUSE:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Pause.fxml"));
                controllers.put(ScreenType.PAUSE,new PauseController(this));
                loader.setController(controllers.get(ScreenType.PAUSE));
                break;

            case END:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "End.fxml"));
                controllers.put(ScreenType.END,new EndController(this));
                loader.setController(controllers.get(ScreenType.END));
                break;

            case HIGH_SCORES:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "HighScore.fxml"));
                controllers.put(ScreenType.HIGH_SCORES,new HighScoreController(this));
                loader.setController(controllers.get(ScreenType.HIGH_SCORES));
                break;

            default:
                loader = null;
        }

        loader.setRoot(loader.load());

        return loader;
    }


    /**
     * Get the handle input object
     * @return handleInput
     */
    public HandleInput getHandleInput() {
        return handleInput;
    }


    /**
     * Get the handle input object
     * @return OutputInput
     */
    public HandleOutput getHandleOutput() {
        return handleOutput;
    }
}
