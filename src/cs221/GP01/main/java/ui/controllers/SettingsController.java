/*
 * @(#) StartController.java 1.1 2018/02/12
 *
 * Copyright (c) 2012 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */
package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.ui.NavigationController;
import cs221.GP01.main.java.ui.Settings;
import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * SettingsController - A class that does something.
 * <p>
 * How it is used
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class SettingsController extends BaseOverlayController implements INeedPrep, Initializable {


    private static SettingsController settingsController;

    private SettingsController(){}

    public static SettingsController getInstance(){
        if(settingsController == null){
            synchronized (SettingsController.class){
                if(settingsController == null){
                    settingsController = new SettingsController();
                }
            }
        }
        return settingsController;
    }


    /**
     * Handles the close button of the overlay being clicked
     */
    @FXML
    public void closeBtnClicked(){
        NavigationController.getInstance().hideOverlay(ScreenType.SETTINGS, parentController);
    }

    /**
     * Handle the clearing of highscores
     */
    @FXML
    public void clearHighScoreClicked(){
        Settings.getInstance().clearHighScores();
    }

    @Override
    public void prepView() {
        //display the relevant stuff.
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
