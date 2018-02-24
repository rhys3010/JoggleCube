package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.UIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController extends BaseOverlayController {
    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public HelpController(UIController UIController){
        super(UIController);
    }
    
    /**
     * Handles the close button of the overlay being clicked
     */
    @FXML
    public void closeBtnClicked(){
        UIController.getNavigationController().hideOverlay(ScreenType.HELP, parentController);
    }
}
