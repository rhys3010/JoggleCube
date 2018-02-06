package cs221.GP05.GUI.EndGUI;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * EndGUIController - A class that controls the EndGUI scene that is defined in EndGUI.fxml
 * Load and display highscores from another class
 * todo delete temp highscore class and establish a new one
 * todo add save option
 * <p>
 * Used with EndGUI.fxml
 * todo improve this description
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1  DRAFT
 */
public class EndGUIController implements Initializable{

    /**
     * Parent Anchor
     */
    @FXML
    AnchorPane parent;

    /**
     * High Score Table
     */
    @FXML
    TableView<HighScore> highScoreTable;

    /**
     * Table Columns
     */
    @FXML
    TableColumn dateCol, scoreCol;

    /**
     * List of Example High Score entries
     */
    final ObservableList<HighScore> highScores = FXCollections.observableArrayList(
            new HighScore(1214),
            new HighScore(5161),
            new HighScore(1671),
            new HighScore(6251),
            new HighScore(1561),
            new HighScore(9868),
            new HighScore(1567)
    );


    /**
     * Populate HighScore table with ficticious data at end screen load
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Set Default Values for Cells
        dateCol.setCellValueFactory(
                new PropertyValueFactory<HighScore, String>("Date")
        );

        scoreCol.setCellValueFactory(
                new PropertyValueFactory<HighScore, String>("Score")
        );

        // Populate the Table with high scores
        highScoreTable.setItems(highScores);
    }

    /**
     * When the return button is clicked it will return to the start screen of the game
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @version 0.1 DRAFT
     * @throws IOException if StartGUI.fxml doesn't exist
     */
    @FXML
    void btnReturnClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../StartGUI/StartGUI.fxml"));
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));

    }
}
