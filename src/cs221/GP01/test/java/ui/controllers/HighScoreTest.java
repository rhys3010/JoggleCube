package cs221.GP01.test.java.ui.controllers;
import cs221.GP01.main.java.model.IJoggleCube;
import cs221.GP01.main.java.model.IScore;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.controllers.HighScore;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HighScoreTest {

    HighScore score = HighScore.getInstance();
<<<<<<< HEAD:src/cs221/GP01/test/java/ui/HighScoreTest.java
    JoggleCube cube = JoggleCube.getInstance();
    String highScorePageLabel = null;
=======
    ObservableList<IScore> highScorePageLabel = null;
>>>>>>> dev-testing:src/cs221/GP01/test/java/ui/controllers/HighScoreTest.java
    ObservableList<IScore> highScoreTable = null;

    @BeforeEach
    public void reset() {

        score.prepView();

    }

    @Test
    public void prepView() {

        score.prepView();
<<<<<<< HEAD:src/cs221/GP01/test/java/ui/HighScoreTest.java
        assertEquals(cube.getOverallHighScores(), score.getOverallScores());
        assertEquals(cube.getCurrentCubeHighScores(), score.getCurrentCubeHighScores());
    }
=======
        assertEquals(JoggleCube.getInstance().getOverallHighScores(), score.getOverallScores());
        assertEquals(JoggleCube.getInstance().getCurrentCubeHighScores(), score.getCurrentCubeHighScores());

       // @Test
       // private static <S, T> void columnReorder () {

        }

       // @Test
       // public void onChanged(){

       // }
>>>>>>> dev-testing:src/cs221/GP01/test/java/ui/controllers/HighScoreTest.java

    @Test
    private void populateTable(){

        assertEquals(score.setText(), highScorePageLabel.isEmpty());
        assertEquals(score.setItems(), highScoreTable.isEmpty());
    }

    @Test
    public void nextPage(){
        
        assertEquals("All Cubes", highScorePageLabel); // checkin if current page is "All cubes"

<<<<<<< HEAD:src/cs221/GP01/test/java/ui/HighScoreTest.java
        // highScorePageLabel.overallScores(true);
        //assertTrue(highScorePageLabel,"All Cubes");
        //highScorePageLabel.overallScores(false);
        //assertFalse(highScorePageLabel,"All Cubes");
=======
     //   score.nextPage();


    }

    @Test
    public void previousPage(){
>>>>>>> dev-testing:src/cs221/GP01/test/java/ui/controllers/HighScoreTest.java

    }

    @Test
    public void changePage(){

        // check which highScorePageLabel is active and if we can hange it from "All cubes" to "Current Cube"

        score.changePage();

        //highScorePageLabel = "All cubes"; // does same thing as bottom? :D
        //assertEquals(highScorePageLabel, "All cubes");
        assertEquals("All cubes", highScorePageLabel);
        assertEquals("Current Cube", highScorePageLabel);
       // assertEquals("All cubes", score.getText());
       // assertEquals("Current Cube", score.getText());
    }
}
