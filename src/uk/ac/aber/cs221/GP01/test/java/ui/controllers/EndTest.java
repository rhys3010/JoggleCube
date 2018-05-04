/*
   * @(#) EndTest.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.model.IJoggleCube;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.INavigation;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.End;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.HighScore;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the EndTest class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
public class EndTest extends ApplicationTest {

    End end = End.getInstance();
    IJoggleCube cube = JoggleCube.getInstance();
    INavigation nav = Navigation.getInstance();

    /**
     * Creates instance of game for testing
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);
    }

    /**
     * Resets game before each test
     */
    @BeforeEach
    public void reset() {
        end.prepView();
        end.setParentController(GameView.getInstance());
    }

    /**
     * Tests score and highscore
     */
    @Test
    public void testPrepView() {
        end.prepView();
        assertEquals(cube.getScore() + "", end.getScore());
        assertEquals(cube.getHighestScore() + "", end.getHighScore());
    }

    /**
     * Tests highscore menu button
     */
    @Test
    public void btnHighScoreClickedTest() {
        Platform.runLater(() -> end.btnHighScoreClicked());
        clickOn(400, 400);
        assertEquals(HighScore.getInstance().getRoot(), nav.getMain().getRoot());
    }

    /**
     * Tests home menu button
     */
    @Test
    public void btnMenuClickedTest() {
        Platform.runLater(() -> end.btnMenuClicked());
        assertEquals(Start.getInstance().getRoot(), nav.getMain().getRoot());

    }

    /**
     * Tests save menu button
     *
     * @throws InterruptedException
     */
    @Test
    public void btnSaveClicked() throws InterruptedException {

        JoggleCube.getInstance().generateRandomGrid();
        Platform.runLater(() -> end.btnSaveClicked());
        Platform.runLater(() -> end.getInputDialog().getTextInputDialog().setContentText(""));
        clickOn(500, 500);
        Button button = (Button) end.getInputDialog().getTextInputDialog().getDialogPane().lookupButton(ButtonType.OK);
        clickOn(button);

        Platform.runLater(() -> end.getInformationDialog().getInformationDialog().close());
    }
}
