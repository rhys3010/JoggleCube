/*
   * @(#) StartController.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.model.JoggleCubeController;
import cs221.GP01.main.java.ui.NavigationController;
import cs221.GP01.main.java.ui.Settings;
import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * StartController - A class that does something.
 * <p>
 * How it is used
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */

public class StartController extends BaseScreenController implements INeedPrep, Initializable {

    private static StartController startController;

    private StartController(){}

    public static StartController getInstance(){
        if(startController == null){
            synchronized (UIController.class){
                if(startController == null){
                    startController = new StartController();
                }
            }
        }
        return startController;
    }

    /**
     * The FXML node of the language dropdown selector
     */
    @FXML
    private ComboBox<String> languageSelector;


    /**
     *
     */
    @Override
    public void prepView(){
        languageSelector.setValue(Settings.getCurrLang());
        languageSelector.setOnAction(e -> Settings.setCurrLang(languageSelector.getValue()));
    }


    /**
     * When the Start New Grid button is clicked it will load the Game scene with a new grid.
     *
     */
    @FXML
    private void btnStartNewGridClicked() {
       JoggleCubeController.getInstance().generateRandomGrid();
        NavigationController.getInstance().switchScreen(ScreenType.GAME);
    }

    /**
     * When the Load Grid button is clicked it will load the LoadGrid scene.
     *
     */
    @FXML
    private void btnLoadGridClicked() {
        NavigationController.getInstance().switchScreen(ScreenType.LOAD);
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
        languageSelector.getItems().setAll(Settings.getLanguages());
    }
}