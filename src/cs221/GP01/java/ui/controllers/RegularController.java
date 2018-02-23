package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class RegularController {


    /**
     * An instance of the UIController object to interface with backend
     */
    protected UIController UIController;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public RegularController(UIController UIController){
        this.UIController = UIController;
    }


    /**
     * The root node of a given screen
     */
    @FXML
    private StackPane root;

    /**
     * The main node of a given screen
     */
    @FXML
    private Node mainNode;


    /**
     * Get the root node of a given screen
     * @return root
     */
    public StackPane getRoot(){
        return root;
    }

    /**
     * Get the main node of the FXML
     * @return mainNode
     */
    public Node getMainNode(){
        return mainNode;
    }

    /**
     * Handle the menu button press
     */
    @FXML
    private void btnMenuClicked(){
        UIController.getMasterController().show(ScreenType.START);
        UIController.initalizeController(ScreenType.START);
    }

    /**
     * Handle the highscore button being pressed
     */
    @FXML
    private void btnHighScoreClicked(){
        UIController.getMasterController().show(ScreenType.HIGH_SCORES);
        UIController.initalizeController(ScreenType.HIGH_SCORES);
    }

    /**
     * Handle the help button being pressed
     */
    @FXML
    private void btnHelpClicked(){
        UIController.getMasterController().show(ScreenType.HELP);
        UIController.initalizeController(ScreenType.HELP);
    }

    /**
     * Handle the settings button being pressed
     */
    @FXML
    private void btnSettingsClicked(){
        UIController.getMasterController().show(ScreenType.SETTINGS);
        UIController.initalizeController(ScreenType.SETTINGS);
    }

}
