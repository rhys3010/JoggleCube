/*
 * @(#) IViewNavigation.java 1.0 2018/03/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

import cs221.GP01.main.java.ui.controllers.BaseScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * INavigation - Interface for the View Navigation classs
 * <p>
 * Handle all aspects of JavaFX scenes
 * This class is used to hide/show overlays and switch screens.
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24)
 * @version 0.2
 * @see ScreenType
 */
public interface IViewNavigation {

    /**
     * Add an FXML screen to the hashmap of screens
     * @param name - the name of the screen as an enumeration of ScreenType
     * @param loader - the FXMLLoader object of the scene
     */
    void add(ScreenType name, FXMLLoader loader);

    /**
     * Remove an FXML screen from the hashmap by name
     * @param name - Name of the screen to remove
     */
    void remove(ScreenType name);

    /**
     * Change the active scene on the JavaFX stage to the scene specified
     * @param newScreen - The scene to switch to
     */
    void switchScreen(ScreenType newScreen);

    /**
     * Show a given overlay over it's parent FXML scene
     * @param overlay - The ScreenType Enumeration of the overlay to display
     * @param parent - The controller object of the overlay's parent FXML
     */
    void showOverlay(ScreenType overlay, BaseScreenController parent);

    /**
     * Hide a given overlay by removing it from its parent FXML
     * @param overlay - The overlay to display
     * @param parent - The controller object of the overlay's parent
     */
    void hideOverlay(ScreenType overlay, BaseScreenController parent);

    /**
     * Declare the main scene of the application
     * @param main - The main scene
     */
    void setMainScene(Scene main);
}
