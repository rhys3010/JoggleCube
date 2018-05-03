/*
 * @(#) Settings.java 1.0 2018/03/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.controllers.GameView;
import cs221.GP01.main.java.ui.controllers.SettingsOverlay;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * SettingsOverlay - Class to handle the preferred game configuration
 * <p>
 * Handle the settings of the game and adapt them depending on user input in the settings overlay
 * @author Rhys Evans
 * @author Nathan Williams (naw21)
 * @version 1.1
 * @see ISettings
 * @see SettingsOverlay
 */
public class Settings implements ISettings{

    /**
     * The instance of the settings singleton class
     */
    private static ISettings settings;

    /**
     * Boolean to store whether or not colourblind is enabled
     */
    private boolean colorBlind = false;

    /**
     * Available grid languages
     */

    private static String languages[] = {"English","Cymraeg"};


    private Dialog infoDialog;

    /**
     * Currently used grid language
     */
    private static String currLang = "English";

    /**
     * The game length
     */
    private static int timerLength = 180;

    /**
     * Colour Variables + Default Values
     */
    private String currentlySelectedColor = "#38aa38";
    private String availableColor = "#30599b";
    private String alreadySelectedColor = "#64846b";
    private String unavailableColor = "#aeaeae";



    /**
     * Private Constructor for settings class so only oone instance of class can be created
     *
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
     */
    public void toggleColourBlind() {

        // Enable colour blind mode
        colorBlind = !colorBlind;

        // Change the colours accordingly
        if (colorBlind) {
            currentlySelectedColor = "#cbc155";
            availableColor = "#b171cb";
            alreadySelectedColor = "#cb5758";
            unavailableColor = "#aeaeae";

        } else {
            currentlySelectedColor = "#38aa38";
            availableColor = "#30599b";
            alreadySelectedColor = "#64846b";
            unavailableColor = "#aeaeae";
        }

        // Update Icon
        GameView.getInstance().getColorBlindIcon().setVisible(colorBlind);
    }


    /**
     * Clear the highscores and prompt user
     */
    public void clearHighScores(){

        // Create confirmation dialog and store result
        Dialog confirmDialog = new Dialog();
        Optional<ButtonType> result = confirmDialog.showConfirmationDialog("Clear High Scores", "Are you sure you want to clear all High Scores?");

        if(result.get() == ButtonType.OK){
            // Remove overall highscores
            JoggleCube.getInstance().clearHighScores();
            infoDialog = new Dialog();
            infoDialog.showInformationDialog("Success", "All High Scores have been cleared");
        }
    }



    /**
     * Returns an instance of the singleton class settings
     * @return settings
     */
    public static ISettings getInstance(){
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

    /**
     * Get the colour to represent currently selected cube
     * @return
     */
    public String getCurrentlySelectedColor(){
        return currentlySelectedColor;
    }

    /**
     * Get the colour to represent available cubes
     * @return
     */
    public String getAvailableColor(){
        return availableColor;
    }

    /**
     * Get the colour to represent already selected cubes
     * @return
     */
    public String getAlreadySelectedColor(){
        return alreadySelectedColor;
    }

    /**
     * Get the colour to represent unavailable cubes
     * @return
     */
    public String getUnavailableColor(){
        return unavailableColor;
    }

    public Dialog getInfoDialog() {
        return infoDialog;
    }
}
