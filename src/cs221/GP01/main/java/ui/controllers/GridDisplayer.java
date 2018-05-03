/*
 * @(#) GridDisplayer.java 1.1 2018/02/24
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

import cs221.GP01.main.java.ui.Settings;
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
 * A class to help GameView display the grids.
 *
 * The grids are displayed in three forms which are all handled in this class.
 * The Blocks change colour and functionality based on a state machine, and get added to the textField when clicked on.
 *
 *
 * @author Nathan Williams
 * @version 1.1
 */
public class GridDisplayer {

    // Holds the previous x and y coords of the mouse to check the change in x and y.
    private double oldMouseX, oldMouseY;

    // Pre defined rotations for the 3D view.
    private Rotate rotateAboutX = new Rotate(-20, 40, 40, 40, Rotate.X_AXIS);
    private Rotate rotateAboutY = new Rotate(-45, 40, 40, 40, Rotate.Y_AXIS);


    //text field to build the word in.
    private TextField textField;


    //FXML Object to display the grids in
    private SubScene subScene;
    private Group threeDGridGroup;
    private BorderPane back;
    private GridPane[] twoDGrid, twoFiveDGrid;
    private Button btnExplode;


    //storage for the labels and boxes(Bblocks) with letters in them.
    private Label[][][] labelCube;
    private Box[][][] boxCube, boxCube3;

    //a boolean to keep track of the current exploded state.
    private boolean toggle;


    /**
     * constructor for gird displayer with all the UI elements used to display the grid
     *
     * @param field the text field to build the word in
     * @param two the 2D grid elements
     * @param twoFive the 2.5D view elements
     * @param sub the sub scene for the 3D view
     * @param group The group to hold the 3D view JavaFx objects
     * @param b the container of the subscene for the 3D view
     * @param explode the explode button so Icon can be changed based on if the cube is exploded or not.
     */
    public GridDisplayer(TextField field, GridPane[] two, GridPane[] twoFive, SubScene sub, Group group, BorderPane b, Button explode) {
        textField = field;
        twoDGrid = two;
        twoFiveDGrid = twoFive;
        subScene = sub;
        threeDGridGroup = group;
        back = b;
        btnExplode = explode;
    }

