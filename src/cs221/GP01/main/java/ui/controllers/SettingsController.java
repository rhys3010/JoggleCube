package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController extends BaseOverlayController implements INeedPrep, Initializable {


    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public SettingsController(UIController UIController){
        super(UIController);
    }


    /**
     * Handles the close button of the overlay being clicked
     */
    @FXML
    public void closeBtnClicked(){
        UIController.getNavigationController().hideOverlay(ScreenType.SETTINGS, parentController);
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
