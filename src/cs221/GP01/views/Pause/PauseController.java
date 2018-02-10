package cs221.GP01.views.Pause;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.Game.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * EndController - A class that controls the Pause subscene that is defined in End.fxml
 * <p>
 * todo fix dodgy model overlaying
 * todo add prompt for exiting
 *
 * todo add save button
 * Used with End.fxml
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class PauseController {

    /**
     * The pause overlay
     */
    @FXML
    VBox pauseOverlay;

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
     * When the resume button is pressed, remove overlay and resume the game.
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @throws IOException
     * @version 0.2 DRAFT
     */
    @FXML
    void btnResumeClicked() throws IOException{
        pauseOverlay.setVisible(false);
        joggleCube.resume();
    }

    /**
     * When the exit button is pressed, go to home screen.
     * todo add prompt?
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1 DRAFT
     */
    @FXML
    void btnExitClicked(){


    }

    private GameController gameController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