    /**
     * sets up 3d enviroment, loads letters into labels and boxes, adds them to the display.
     *
     * @param letters a 3x3x3 array of letters to populate the grid views.
     */
    public void buildGrids(String[][][] letters) {
        threeDGridGroup.getChildren().clear();
        for (int i = 0; i < 3; i++) {
            twoDGrid[i].getChildren().clear();
            twoFiveDGrid[i].getChildren().clear();
        }


        //sets up the 3D enviroment
        Camera camera = new ParallelCamera();
        camera.getTransforms().addAll(
                rotateAboutX,
                rotateAboutY,
                new Translate(-150, -100, 0)
        );
        threeDGridGroup.getChildren().add(camera);
        subScene.setCamera(camera);

        threeDGridGroup.getChildren().add(new AmbientLight());

        //sets up the right mouse button for rotating the cube
        back.setOnMouseDragged(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                double mouseX = e.getY();
                double mouseY = e.getX();
                if (oldMouseY != 0 && oldMouseX != 0) {
                    rotateAboutX.setAngle((rotateAboutX.getAngle() + oldMouseX - mouseX) % 360);
                    rotateAboutY.setAngle((rotateAboutY.getAngle() + mouseY - oldMouseY) % 360);
                }
                oldMouseX = mouseX;
                oldMouseY = mouseY;
            }
        });

        //when the drag is released reset the variables
        back.setOnMouseReleased(
                e -> {
                    if (e.getButton().equals(MouseButton.SECONDARY)) {
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
        if (letters != null) {
            //Create the labels and boxes ready for display
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        //3D
                        Box box = new Box(30, 30, 30);
                        boxCube3[k][i][j] = box;

                        //2.5D
                        Box box1 = new Box(30, 30, 30);
                        box1.setRotationAxis(new Point3D(0, 0, 0));
                        box1.setRotate(0);
                        boxCube[k][i][j] = box1;

                        //2D
                        Label label = new Label(letters[k][i][j]);
                        labelCube[k][i][j] = label;

                        setActive(k, i, j, true);
                    }
                }
            }

            //adds the labels and blocks to the 2d and 2.5d view
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 2; j > -1; j--) { //revered to render the block overlap correctly
                        twoDGrid[k].add(labelCube[k][j][i], i, j);
                        twoFiveDGrid[k].add(boxCube[k][i][j], j, i);

                    }
                }
            }

            //adds the blocks to the 3d view and positions them.
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        threeDGridGroup.getChildren().add(boxCube3[k][i][j]);
                        boxCube3[k][i][j].setTranslateX(k * 40);
                        boxCube3[k][i][j].setTranslateY(i * 40);
                        boxCube3[k][i][j].setTranslateZ(j * 40);
                    }
                }
            }
        }
    }

    /**
     * When a block is clicked this method is called to update
     * the states of all the blocks in the views based on if it
     * was the blocked clicked, a neighbour of the block or it isn't.
     *
     * Adds the letter of the clicked block to the text field.
     *
     * @param k the position of the block that was clicked
     * @param i the position of the block that was clicked
     * @param j the position of the block that was clicked
     */
    private void blockClicked(int k, int i, int j, MouseEvent e) {
        if (e.getButton().equals(MouseButton.PRIMARY)) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {

                        //the block that was clicked
                        if (x == k && y == i && z == j) {
                            setSelected(x, y, z);

                            //is a neighbour of the block that was clicked
                        } else if (isNeighbour(k, i, j, x, y, z)) {
                            setActive(x, y, z, false);

                            //not a neighbour of the block that was clicked
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
                    setActive(x, y, z, true);
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
        labelCube[x][y][z].setStyle("-fx-background-color:" + Settings.getInstance().getCurrentlySelectedColor() + ";");
        labelCube[x][y][z].setOnMouseClicked(null);

        PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(), Settings.getInstance().getCurrentlySelectedColor());

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
        if (!labelCube[x][y][z].getStyle().contains("-fx-background-color:" + Settings.getInstance().getAlreadySelectedColor() + ";")) {
            //2d
            labelCube[x][y][z].setStyle("-fx-background-color:" + Settings.getInstance().getUnavailableColor() + ";");
            labelCube[x][y][z].setOnMouseClicked(null);


            PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(), Settings.getInstance().getUnavailableColor());
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
     * @param x        position of the block in question
     * @param y        position of the block in question
     * @param z        position of the block in question
     * @param override overides any other states ready to begin selecting again
     */

    private void setActive(int x, int y, int z, boolean override) {
        if (labelCube[x][y][z].getStyle().contains("-fx-background-color:" + Settings.getInstance().getCurrentlySelectedColor() + ";") && !override) {

            //2d
            labelCube[x][y][z].setStyle("-fx-background-color:" + Settings.getInstance().getAlreadySelectedColor() + ";");

            PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(), Settings.getInstance().getAlreadySelectedColor());
            //2.5d
            boxCube[x][y][z].setMaterial(mat);
            //3d
            boxCube3[x][y][z].setMaterial(mat);

        } else if (!labelCube[x][y][z].getStyle().contains("-fx-background-color:" + Settings.getInstance().getAlreadySelectedColor() + ";") || override) {
            //2d
            labelCube[x][y][z].setStyle("-fx-background-color:" + Settings.getInstance().getAvailableColor() + ";");
            labelCube[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z, e));
            //2.5d
            PhongMaterial mat = generateMaterial(labelCube[x][y][z].getText(), Settings.getInstance().getAvailableColor());

            boxCube[x][y][z].setMaterial(mat);
            boxCube[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z, e));
            //3d
            boxCube3[x][y][z].setMaterial(mat);
            boxCube3[x][y][z].setOnMouseClicked(e -> blockClicked(x, y, z, e));
        }
    }


    /**
     * generates a material with a letter on it to display a block
     *
     * @param letter the letter to display
     * @param color  the background color
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
        grid.setStyle("-fx-background-color: " + color + ";");
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
     * @author Sam Jones
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
        int neighborsX, neighborsY, neighborsZ;
        for (int i = -1; i < 2; i++) {
            neighborsX = selectedX;
            neighborsX += i;
            for (int j = -1; j < 2; j++) {
                neighborsY = selectedY;
                neighborsY += j;
                for (int k = -1; k < 2; k++) {
                    neighborsZ = selectedZ;
                    neighborsZ += k;
                    if (possibleX == neighborsX && possibleY == neighborsY && possibleZ == neighborsZ)
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Toggles the explode of the 3D view of the cube.
     */
    public void toggleExplode() {
       //disables the button while the animation is happening.
        btnExplode.setDisable(true);


        // Clear explode button styles
        btnExplode.getStyleClass().clear();

        // Switch buttons between explode/implode
        if (toggle) {
            btnExplode.getStyleClass().add("explode");
        } else {
            btnExplode.getStyleClass().add("implode");
        }

        //re enables the button at the end of the animation.
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        btnExplode.setDisable(false);
                    }
                },
                400
        );

        //creates an animation timeline.
        Timeline timeline = new Timeline();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addAnimation(timeline, boxCube3[0][i][j], toggle, 0, i, j);
                addAnimation(timeline, boxCube3[2][i][j], !toggle, 2, i, j);
            }
        }
        //runs the timeline
        timeline.play();

        //updates the saved state variable.
        toggle = !toggle;
    }

    /**
     * adds an animation to the timeline for the specified cube.
     *
     * @param timeline the timeline to add the animation to
     * @param box the box to be moved.
     * @param left the direction to move, true if left, false if right
     * @param x the position of the block
     * @param y the position of the block
     * @param z the position of the block
     */
    private void addAnimation(Timeline timeline, Box box, boolean left, int x, int y, int z) {
        //The distance the BLock should move.
        int displacment = 60;

        if (left) {
            //move the block left
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(box.translateXProperty(), box.getTranslateX())),
                    new KeyFrame(new Duration(400), new KeyValue(box.translateXProperty(), box.getTranslateX() + displacment))
            );
        } else {
            //move the block right
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(box.translateXProperty(), box.getTranslateX())),
                    new KeyFrame(new Duration(400), new KeyValue(box.translateXProperty(), box.getTranslateX() - displacment))
            );
        }
    }


    /**
     * Checks if the block is active or not for testing purposes.
     *
     * @param x position of the block
     * @param y position of the block
     * @param z position of the block
     * @return whether the block is active or not
     */
    public boolean isActive(int x, int y, int z) {
        return labelCube[x][y][z].getStyle().contains("-fx-background-color:" + Settings.getInstance().getAvailableColor() + ";");
    }

    /**
     * Changes the block to active state for testing
     *
     * @param x position of the block
     * @param y position of the block
     * @param z position of the block
     */
    public void setInActiveP(int x, int y, int z) {
        setInActive(x,y,z);
    }
}