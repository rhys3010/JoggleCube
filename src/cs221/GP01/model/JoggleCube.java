package cs221.GP01.model;

import cs221.GP01.views.End.EndController;
import cs221.GP01.views.Game.GameController;
import cs221.GP01.views.HighScore.HighScoreController;
import cs221.GP01.views.Start.StartController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class JoggleCube {

    /**
     *
     * @param gameScreenController the controller to send grid data to, and receive input.
     */
    public void newRandomGrid(GameController gameScreenController) {
        System.out.println("new grid created and loaded into gameScreen");
    }

    /**
     *
     * @param EndController the controller to send the end game data to
     */
    public void endGame(EndController EndController) {
        System.out.println("final score sent to controller");
    }

    /**
     *
     * @param option either "current grid" or "overall"
     * @param highScoreController the controller to send the highScore data to.
     */
    public void getHighScores(String option, HighScoreController highScoreController) {
        System.out.println("highscore sent to controller");
    }

    public void resume() {
        System.out.println("resumed");
    }

    public void pause() {
        System.out.println("paused");
    }

    /**
     *
     * @param gameController
     * @param gridFile
     */
    public void loadGrid(GameController gameController, File gridFile) {
        System.out.println("grid loaded from file");
    }


    /**
     *
     * @return a list of the recently opened grids
     */
    public ObservableList<String> getRecentGrids() {
        ObservableList<String> recentGrids = FXCollections.observableArrayList (
                "something/something/grid01.grid", "grid02.grid", "grid03.grid", "grid04.grid");
        return recentGrids;
    }
}
