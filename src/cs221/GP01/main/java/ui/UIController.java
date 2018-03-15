/*
   * @(#) JoggleCube.java 1.1 2018/03/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.ui;

import cs221.GP01.main.java.model.IJoggleCubeController;
import cs221.GP01.main.java.ui.controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * UIController - A class to implement IFrontendController
 * <p>
 * This class will behave as a mediator between the game's logic and the game's display, it will handle state changes,
 * screen changes and various aspects of user input
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 * @see IFrontendController
 */
public class UIController implements IFrontendController{

    /**
     * The singleton instance of the UIController class
     */
    private static UIController uiController;

    /**
     * Default constructor
     * todo: needed?
     */
    private UIController(){}

    /**
     * Get the singleton instance of the UIController object
     * @return uiController
     */
    public static UIController getInstance(){
        if(uiController == null){
            synchronized (UIController.class){
                if(uiController == null){
                    uiController = new UIController();
                }
            }
        }
        return uiController;
    }

    /**
     * The Path to the views package with all the FXML files for the screen
     */
    private static final String VIEWS_PATH_PREFIX = "/cs221/GP01/main/resource/view/";


    /**
     * All screen names (used to create and add to navigationController)
     */
    private static final ScreenType SCREENS[] = {ScreenType.START, ScreenType.SETTINGS, ScreenType.LOAD, ScreenType.GAME, ScreenType.END, ScreenType.HIGH_SCORES, ScreenType.HELP};


    /**
     * Initialize the game by creating the necessary scenes and starting the JavaFx
     * @throws IOException If the fxml file can't be found or opened
     */
    public void initialize(Scene main) throws IOException{

        // Initialize the screen controller
        IViewNavigation navigationController = NavigationController.getInstance();
        navigationController.setMainScene(main);
        // Iteratively create screens and add to navigationController
        for(int i = 0; i < SCREENS.length; i++){
            // Create FXML loader and populate with root and controller
            navigationController.add(SCREENS[i], createScreen(SCREENS[i]));
        }

        // Show the Start Screen
        navigationController.switchScreen(ScreenType.START);
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
                loader.setController(StartController.getInstance());
                break;

            case SETTINGS:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Settings.fxml"));
                loader.setController(SettingsController.getInstance());
                break;
            case LOAD:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Load.fxml"));
                loader.setController(LoadGridController.getInstance());
                break;

            case GAME:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Game.fxml"));
                loader.setController(GameController.getInstance());
                break;

            case END:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "End.fxml"));
                loader.setController(EndController.getInstance());
                break;

            case HIGH_SCORES:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "HighScore.fxml"));
                loader.setController(HighScoreController.getInstance());
                break;

            case HELP:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Help.fxml"));
                loader.setController(HelpController.getInstance());
                break;

            default:
                loader = null;
        }

        loader.setRoot(loader.load());

        return loader;
    }
}
