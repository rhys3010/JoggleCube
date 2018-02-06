package cs221.GP05.GUI.PauseGUI;

import cs221.GP05.GUI.GameGUI.GameGUIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * PauseGUIController - A class that controls the PauseGUI subscene that is defined in PauseGUI.fxml
 * <p>
 * Used with PauseGUI.fxml
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1  DRAFT
 */
public class PauseGUIController {

    /**
     * The pause overlay
     */
    @FXML
    VBox pauseOverlay;

    /**
     * All the pause menu buttons
     */
    @FXML
    Button resumeBtn, exitBtn, helpBtn;

    /**
     * When the resume button is pressed, remove overlay and enable game screen
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @throws IOException
     * @version 0.1 DRAFT
     */
    @FXML
    void btnResumeClicked() throws IOException{


        // Hide overlay
        pauseOverlay.setVisible(false);
    }

    /**
     * When the resume button is pressed, remove overlay and enable game screen
     * todo fix dodgy model overlaying
     * todo add prompt for exiting
     *
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1 DRAFT
     * @throws IOException if the StartGUI.fxml is not found.
     */
    @FXML
    void btnExitClicked() throws IOException{

    }
}
