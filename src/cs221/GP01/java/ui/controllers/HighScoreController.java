/*
 * @(#) HighScoreController.java 1.0 2018/02/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.model.HighScore;
import cs221.GP01.java.ui.UIController;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * HighScoreController - A class that controls the HighScore scene that is defined in HighScore.fxml
 * Load and display highscores from another class
 * todo delete temp highscore class and establish a new one
 *
 * <p>
 * Used with HighScore.fxml
 * todo improve this description
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class HighScoreController extends BaseScreenController implements Initializable{

    /**
     * High Score Table
     */
    @FXML
    private TableView<HighScore> highScoreTable;

    /**
     * Table Columns
     */
    @FXML
    private TableColumn idCol, dateCol, scoreCol, nameCol;

    /**
     * The label of the highscore page
     */
    @FXML
    private Label highScorePageLabel;

    /**
     * Highscores for all cubes
     */
    private ObservableList<HighScore> overallHighScores;

    /**
     * Highscores for the currently played cube
     */
    private ObservableList<HighScore> currentCubeHighScores;

    /**
     * The index of the current high score page
     */
    private int currentHighScorePageIndex = 0;

    /**
     * The different highscore pages that can be displayed
     */
    private final String HIGH_SCORE_PAGES[] = {"Overall", "Current"};

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController the UIController between UI and Backend.
     */
    public HighScoreController(UIController UIController){
        super(UIController);
        overallHighScores = UIController.getJoggleCube().getOverallHighScores();
        currentCubeHighScores = UIController.getJoggleCube().getCurrentCubeHighScores();
    }


    /**
     * Populate HighScore table with highscore data at end screen load
     * todo Add Rank Number to table
     *
     * todo add a loaded grid High score tab that is only displayed if UIController.getHandleInput().getLoadedGridHighScores() does not return a null and load in the data.
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

        // Prevent user from reordering table
        columnReorder(highScoreTable, idCol, nameCol, scoreCol, dateCol);

        // Sort by score
        highScoreTable.getSortOrder().add(scoreCol);
        highScoreTable.sort();
        scoreCol.setSortable(false);

        // Populate the Table with filtered high scores
        populateTable(overallHighScores);
    }



    /**
     * Stop users from being able to reorder table columns (temporary fix)
     * Solution used here: https://bittlife.com/javafx-disable-column-reorder-tableview/
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


    /**
     * Helper function to populate the highscore table
     * @param list - a filtered list of the highscores to populate the table with
     */
    private void populateTable(ObservableList<HighScore> list){

        // Clear table
        //highScoreTable.getItems().clear();

        //filters list to the top 10
        FilteredList<HighScore> filteredList = new FilteredList<HighScore>(
                list,
                HighScore -> list.indexOf(HighScore) < 10
        );

        highScoreTable.setItems(filteredList);
    }

    /**
     * Advance to the next page in the Highscores list
     * todo consider other ways of doing this
     */
    @FXML
    public void nextPage(){
        currentHighScorePageIndex++;

        // Use modulo to circulate through the list
        currentHighScorePageIndex %= HIGH_SCORE_PAGES.length;

        changePage();
    }

    /**
     * Go the previous page in the highscores list
     */
    @FXML
    public void previousPage(){
        currentHighScorePageIndex--;

        // Java made this happen, I'm sorry :(
        // (Java always returns the remainder of a modulo operation so it doesn't do well with modulo of negative numbers, that's why the below line is such a cluster-fun)
        currentHighScorePageIndex  = (((currentHighScorePageIndex % HIGH_SCORE_PAGES.length) + HIGH_SCORE_PAGES.length) % HIGH_SCORE_PAGES.length);

        changePage();
    }

    /**
     * Utility function to change the page of the high score table
     */
    private void changePage(){
        if(HIGH_SCORE_PAGES[currentHighScorePageIndex].equals("Overall")){
            populateTable(overallHighScores);
            highScorePageLabel.setText("All Cubes");
        }else if(HIGH_SCORE_PAGES[currentHighScorePageIndex].equals("Current")) {
            populateTable(currentCubeHighScores);
            highScorePageLabel.setText("Current Cube");
        }
    }

}
