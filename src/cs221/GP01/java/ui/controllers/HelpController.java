package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.UIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpController extends BaseController implements Initializable {
    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public HelpController(UIController UIController){
        super(UIController);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {    }
}
