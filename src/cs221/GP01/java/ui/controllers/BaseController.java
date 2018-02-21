package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class BaseController {

    /**
     * The root Node in this scene.
     */
    @FXML
    private Node root;


    /**
     * An instance of the UIController object to interface with backend
     */
    protected UIController UIController;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public BaseController(UIController UIController){
        this.UIController = UIController;
    }

    /**
     *
     */
    @FXML
    private void btnSettingsClicked() {
        UIController.getScreenController().show(ScreenType.SETTINGS);
    }

    /**
     * When the HighScore button is clicked it will load the Highscore scene.
     */
    @FXML
    private void btnHighScoreClicked() {
        UIController.getScreenController().show(ScreenType.HIGH_SCORES);
        UIController.initalizeController(ScreenType.HIGH_SCORES);
    }

    @FXML
    private void btnHelpClicked() {
        UIController.getScreenController().show(ScreenType.HELP);
    }

    @FXML
    private void btnStartClicked() {
        UIController.getScreenController().show(ScreenType.START);
    }

    /**
     * Get the root node of the FXML
     * @return root - the root node
     */
    public Node getRoot(){
        return root;
    }

}
