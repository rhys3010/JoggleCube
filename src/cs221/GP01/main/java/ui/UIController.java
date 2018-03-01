/*
   * @(#) JoggleCube.java 1.1 2018/02/13
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
 * UIController - A class to behave as a mediator between UI and Backend.
 * <p>
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1
 */
public class UIController implements IUIController {

    /**
     * The Path to the views package
     */
    private final String VIEWS_PATH_PREFIX = "../../resource/view/";

    /**
     * The NavigationController object
     */
    private IViewNavigation navigationController;


    private IGameController IGameController;
    private StartController startController;
    private HighScoreController highScoreController;
    private LoadGridController loadGridController;
    private EndController endController;
    private SettingsController settingsController;
    private HelpController helpController;

    public IGameController getGameController() {
        return IGameController;
    }

    public StartController getStartController() {
        return startController;
    }

    public HighScoreController getHighScoreController() {
        return highScoreController;
    }

    public LoadGridController getLoadGridController() {
        return loadGridController;
    }

    public EndController getEndController() {
        return endController;
    }

    public SettingsController getSettingsController() {
        return settingsController;
    }

    public HelpController getHelpController() {
        return helpController;
    }

    /**
     * All screen names (used to create and add to navigationController)
     */
    private static final ScreenType SCREENS[] = {ScreenType.START, ScreenType.SETTINGS, ScreenType.LOAD, ScreenType.GAME, ScreenType.END, ScreenType.HIGH_SCORES, ScreenType.HELP};

    /**
     * The JoggleCube object to handle backend logic
     */
    private IJoggleCubeController joggleCube ;

    /**
     * Constructor to get the joggleCube object
     * @param joggleCube
     */
    public UIController(IJoggleCubeController joggleCube) {
        this.joggleCube = joggleCube;
        joggleCube.setUI(this);
    }


    /**
     * Initialize the game by creating the necessary scenes and starting the JavaFx
     * @throws IOException If the fxml file can't be found or opened
     */
    public void initialize(Scene main) throws IOException{

        // Initialize the screen controller
        navigationController = new NavigationController(main);

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
                startController = new StartController(this);
                loader.setController(startController);
                break;

            case SETTINGS:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Settings.fxml"));
                settingsController =  new SettingsController(this);
                loader.setController(settingsController);
                break;
            case LOAD:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Load.fxml"));
                loadGridController = new LoadGridController(this);
                loader.setController(loadGridController);
                break;

            case GAME:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Game.fxml"));
                IGameController = new GameController(this);
                loader.setController(IGameController);
                break;

            case END:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "End.fxml"));
                endController = new EndController(this);
                loader.setController(endController);
                break;

            case HIGH_SCORES:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "HighScore.fxml"));
                highScoreController = new HighScoreController(this);
                loader.setController(highScoreController);
                break;

            case HELP:
                loader = new FXMLLoader(getClass().getResource(VIEWS_PATH_PREFIX + "Help.fxml"));
                helpController = new HelpController(this);
                loader.setController(helpController);
                break;

            default:
                loader = null;
        }

        loader.setRoot(loader.load());

        return loader;
    }


    /**
     * Get the handle input object
     * @return joggleCube
     */
    public IJoggleCubeController getJoggleCube() {
        return joggleCube;
    }



    /**
     * Retrieve the Screen Controller
     * @return NavigationController
     */
    public IViewNavigation getNavigationController(){
        return navigationController;
    }
}
