/*
 * @(#) IFrontendController.java 1.1 2018/03/12
 *
 * Copyright (c) 2012 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

import javafx.scene.Scene;

import java.io.IOException;

/**
 * IFrontendController - An interface for the main controller of the game's frontend
 * <p>
 * Class used to handle the mediation between the game logic and game display, passing values, changing state etc.
 * @author Rhys Evans (rhe24)
 * @version 0.1
 */
public interface IFrontendController {

    /**
     * Initialize the game's display
     * @param main - The main scene of the game
     * @throws IOException If the FXML file for the screens cannot be found
     */
    void initialize(Scene main) throws IOException;
}
