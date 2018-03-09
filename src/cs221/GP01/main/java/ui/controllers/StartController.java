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
 * todo add a Highscore button
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */

public class StartController extends BaseScreenController implements Initializable {

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
    ComboBox languageSelector;


    /**
     * todo Do initialization stuff here
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        //todo get this list of info from a another class?
        languageSelector.getItems().setAll("English","Cymraeg");
        languageSelector.setOnAction(e -> {
            JoggleCubeController.getInstance().setLanguage(languageSelector.getValue().toString().substring(0,2).toLowerCase());
        });
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

}