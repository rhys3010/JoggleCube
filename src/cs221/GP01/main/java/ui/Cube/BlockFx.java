package cs221.GP01.main.java.ui.Cube;

import javafx.geometry.HPos;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static cs221.GP01.main.java.ui.Cube.BlockState.*;

public class BlockFx {

    private String currentlySelectedColor = "#38aa38";
    private String availableColor = "#30599b";
    private String alreadySelectedColor = "#54ad54";
    private String unavailableColor = "#595959";

    private Box d3;
    private Box d25;
    private Label d2;

    private String letter;

    private Map<BlockState,PhongMaterial> mats;

    private BlockState state;

    public BlockFx(String letter){
        d3 = new Box(30, 30, 30);
        d25 = new Box(30, 30, 30);
        d25.setRotationAxis(new Point3D(0,0,0));
        d25.setRotate(0);
        d2 = new Label(letter);
        this.letter = letter;
        state = ACTIVE;

        mats = new HashMap<>();
        mats.put(ACTIVE,generateMaterial(letter, availableColor));
        mats.put(SELECTED,generateMaterial(letter, currentlySelectedColor));
        mats.put(UNAVAILABLE,generateMaterial(letter, unavailableColor));
        mats.put(PREV_SELECTED,generateMaterial(letter, alreadySelectedColor));
    }

    public void changeState(BlockState state){

        d3.setMaterial(mats.get(state));
        d25.setMaterial(mats.get(state));
        d2.setStyle("-fx-background-color:" + availableColor+ ";");
        



        this.state = state;
    }




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
}
