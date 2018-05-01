package cs221.GP01.test.java.ui;
import cs221.GP01.main.java.model.IScore;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.controllers.HighScore;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HighScoreTest {

    HighScore score = HighScore.getInstance();
    JoggleCube cube = JoggleCube.getInstance();
    ObservableList<IScore> highScorePageLabel = null;
    ObservableList<IScore> highScoreTable = null;

    @BeforeEach
    public void reset() {
        score.prepView();

    }

    @Test
    public void prepView() {

        score.prepView();
        assertEquals(cube.getOverallHighScores(), score.getOverallScores());
        assertEquals(cube.getCurrentCubeHighScores(), score.getCurrentCubeHighScores());

       // @Test
       // private static <S, T> void columnReorder () {

        }

       // @Test
       // public void onChanged(){

       // }

    @Test
    private void populateTable(){

        assertEquals(score.setText(), highScorePageLabel.isEmpty());
        assertEquals(score.setItems(), highScoreTable.isEmpty());
    }

    @Test
    public void nextPage(){

        score.nextPage();


    }

    @Test
    public void previousPage(){

    }

    @Test
    public void changePage(){

        score.changePage();
        assertEquals("Current Cube", score.getText());
        assertEquals("All cubes", score.getText());
    }

    @Test
    public void initialize(){


    }
}
