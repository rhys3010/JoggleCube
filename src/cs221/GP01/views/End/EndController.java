package cs221.GP01.views.End;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.HighScore.HighScoreController;
import cs221.GP01.views.Start.StartController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * EndController - A class that controls the Pause subscene that is defined in End.fxml
 * <p>
 * todo fix dodgy model overlaying
 * todo add prompt for exiting
 * Used with End.fxml
 *
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class EndController {

    /**
     * The end overlay
     */
    @FXML
    VBox endOverlay;

    /**
     * a link to the backend
     */
    private JoggleCube joggleCube;

    /**
     * method to set a link to the backend
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @see JoggleCube
     * @param joggleCube the backend
     */
    public void setJoggleCube(JoggleCube joggleCube){
        this.joggleCube = joggleCube;
    }

    /**
     * When the exit button is pressed, close the program
     * todo add prompt?
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @author Nathan Williams (naw21)
     * @version 0.2 DRAFT
     */
    @FXML
    void btnHighScoreClicked() throws IOException {
        //load fxml and controller
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../HighScore/HighScore.fxml"));
        Parent root = fxmlLoader.load();
        //get the controller
        HighScoreController highScoreController = fxmlLoader.getController();
        //tells the controller about the back end
        highScoreController.setJoggleCube(joggleCube);
        //get the backend to give the high scores to the highScoreController
        joggleCube.getHighScores("Current Grid",highScoreController);

        //display the GUI
        Stage stage = (Stage) endOverlay.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }

    /**
     * When the 'save' button is clicked prompt user to chose a save location
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1
     */
    @FXML
    void btnSaveClicked(){
        Stage newStage = new Stage();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Save Cube");
        File file = fileChooser.showSaveDialog(newStage);
        if (file != null) {

        } else {
            //todo add try again pop-up
        }
    }


    /**
     * When the 'return to menu' button is clicked change scene to menu scene
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1
     */
    @FXML
    void btnMenuClicked() throws IOException{

        // load fxml and controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Start/Start.fxml"));
        Parent root = loader.load();

        StartController startController = loader.getController();
        startController.setJoggleCube(joggleCube);

        Stage stage = (Stage) endOverlay.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }
}
