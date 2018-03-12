/*
 * @(#) ISettings.java 1.0 2018/03/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

/**
 * Interface for the Settings class to handle game behaviour/state
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
     * Check if the music option is enabled
     * @return true/false depending on state of the option
     */
    boolean isMusicEnabled();

    /**
     * Check if the sound effects option is enabled
     * @return true/false depending on state of the option
     */
    boolean isSoundEffectsEnabled();

    /**
     * Get the volume value of the game
     * @return - 0-100 value
     */
    double getVolume();

    /**
     * Set the colour blind option to true/false
     * @param colorBlind
     */
    void setColorBlindEnabled(boolean colorBlind);

    /**
     * Set the music option to true/false
     * @param music
     */
    void setMusicEnabled(boolean music);

    /**
     * Set the sound effects option to true/false
     * @param soundEffects
     */
    void setSoundEffectsEnabled(boolean soundEffects);

    /**
     * Set the volume value to a value between 0-100
     * @param volume
     */
    void setVolume(double volume);

    /**
     * Clear the currently saved highscores
     */
    void clearHighScores();
}
