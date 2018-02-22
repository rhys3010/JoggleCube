package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.UIController;
import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController extends BaseController implements Initializable {


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
    public void initialize(URL location, ResourceBundle resources){

    }

    /**
     * When the close button is pressed, remove the hide the overlay and re-enable the screen
     * @see GameController
     * @throws IOException
     */
    @FXML
    void btnCloseClicked() throws IOException{
        UIController.getScreenController().hide(ScreenType.SETTINGS);
    }
}
