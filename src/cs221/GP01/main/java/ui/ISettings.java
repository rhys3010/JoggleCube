/*
 * @(#) ISettings.java 1.0 2018/03/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

/**
 * ISettings - Interface for the Settings class
 * <p>
 * Handle the preferred behaviour of several aspects of the game's behaviour / state
 * @author Rhys Evans (rhe24)
 * @version 0.1
 * @see Settings
 */
public interface ISettings {

    /**
     * Check if the colour blind option is enabled
     * @return true/false depending on state of the option
     */
    boolean isColorBlindEnabled();


    /**
     * Set the colour blind option to true/false
     * @param colorBlind
     */
    void setColorBlindEnabled(boolean colorBlind);


    /**
     * Clear the currently saved highscores
     */
    void clearHighScores();
}
