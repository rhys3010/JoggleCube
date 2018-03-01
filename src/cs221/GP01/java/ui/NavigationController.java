/*
   * @(#) ScreenController.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.java.ui;

import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;

import java.util.HashMap;

/**
 * NavigationController - Control the screens being displayed
 * <p>
 * All screens are stored in a HashMap and can be activated, added or removed. The screen are stored as an FXML Loader with
 * an FXML file and root pane 'pre-loaded'
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1
 */

public class NavigationController implements IViewNavigation{

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
    public NavigationController(Scene main){
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
     * @param newScreen - The screen to switch to
     */
    public void switchScreen(ScreenType newScreen){
        // Only allow screen switching if screen type isn't an overlay
        if(screens.get(newScreen).getController() instanceof BaseScreenController){
            //checks if the View needs prep before displaying
            if(screens.get(newScreen).getController() instanceof INeedPrep) {
                ((INeedPrep) screens.get(newScreen).getController()).prepView();
            }
            main.setRoot(screens.get(newScreen).getRoot());
        }
    }

    /**
     * Show a given overlay
     * @param overlay - The overlay to be shown
     * @param parent - The parent scene of the overlay
     */
    public void showOverlay(ScreenType overlay, BaseScreenController parent){
        // Verify that the overlay is of type BaseOverlayController
        if(screens.get(overlay).getController() instanceof BaseOverlayController){
            // Disable the background of the parent screen
            parent.getMainNode().setDisable(true);

            // If the parent screen is a GameController, then disable the game cube
            if(parent instanceof GameController){
                ((IGameController) parent).getCubeContainer().setVisible(false);
            }
            
            // Set the parent of the overlay
            ((BaseOverlayController) screens.get(overlay).getController()).setParentController(parent);

            //checks if the overlay needs prep before displaying
            if(screens.get(overlay).getController() instanceof INeedPrep) {
                ((INeedPrep) screens.get(overlay).getController()).prepView();
            }

            // Add the overlay's FXML to the parent screen's root
            parent.getRoot().getChildren().add(screens.get(overlay).getRoot());
        }
    }

    /**
     * Hide a given overlay
     * @param overlay - The overlay to be hidden
     */
    public void hideOverlay(ScreenType overlay, BaseScreenController parent){

        // Verify that the overlay is of type BaseOverlayController
        if(screens.get(overlay).getController() instanceof BaseOverlayController){

            // Remove the overlay's FXML from parent
            parent.getRoot().getChildren().remove(screens.get(overlay).getRoot());

            // Re-enable the parent's background
            parent.getMainNode().setDisable(false);

            // If the parent screen is Game Controller, re-enable the game cube
            if(parent instanceof GameController){
                ((IGameController) parent).getCubeContainer().setVisible(true);
            }
        }
    }
}
