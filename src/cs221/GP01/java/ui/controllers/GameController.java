/*
   * @(#) GameController.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.UIController;
import cs221.GP01.java.ui.ScreenType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * GameController - A class that controls the Game scene that is defined in Game.fxml
 * <p>
 * Used with Game.fxml
 * todo improve this description
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.2  DRAFT
 */
public class GameController implements Initializable{
    /**
     * the parent StackPane in this scene.
     */
    @FXML
    private StackPane root;

    /**
     * The tab pane that contains the cube representations
     */
    @FXML
    private TabPane cubeContainer;

    @FXML
    private ListView<String> foundWordsList;

    private ObservableList<String> foundWords = FXCollections.observableArrayList();

    @FXML
    private GridPane top2d,middle2d,bottom2d,top25d,middle25d,bottom25d;

    @FXML
    private Button btnSubmit;


    /**
     * The main game screen content (cube I/O etc)
     */
    @FXML
    private VBox gameScreen;

    @FXML
    private TextField textField;

    /**
     * An instance of the UIController object to interface with backend
     */
    private UIController UIController;

    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public GameController(UIController UIController){
        this.UIController = UIController;
    }


    private Label[][][] labelCube;
    private Box[][][] boxCube;


    /**
     * When a block is clicked this method is called.
     *
     * @param k the position of the block that called the method
     * @param i the position of the block that called the method
     * @param j the position of the block that called the method
     */
    private void blockClicked(int k, int i, int j){
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    if(x == k && y == i && z == j){
                        setSelected(x,y,z);
                    } else if(isNeighbour(k,i,j,x,y,z)){
                        setActive(x,y,z,false);
                    } else {
                        setInActive(x,y,z);
                    }
                }
            }
        }
        textField.appendText(labelCube[k][i][j].getText());
    }

    /**
     * sets the state of the block to selected
     *
     * @param x position of the block in question
     * @param y position of the block in question
     * @param z position of the block in question
     */

    private void setSelected(int x, int y, int z) {
        labelCube[x][y][z].setStyle("-fx-background-color:#ff0000;");
        labelCube[x][y][z].setOnMouseClicked(null);

        boxCube[x][y][z].setMaterial(generateMaterial(labelCube[x][y][z].getText(),"#ff0000"));
        boxCube[x][y][z].setOnMouseClicked(null);
    }


    /**
     * sets the state of the block to in-active
     *
     * @param x position of the block in question
     * @param y position of the block in question
     * @param z position of the block in question
     */

    private void setInActive(int x, int y, int z) {
        if(!labelCube[x][y][z].getStyle().contains("-fx-background-color:#550000;")){
            labelCube[x][y][z].setStyle("-fx-background-color:#566377;");
            labelCube[x][y][z].setOnMouseClicked(null);

            boxCube[x][y][z].setMaterial(generateMaterial(labelCube[x][y][z].getText(),"#566377"));
            boxCube[x][y][z].setOnMouseClicked(null);
        }
    }

    /**
     * sets the state of the block to Active allowing the user to click on it
     *
     * @param x position of the block in question
     * @param y position of the block in question
     * @param z position of the block in question
     * @param override overides any other states ready to begin selecting again
     */

    private void setActive(int x, int y, int z, boolean override) {
        if(labelCube[x][y][z].getStyle().contains("-fx-background-color:#ff0000;") && !override){
            labelCube[x][y][z].setStyle("-fx-background-color:#550000;");
            boxCube[x][y][z].setMaterial(generateMaterial(labelCube[x][y][z].getText(),"#550000"));

        } else if(!labelCube[x][y][z].getStyle().contains("-fx-background-color:#550000;") || override) {
            labelCube[x][y][z].setStyle("-fx-background-color:#2980b9;");
            labelCube[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z));

            boxCube[x][y][z].setMaterial(generateMaterial(labelCube[x][y][z].getText(),"#2980b9"));
            boxCube[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z));
        }
    }

    private void buildGrids(String[][][] letters) {
        //creates space ready for the boxes and labels
        labelCube = new Label[3][3][3];
        boxCube = new Box[3][3][3];

        //easy access of the three planes
        GridPane[] twoDGrid = {top2d, middle2d, bottom2d};
        GridPane[] twoFiveDGrid = {top25d, middle25d, bottom25d};

        //links the ListView with the list
        foundWordsList.setItems(foundWords);

        //stops user editing the textField todo test this
        textField.setDisable(true);

        //checks the letters actually exist before making a fool of ones self and trying to display them.
        if(letters[0][0][0] != null) {
            //Create the labels and boxes ready for display
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Box box = new Box(30, 30, 30);
                        box.setRotationAxis(new Point3D(0,0,0));
                        box.setRotate(0);
                        boxCube[k][i][j] = box;

                        Label label = new Label(letters[k][i][j]);
                        labelCube[k][i][j] = label;

                        setActive(k,i,j,true);
                    }
                }
            }

            //render the labels and blocks (adds them to relevant grids)
            for (int k = 0; k < 3; k++) {
                //rotates the grids ready for display todo move to FXML
                twoFiveDGrid[k].setRotationAxis(new Point3D(0.3,-1.0,0));
                twoFiveDGrid[k].setRotate(45);

                for (int i = 0; i < 3; i++) {
                    for (int j = 2; j > -1; j--) {
                        twoDGrid[k].add(labelCube[k][j][i], i, j);
                        twoFiveDGrid[k].add(boxCube[k][j][i],j,i);
                    }
                }
            }
        }
    }


    /**
     * generates a material with a letter on it to display a block
     *
     * @param letter the letter to display
     * @param color the background color
     * @return a material with a letter on it ready to display
     */
    private PhongMaterial generateMaterial(String letter,String color) {
        //Create a container
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        //create a label with the letter
        Label label = new Label(letter);
        label.setStyle("-fx-font-size: 22; -fx-text-fill:white;");
        GridPane.setHalignment(label, HPos.CENTER);

        //add the label to the grid
        grid.add(label, 0, 0);

        //set the gridlines to show
        grid.setGridLinesVisible(true);

        //set the column and row sizes iof the grid
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100);
        grid.getColumnConstraints().addAll(col1);
        RowConstraints row1 = new RowConstraints();
        grid.getRowConstraints().addAll(row1);
        row1.setPercentHeight(100);

        //set height and width of the grid
        grid.setPrefSize(30, 30);
        //give the grid a background colour
        grid.setStyle("-fx-background-color: "+ color + ";");
        //create a scene to render the grid.
        Scene tmpScene = new Scene(grid);

        //take a snapshot of the grid and turn into and image
        Image net = grid.snapshot(null, null);
        //create the material
        PhongMaterial mat = new PhongMaterial();
        //set the material to show the image
        mat.setDiffuseMap(net);

        return mat;
    }

    /**
     * Works out if the selected blocks are neighbors.
     *
     * @param selectedX position of the selected block
     * @param selectedY position of the selected block
     * @param selectedZ position of the selected block
     * @param possibleX position of the possible neighbor block
     * @param possibleY position of the possible neighbor block
     * @param possibleZ position of the possible neighbor block
     * @return whether the two blocks are neighbors or not
     */
    private boolean isNeighbour(int selectedX, int selectedY, int selectedZ, int possibleX, int possibleY, int possibleZ) {
        int neighborsX,neighborsY,neighborsZ;
        for(int i = -1; i<2; i++) {
            neighborsX = selectedX;
            neighborsX += i;
            for (int j = -1; j < 2; j++) {
                neighborsY = selectedY;
                neighborsY += j;
                for (int k = -1; k < 2; k++) {
                    neighborsZ = selectedZ;
                    neighborsZ += k;
                    if(possibleX == neighborsX && possibleY == neighborsY && possibleZ == neighborsZ)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * When the End Game button is clicked it will load the EndGui scene.
     */
    @FXML
    private void btnEndGameClicked() {
        UIController.getScreenController().show(ScreenType.END);
        UIController.getJoggleCube().endGame();
    }

    @FXML
    private void btnSubmitClicked() {
        if(!textField.getText().equals("")) {
            if (UIController.getJoggleCube().testWordValidity(textField.getText())) {
                foundWords.add(textField.getText());
                textField.setText("");
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        for (int z = 0; z < 3; z++) {
                            setActive(x,y,z,true);
                        }
                    }
                }
                //todo make these css variables
                btnSubmit.setStyle("-fx-background-color:#006600;");
            } else {
                btnSubmit.setStyle("-fx-background-color:#880000;");
            }
        } else {
            btnSubmit.setStyle("-fx-background-color:#880000;");
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        btnSubmit.setStyle("-fx-background-color:-fx-tertiary-color;");
                    }
                },
                1000
        );
    }

    //todo add method to change the submit buttons colour back when the the mouse is next clicked maybe?


    /**
     * Handle the settings button being clicked
     */
    @FXML
    private void btnSettingsClicked(){
        UIController.getScreenController().show(ScreenType.SETTINGS);
    }

    /**
     * Initialize game screen
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        buildGrids(UIController.getJoggleCube().getCubeData());
        //todo create an instance of timer and start it to keep updating th label
    }


    /**
     * Get the root node of the FXML
     * @return root - the root node
     */
    public StackPane getRootNode(){
        return root;
    }

    /**
     * Get the game screen
     * @return gameScreen - the FXML node of the game screen
     */
    public VBox getGameScreen(){
        return gameScreen;
    }

    /**
     * Get the cube container tab pane
     * @return cubeContainer - the FXML node for tabpane
     */
    public TabPane getCubeContainer(){
        return cubeContainer;
    }
}
