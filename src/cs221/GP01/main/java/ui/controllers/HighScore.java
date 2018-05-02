/*
 * @(#) HighScoreController.java 1.1 2018/02/12
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.model.IScore;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.ScreenType;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * HighScore - A class that controls the IScore scene that is defined in IScore.fxml
 * Load and display highscores from another class
 * todo delete temp highscore class and establish a new one
 *
 * <p>
 * Used with IScore.fxml
 * todo improve this description
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21@aber.ac.uk)
 * @version 1.1
 */
public class HighScore extends BaseScreen implements Initializable, INeedPrep {


    private static HighScore highScoreView;

    private HighScore(){}

    public static HighScore getInstance(){
        if(highScoreView == null){
            synchronized (HighScore.class){
                if(highScoreView == null){
                    highScoreView = new HighScore();
                }
            }
        }
        return highScoreView;
    }

    /**
     * High IScore Table
     */
    @FXML
    private TableView<IScore> highScoreTable;

    /**
     * Table Columns
     */
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn<IScore, String> dateCol;
    @FXML
    private TableColumn scoreCol;
    @FXML
    private TableColumn<IScore, String> nameCol;

    /**
     * The label of the highscore page
     */
    @FXML
    private Label highScorePageLabel;

    /**
     * Highscores for all cubes
     */
    private ObservableList<IScore> overallScores;

    /**
     * Highscores for the currently played cube
     */
    private ObservableList<IScore> currentCubeScores;

    /**
     * The different highscore pages that can be displayed
     */
    private final String HIGH_SCORE_PAGES[] = {"Overall", "Current"};

    @FXML
    private Button leftPageNav, rightPageNav;


    /**
     * Populate IScore table with highscore data
     *
     */
    public void prepView(){

        overallScores = JoggleCube.getInstance().getOverallHighScores();
        currentCubeScores = JoggleCube.getInstance().getCurrentCubeHighScores();

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

        // Set IScore sort type
        scoreCol.setSortType(TableColumn.SortType.DESCENDING);
        highScoreTable.getSortOrder().add(scoreCol);
        scoreCol.setSortable(true);
        highScoreTable.sort();
    }

    /**
     * Stop users from being able to reorder table columns (temporary fix)
     * Solution used here: https://bittlife.com/javafx-disable-column-reorder-tableview/
     */
    @SuppressWarnings("unchecked")
    private static <S, T> void columnReorder(TableView table, TableColumn<S, T>... columns){
        table.getColumns().addListener(new ListChangeListener() {
            boolean suspended;

            @Override
            @SuppressWarnings("unchecked")
            public void onChanged(Change change) {
                change.next();
                if(change.wasReplaced() && !suspended){
                    this.suspended = true;

                    table.getColumns().setAll( (java.lang.Object[])columns);
                    this.suspended = false;
                }
            }
        });
    }


    /**
     * Helper function to populate the highscore table
     *
     * @param list - a filtered list of the highscores to populate the table with
     */
    private void populateTable(ObservableList<IScore> list, String title){

        highScorePageLabel.setText(title);

        highScoreTable.setItems(list);
    }

    /**
     * Utility function to change the page of the high score table
     *
     * todo if not filtering to top 10 compare the data in table: highScoreTable.getItems().equals(overallScores);
     */
    @FXML
    public void changePage(){
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
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources) {
        // Set Default Values for Cells
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("Date")
        );

        scoreCol.setCellValueFactory(
                new PropertyValueFactory<IScore, Integer>("Score")
        );


        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("Name")
        );

        idCol.setCellValueFactory(
                (Callback<TableColumn.CellDataFeatures<IScore, String>, ObservableValue<String>>)
                        p -> new ReadOnlyObjectWrapper(highScoreTable.getItems().indexOf(p.getValue()) + 1 + "")
        );
        // Prevent user from reordering table
        columnReorder(highScoreTable, idCol, nameCol, scoreCol, dateCol);
    }


    //agl6

    public ObservableList<IScore> getOverallScores() {
        return overallScores;
    }

    public ObservableList<IScore> getCurrentCubeHighScores(){
        return currentCubeScores;
    }

    public String getText() {
        return getText();
    }

    public short setText() {
        return setText();
    }

    public short setItems() {
        return setItems();
    }

    public Label highScorePageLabel(){
        return highScorePageLabel();

    }
}
