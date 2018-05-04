package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.model.IJoggleCube;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.INavigation;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.End;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.HighScore;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EndTest extends ApplicationTest{

    End end = End.getInstance();
    IJoggleCube cube = JoggleCube.getInstance();
    INavigation nav = Navigation.getInstance();

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }


    @BeforeEach
    public void reset() {
        end.prepView();
        end.setParentController(GameView.getInstance());
    }

    @Test
    public void testPrepView() {
        end.prepView();
        assertEquals(cube.getScore() + "", end.getScore());
        assertEquals(cube.getHighestScore() + "", end.getHighScore());
    }

    @Test
    public void btnHighScoreClickedTest() {
        Platform.runLater(()->end.btnHighScoreClicked());
        clickOn(400,400);
        assertEquals( HighScore.getInstance().getRoot(), nav.getMain().getRoot());

    }

    @Test
    public void btnMenuClickedTest() {
        Platform.runLater(()->end.btnMenuClicked());
        assertEquals( Start.getInstance().getRoot(),nav.getMain().getRoot());

    }

    @Test
    public void btnSaveClicked() throws InterruptedException {

        JoggleCube.getInstance().generateRandomGrid();
        Platform.runLater(()->end.btnSaveClicked());
        Platform.runLater(()->end.getInputDialog().getTextInputDialog().setContentText(""));
        clickOn(500,500);
        Button button = (Button) end.getInputDialog().getTextInputDialog().getDialogPane().lookupButton(ButtonType.OK);
        clickOn(button);

        Platform.runLater(()->end.getInformationDialog().getInformationDialog().close());
        

    }


}
