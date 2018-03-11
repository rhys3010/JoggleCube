/*
 * @(#) HighScoreController.java 1.0 2018/02/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.model.Score;
import cs221.GP01.main.java.model.JoggleCubeController;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * HighScoreController - A class that controls the Score scene that is defined in Score.fxml
 * Load and display highscores from another class
 * todo delete temp highscore class and establish a new one
 *
 * <p>
 * Used with Score.fxml
 * todo improve this description
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21@aber.ac.uk)
 * @version 0.2
 */
public class HighScoreController extends BaseScreenController implements Initializable, INeedPrep {


    private static HighScoreController highScoreController;

    private HighScoreController(){}

    public static HighScoreController getInstance(){
        if(highScoreController == null){
            synchronized (HighScoreController.class){
                if(highScoreController == null){
                    highScoreController = new HighScoreController();
                }
            }
        }
        return highScoreController;
    }

    /**
     * High Score Table
     */
    @FXML
    private TableView<Score> highScoreTable;

    /**
     * Table Columns
     */
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn<Score, String> dateCol;
    @FXML
    private TableColumn scoreCol;
    @FXML
    private TableColumn<Score, String> nameCol;

    /**
     * The label of the highscore page
     */
    @FXML
    private Label highScorePageLabel;

    /**
     * Highscores for all cubes
     */
    private ObservableList<Score> overallScores;

    /**
     * Highscores for the currently played cube
     */
    private ObservableList<Score> currentCubeScores;

    /**
     * The different highscore pages that can be displayed
     */
    private final String HIGH_SCORE_PAGES[] = {"Overall", "Current"};

    @FXML
    private Button leftPageNav, rightPageNav;


    /**
     * Populate Score table with highscore data
     *
     */
    public void prepView(){

        overallScores = JoggleCubeController.getInstance().getOverallHighScores();
        currentCubeScores = JoggleCubeController.getInstance().getCurrentCubeHighScores();

        if(currentCubeScores == null){
            leftPageNav.setVisible(false);
            rightPageNav.setVisible(false);
        } else {
            leftPageNav.setVisible(true);
            rightPageNav.setVisible(true);
        }

        // Populate the Table with filtered high scores
        if(overallScores != null) {
            populateTable(overallScores, "All Cubes");
        }
    }



    /**
     * Stop users from being able to reorder table columns (temporary fix)
     * Solution used here: https://bittlife.com/javafx-disable-column-reorder-tableview/
     */
    @SafeVarargs
    private static <S, T> void columnReorder(TableView table, TableColumn<S, T>... columns){
        table.getColumns().addListener(new ListChangeListener() {
            boolean suspended;

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


    /**
     * Helper function to populate the highscore table
     *
     * todo do we need to limit to top 10?
     *
     * @param list - a filtered list of the highscores to populate the table with
     */
    private void populateTable(ObservableList<Score> list, String title){

        highScorePageLabel.setText(title);

        //filters list to the top 10
        FilteredList<Score> filteredList = new FilteredList<Score>(
                list,
                HighScore -> list.indexOf(HighScore) < 10
        );

        highScoreTable.setItems(filteredList);
        highScoreTable.sort();
    }

    /**
     * Advance to the next page in the Highscores list
     *
     */
    @FXML
    public void nextPage(){
        changePage();
    }

    /**
     * Go the previous page in the highscores list
     */
    @FXML
    public void previousPage(){
        changePage();
    }

    /**
     * Utility function to change the page of the high score table
     *
     * todo if not filtering to top 10 compare the data in table: highScoreTable.getItems().equals(overallScores);
     */
    private void changePage(){
        if(highScorePageLabel.getText().equals("Current Cube")){
            populateTable(overallScores,"All Cubes");
        }else if(highScorePageLabel.getText().equals("All Cubes")) {
            populateTable(currentCubeScores, "Current Cube");
        }
    }
    /**
     *
     * table setup stuff
     *
     *
     * todo Add Rank Number to table
     *
     * @param location axc
     * @param resources axc
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set Default Values for Cells
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Score, String>("Date")
        );

        scoreCol.setCellValueFactory(
                new PropertyValueFactory<Score, Integer>("Score")
        );

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Score, String>("Name")
        );

        // Set Score sort type
        scoreCol.setSortType(TableColumn.SortType.DESCENDING);

        // Prevent user from reordering table
        columnReorder(highScoreTable, idCol, nameCol, scoreCol, dateCol);

        // Sort by score
        highScoreTable.getSortOrder().add(scoreCol);

        scoreCol.setSortable(false);
    }
}
