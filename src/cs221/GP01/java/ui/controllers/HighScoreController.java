/*
   * @(#) HighScoreController.java 1.0 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.model.HighScore;
import cs221.GP01.java.ui.Mediator;
import cs221.GP01.java.ui.ScreenType;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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
public class HighScoreController implements Initializable{



    /**
     * Parent Anchor
     */
    @FXML
    private Node root;

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
     * An instance of the mediator object to interface with backend
     */
    private Mediator mediator;

    /**
     * Constructor to ensure mediator object is passed
     * @param mediator the mediator between UI and Backend.
     */
    public HighScoreController(Mediator mediator){ this.mediator = mediator; }


    /**
     * Populate HighScore table with highscore data at end screen load
     * todo Add Rank Number to table
     *
     * todo add a loaded grid High score tab that is only displayed if mediator.getHandleInput().getLoadedGridHighScores() does not return a null and load in the data.
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

        //gets highScores from the mediator
        ObservableList<HighScore> highScores = mediator.getJoggleCube().getOverallHighScores();

        //filters it to the top 10
        FilteredList<HighScore> filteredHighScores = new FilteredList<HighScore>(
                highScores,
                HighScore -> highScores.indexOf(HighScore) < 10
        );

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
     * @see StartController
     * @throws IOException if Start.fxml doesn't exist
     */
    @FXML
    void btnReturnClicked() throws IOException {
        mediator.getScreenController().show(ScreenType.START);
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
     * Get the root node of the FXML
     * @return root - the root node
     */
    public Node getRoot(){
        return root;
    }
}
