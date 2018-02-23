package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.UIController;
import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController extends BaseOverlayController implements Initializable {


    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public SettingsController(UIController UIController){
        super(UIController);
    }

    /**
     * todo Do initialization stuff here
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Handles the close button of the overlay being clicked
     */
    @FXML
    public void closeBtnClicked(){
        UIController.getNavigationController().hideOverlay(ScreenType.SETTINGS, parentController);
    }

}
