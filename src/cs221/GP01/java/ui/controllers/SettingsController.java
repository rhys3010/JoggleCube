package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.Mediator;
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
     * An instance of the mediator object to interface with backend
     */
    private Mediator mediator;

    /**
     * Constructor to ensure mediator object is passed
     * @param mediator
     */
    public SettingsController(Mediator mediator){
        this.mediator = mediator;
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
        mediator.getScreenController().show(ScreenType.START);

    }




}
