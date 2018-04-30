/*
 * @(#) StartController.java 1.1 2018/02/12
 *
 * Copyright (c) 2012 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */
package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Settings - A class that does something.
 * <p>
 * How it is used
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class Settings extends BaseOverlay implements INeedPrep, Initializable {

    /**
     * All FXML nodes
     */
    @FXML
    private CheckBox colorBlindToggle;

    private static Settings settingsView;

    private Settings(){}

    public static Settings getInstance(){
        if(settingsView == null){
            synchronized (Settings.class){
                if(settingsView == null){
                    settingsView = new Settings();
                }
            }
        }
        return settingsView;
    }


    /**
     * Handles the close button of the overlay being clicked
     */
    @FXML
    public void closeBtnClicked(){
        Navigation.getInstance().hideOverlay(ScreenType.SETTINGS, parentController);
    }

    /**
     * Handle the clearing of highscores
     */
    @FXML
    public void clearHighScoreClicked(){
        cs221.GP01.main.java.ui.Settings.getInstance().clearHighScores();
    }

    /**
     * Handle Colour Blind Toggle
     */
    @FXML
    public void colorBlindToggleClicked(){
        // Switch the toggle to the opposite of what it currently is
        cs221.GP01.main.java.ui.Settings.getInstance().setColorBlindEnabled(colorBlindToggle.isSelected());
    }


    @Override
    public void prepView() {
        // Set the toggles and sliders to their default value from settings class
        colorBlindToggle.setSelected(cs221.GP01.main.java.ui.Settings.getInstance().isColorBlindEnabled());
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
