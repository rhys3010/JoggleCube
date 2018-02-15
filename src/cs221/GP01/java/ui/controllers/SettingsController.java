package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.UIController;
import cs221.GP01.java.ui.ScreenType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    /**
     * The root Node in this scene.
     */
    @FXML
    private Node root;

    /**
     * An instance of the UIController object to interface with backend
     */
    private UIController UIController;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public SettingsController(UIController UIController){
        this.UIController = UIController;
    }

    /**
     * todo Do initialization stuff here
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

    /**
     * Get the root node of the FXML
     * @return root - the root node
     */
    public Node getRoot(){
        return root;
    }

    @FXML
    private void btnBackClicked() throws IOException {
        UIController.getScreenController().show(ScreenType.START);

    }




}
