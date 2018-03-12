/*
 * @(#) Settings.java 1.0 2018/03/05
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Handle the system settings from the settings overlay
 * @author Rhys Evans
 * @author Nathan Williams (naw21)
 * @version 0.3  DRAFT
 */
public class Settings implements ISettings{

    /**
     * The instance of the settings singleton class
     */
    private static Settings settings;

    /**
     * Boolean to store whether or not colourblind is enabled
     */
    private boolean colorBlind = false;

    /**
     * Boolean to store whether or not the music option is enabled
     * Enabled as default
     */
    private boolean music = true;

    /**
     * Boolean to store whether the sound effects option is enabled
     * Enabled as default
     */
    private boolean soundEffects = true;

    /**
     * Store the current volume of the game (0-100)
     * 75% as default
     */
    private int volume = 75;

    /**
     * Constructor for settings class
     */
    private Settings(){}


    /**
     * Check if the colour blind option is enabled
     * @return true/false depending on state of the option
     */
    public boolean isColorBlindEnabled(){
        return colorBlind;
    }

    /**
     * Check if the music option is enabled
     * @return true/false depending on state of the option
     */
    public boolean isMusicEnabled(){
        return music;
    }

    /**
     * Check if the sound effects option is enabled
     * @return true/false depending on state of the option
     */
    public boolean isSoundEffectsEnabled(){
        return soundEffects;
    }

    /**
     * Get the volume value of the game
     * @return - 0-100 value
     */
    public int getVolume(){
        return volume;
    }

    /**
     * Set the colour blind option to true/false
     * @param colorBlind
     */
    public void setColorBlindEnabled(boolean colorBlind){
        this.colorBlind = colorBlind;
    }

    /**
     * Set the music option to true/false
     * @param music
     */
    public void setMusicEnabled(boolean music){
        this.music = music;
    }

    /**
     * Set the sound effects option to true/false
     * @param soundEffects
     */
    public void setSoundEffectsEnabled(boolean soundEffects){
        this.soundEffects = soundEffects;
    }

    /**
     * Set the volume value to a value between 0-100
     * @param volume
     */
    public void setVolume(int volume){
        // Verify that volume is between 0-100
        if(volume >= 0 && volume <= 100){
            this.volume = volume;

        }else{
            // Todo: error handling
            System.err.println("Invalid Volume Value! Volume Values must be between 0-100");
        }
    }

    /**
     * Clear the highscores and prompt user
     */
    public void clearHighScores(){
        // Display 'are you sure' overlay
        Alert sureAlert = new Alert(Alert.AlertType.CONFIRMATION);
        sureAlert.setTitle("Quit Game");
        sureAlert.setHeaderText(null);
        sureAlert.setContentText("Are you sure you want to clear all High Scores?");

        Optional<ButtonType> result = sureAlert.showAndWait();

        if (result.get() == ButtonType.OK) {
            // todo: Clear high scores here
        } else {
            sureAlert.close();
        }
    }



    /**
     * Returns an instance of the singleton class settings
     * @return settings
     */
    public static Settings getInstance(){
        if(settings == null){
            synchronized (Settings.class){
                if(settings == null){
                    settings = new Settings();
                }
            }
        }
        return settings;
    }



}
