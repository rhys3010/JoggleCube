/*
 * @(#) GridDisplayer.java 1.0 2018/02/24
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;


/**
 *
 * A class to help GameController display the grids.
 *
 * todo optimize
 *
 * @author Nathan Williams
 * @version 1.0
 */
public class GridDisplayer {

    /**
     * COLOR VARIABLES
     */
    private String currentlySelectedColor = "#38aa38";
    private String availableColor = "#30599b";
    private String alreadySelectedColor = "#54ad54";
    private String unavailableColor = "#595959";



    private double oldMouseX, oldMouseY;

    private Rotate rotateAboutX = new Rotate(-20,40,40,40, Rotate.X_AXIS);
    private Rotate rotateAboutY = new Rotate(-45,40,40,40, Rotate.Y_AXIS);

    private TextField textField;


    //FXML stuff
    private SubScene subScene;
    private Group groupy;
    private BorderPane back;
    private GridPane[] twoDGrid,twoFiveDGrid;
    private Button explodeIcon;


    //storage for the labels and boxes with letters in them.
    private Label[][][] labelCube;
    private Box[][][] boxCube,boxCube3;

    public GridDisplayer(TextField field, GridPane[] two, GridPane[] twoFive, SubScene sub, Group group, BorderPane b, Button explode) {
        textField = field;
        twoDGrid = two;
        twoFiveDGrid = twoFive;
        subScene = sub;
        groupy = group;
        back = b;
        explodeIcon = explode;
    }

