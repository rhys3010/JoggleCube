/*
 * @(#) Settings.java 1.0 2018/03/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

import cs221.GP01.main.java.model.JoggleCube;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Settings - Class to handle the preferred game configuration
 * <p>
 * Handle the settings of the game and adapt them depending on user input in the settings overlay
 * @author Rhys Evans
 * @author Nathan Williams (naw21)
 * @version 1.1
 * @see ISettings
 * @see cs221.GP01.main.java.ui.controllers.Settings
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

    private static String languages[] = {"English","Cymraeg"};
    private static String currLang = "English";
    private static int timerLength = 180;


    /**
     * Constructor for settings class
     * todo: needed?
     */
    private Settings(){}

    public static String getCurrLang() {
        return currLang;
    }

    public static void setCurrLang(String currLang) {
        if((currLang.length() == 2)){
            //converts prefix into full language name
            for(String lang : languages){
                if (lang.substring(0,2).toLowerCase().equals(currLang.toLowerCase())) {
                    Settings.currLang = lang;
                    break;
                }
            }
        } else {
            //checks if valid language
            for(String lang : languages){
                if (lang.toLowerCase().equals(currLang.toLowerCase())) {
                    Settings.currLang = lang;
                    break;
                }
            }
        }
        //when language is changed set the joggleCubeLanguage
        JoggleCube.getInstance().setLanguage();
    }

    public static String getCurrLangPrefix() {
        return currLang.substring(0,2).toLowerCase();
    }


    /**
     * Check if the colour blind option is enabled
     * @return true/false depending on state of the option
     */
    public boolean isColorBlindEnabled(){
        return colorBlind;
    }

    /**
     * Set the colour blind option to true/false
     * @param colorBlind
     */
    public void setColorBlindEnabled(boolean colorBlind){
        this.colorBlind = colorBlind;
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


    public static String[] getLanguages() {
        return languages;
    }

    public static int getTimerLength() {
        return timerLength;
    }

    public static void setTimerLength(int timerLength) {
        Settings.timerLength = timerLength;
    }
}
