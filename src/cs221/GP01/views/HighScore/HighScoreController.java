package cs221.GP01.views.HighScore;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.Start.StartController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * HighScoreController - A class that controls the HighScore scene that is defined in HighScore.fxml
 * Load and display highscores from another class
 * todo delete temp highscore class and establish a new one
 * todo add save option
 * <p>
 * Used with HighScore.fxml
 * todo improve this description
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class HighScoreController implements Initializable{

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
     * Parent Anchor
     */
    @FXML
    StackPane parent;

    /**
     * High Score Table
     */
    @FXML
    TableView<HighScore> highScoreTable;

    /**
     * Table Columns
     */
    @FXML
    TableColumn idCol, dateCol, scoreCol, nameCol;


    /**
     * List of Example High Score entries
     */
    final ObservableList<HighScore> highScores = FXCollections.observableArrayList(
            new HighScore(5161, "John"),
            new HighScore(1415, "Adam"),
            new HighScore(2515, "Wendy"),
            new HighScore(5151, "Steve"),
            new HighScore(6711, "Pete"),
            new HighScore(1314, "Dave"),
            new HighScore(4215, "Joe"),
            new HighScore(1455, "Joan"),
            new HighScore(6161, "Sara"),
            new HighScore(3671, "Andrew"),
            new HighScore(1551, "James"),
            new HighScore(2565, "Levi")
    );

    /**
     * Filtered list of HighScore entries to ensure only the top 10 scores are displayed
     * usind filtered list
     */
    final FilteredList<HighScore> filteredHighScores = new FilteredList<HighScore>(
            highScores,
            HighScore -> highScores.indexOf(HighScore) < 10
    );


    /**
     * Populate HighScore table with highscore data at end screen load
     * todo Add Rank Number to table
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
                new PropertyValueFactory<HighScore, Integer>("Score")
        );

        nameCol.setCellValueFactory(
                new PropertyValueFactory<HighScore, String>("Name")
        );


        // Set Score sort type
        scoreCol.setSortType(TableColumn.SortType.DESCENDING);

        // Populate the Table with filtered high scores
        highScoreTable.setItems(filteredHighScores);

        // Prevent user from reordering table
        columnReorder(highScoreTable, idCol, nameCol, scoreCol, dateCol);

        // Sort by score
        highScoreTable.getSortOrder().add(scoreCol);
        highScoreTable.sort();
        scoreCol.setSortable(false);
    }


    /**
     * When the return button is clicked it will return to the start screen of the game
     * @author Rhys Evans (rhe24@aber.ac.uk)
     * @author Nathan Williams (naw21)
     * @version 0.2 DRAFT
     * @throws IOException if Start.fxml doesn't exist
     */
    @FXML
    void btnReturnClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Start/Start.fxml"));
        Parent root = fxmlLoader.load();
        StartController startController = fxmlLoader.getController();
        startController.setJoggleCube(joggleCube);
        Stage stage = (Stage) parent.getScene().getWindow();
        stage.setScene(new Scene(root, 600, 600));
    }



    /**
     * Stop users from being able to reorder table columns (temporary fix)
     * Solution used here: https://bittlife.com/javafx-disable-column-reorder-tableview/
     * @author Rhys Evans (rhe24)
     */
    public static <S, T> void columnReorder(TableView table, TableColumn<S, T> ...columns){
        table.getColumns().addListener(new ListChangeListener() {
            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if(change.wasReplaced() && !suspended){
                    this.suspended = true;
                    table.getColumns().setAll(columns);
                    this.suspended = false;
                }
            }
        });
    }

}