    /**
     * sets up 3d enviroment, loads letters into labels and boxes, adds them to the display.
     *
     * @param letters
     */
    public void buildGrids(String[][][] letters) {
        groupy.getChildren().clear();
        for(int i = 0; i < 3; i++){
            twoDGrid[i].getChildren().clear();
            twoFiveDGrid[i].getChildren().clear();
        }



        //sets up the 3D enviroment
        Camera camera = new ParallelCamera();
        camera.getTransforms().addAll (
                rotateAboutX,
                rotateAboutY,
                new Translate(-150, -100, 0)
        );
        groupy.getChildren().add(camera);
        subScene.setCamera(camera);

        //sets up the right mouse button for rotating the cube
        back.setOnMouseDragged( e ->{
            if(e.getButton().equals(MouseButton.SECONDARY)){
                double mouseX = e.getY();
                double mouseY = e.getX();
                if(oldMouseY != 0 && oldMouseX != 0){
                    rotateAboutX.setAngle((rotateAboutX.getAngle() + oldMouseX-mouseX) % 360);
                    rotateAboutY.setAngle((rotateAboutY.getAngle() + mouseY-oldMouseY) % 360);
                }
                oldMouseX = mouseX;
                oldMouseY = mouseY;
            }
        });

        //when the drg is released reset the variables
        back.setOnMouseReleased(
            e -> {
                if(e.getButton().equals(MouseButton.SECONDARY)) {
                    oldMouseX = 0;
                    oldMouseY = 0;
                }
            }
        );

        //creates space ready for the boxes and labels
        labelCube = new Label[3][3][3];
        boxCube = new Box[3][3][3];
        boxCube3 = new Box[3][3][3];

        //checks the letters actually exist before making a fool of ones self and trying to display them.
        if(letters != null) {
            //Create the labels and boxes ready for display
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //3D
                        Box box = new Box(30, 30, 30);
                        boxCube3[k][i][j] = box;

                        //2.5D
                        Box box1 = new Box(30, 30, 30);
                        box1.setRotationAxis(new Point3D(0,0,0));
                        box1.setRotate(0);
                        boxCube[k][i][j] = box1;

                        //2D
                        Label label = new Label(letters[k][i][j]);
                        labelCube[k][i][j] = label;

                        setActive(k,i,j,true);
                    }
                }
            }

            //adds the labels and blocks to the 2d and 2.5d view
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 2; j > -1; j--) { //revered to render the block overlap correctly
                        twoDGrid[k].add(labelCube[k][j][i], i, j);
                        twoFiveDGrid[k].add(boxCube[k][i][j],j,i);

                    }
                }
            }

            //adds the blocks to the 3d view and positions them.
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        groupy.getChildren().add(boxCube3[k][i][j]);
                        boxCube3[k][i][j].setTranslateX(k*40);
                        boxCube3[k][i][j].setTranslateY(i*40);
                        boxCube3[k][i][j].setTranslateZ(j*40);
                    }
                }
            }
        }
    }

    /**
     * When a block is clicked this method is called.
     *
     * @param k the position of the block that called the method
     * @param i the position of the block that called the method
     * @param j the position of the block that called the method
     */
    private void blockClicked(int k, int i, int j, MouseEvent e){
        if(e.getButton().equals(MouseButton.PRIMARY)) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        if (x == k && y == i && z == j) {
                            setSelected(x, y, z);
                        } else if (isNeighbour(k, i, j, x, y, z)) {
                            setActive(x, y, z, false);
                        } else {
                            setInActive(x, y, z);
                        }
                    }
                }
            }
            textField.appendText(labelCube[k][i][j].getText());
        }
    }

    /**
     * resets the cube so they can all be clicked
     */
    public void setAllActive() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    setActive(x,y,z,true);
                }
            }
        }
    }

    /**
     * sets the state of the block to selected
     *
     * @param x position of the block in question
     * @param y position of the block in question
     * @param z position of the block in question
     */

    private void setSelected(int x, int y, int z) {

        //2d
        labelCube[x][y][z].setStyle("-fx-background-color:" + currentlySelectedColor + ";");
        labelCube[x][y][z].setOnMouseClicked(null);

        PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(),currentlySelectedColor);

        //2.5d
        boxCube[x][y][z].setMaterial(mat);
        boxCube[x][y][z].setOnMouseClicked(null);

        //3d
        boxCube3[x][y][z].setMaterial(mat);
        boxCube3[x][y][z].setOnMouseClicked(null);
    }


    /**
     * sets the state of the block to in-active
     *
     * @param x position of the block in question
     * @param y position of the block in question
     * @param z position of the block in question
     */

    private void setInActive(int x, int y, int z) {
        if(!labelCube[x][y][z].getStyle().contains("-fx-background-color:" + alreadySelectedColor + ";")){
            //2d
            labelCube[x][y][z].setStyle("-fx-background-color:" + unavailableColor + ";");
            labelCube[x][y][z].setOnMouseClicked(null);


            PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(), unavailableColor);
            //2.5d
            boxCube[x][y][z].setMaterial(mat);
            boxCube[x][y][z].setOnMouseClicked(null);

            //3d
            boxCube3[x][y][z].setMaterial(mat);
            boxCube3[x][y][z].setOnMouseClicked(null);

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
        if(labelCube[x][y][z].getStyle().contains("-fx-background-color:" + currentlySelectedColor + ";") && !override){

            //2d
            labelCube[x][y][z].setStyle("-fx-background-color:" + alreadySelectedColor +";");

            PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(), alreadySelectedColor);
            //2.5d
            boxCube[x][y][z].setMaterial(mat);
            //3d
            boxCube3[x][y][z].setMaterial(mat);

        } else if(!labelCube[x][y][z].getStyle().contains("-fx-background-color:" + alreadySelectedColor + ";") || override) {
            //2d
            labelCube[x][y][z].setStyle("-fx-background-color:" + availableColor + ";");
            labelCube[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z,e));
            //2.5d
            PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(),availableColor);

            boxCube[x][y][z].setMaterial(mat);
            boxCube[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z,e));
            //3d
            boxCube3[x][y][z].setMaterial(mat);
            boxCube3[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z,e));
        }
    }


    /**
     * generates a material with a letter on it to display a block
     *
     * @param letter the letter to display
     * @param color the background color
     * @return a material with a letter on it ready to display
     */
    private PhongMaterial generateMaterial(String letter, String color) {
        //Create a container
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        //create a label with the letter
        Label label = new Label(letter);
        label.setStyle("-fx-font-size: 19; -fx-text-fill: white;");
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

    private boolean toggle;

    public void toggleExplode() {

        // Clear explode button class
        explodeIcon.getStyleClass().clear();

        // Switch buttons between explode/implode
        if(toggle) {
            explodeIcon.getStyleClass().add("explode");
        }else{
            explodeIcon.getStyleClass().add("implode");
        }


        Timeline timeline = new Timeline();
        for(int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                addAnimation(timeline,boxCube3[0][i][j],toggle,0,i,j);
                addAnimation(timeline,boxCube3[2][i][j],!toggle,2,i,j);
            }
        }
        timeline.play();
        toggle = !toggle;
    }

    private void addAnimation(Timeline timeline, Box box,boolean left,int x,int y,int z) {
        int displacment = 60;
        if(left){
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,new KeyValue(box.translateXProperty(), box.getTranslateX())),
                    new KeyFrame(new Duration(400),new KeyValue(box.translateXProperty(), box.getTranslateX() + displacment))
            );
        } else {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(box.translateXProperty(), box.getTranslateX())),
                    new KeyFrame(new Duration(400), new KeyValue(box.translateXProperty(), box.getTranslateX() - displacment))
            );
        }

    }
}
