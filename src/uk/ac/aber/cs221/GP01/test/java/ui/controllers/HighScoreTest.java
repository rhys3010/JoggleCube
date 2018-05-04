/*
   * @(#) HighScoreTest.java 1.0 2018/03/05
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.model.IJoggleCube;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.HighScore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Tests for the HighScore class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
public class HighScoreTest extends ApplicationTest {

    HighScore score = HighScore.getInstance();
    IJoggleCube cube = JoggleCube.getInstance();

    /**
     * Reset before each test
     */
    @BeforeEach
    public void reset() {
        score.prepView();
    }

    /**
     * Creates instance of game for testing
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);
    }

    /**
     * Tests page layout
     */
    @Test
    public void prepViewTest() {
        score.prepView();
        assertEquals(cube.getOverallHighScores(), score.getOverallScores());
        assertEquals(cube.getCurrentCubeHighScores(), score.getCurrentCubeHighScores());

        assertFalse(score.getLeftPageNav().isVisible());
        assertFalse(score.getRightPageNav().isVisible());

        assertEquals("All Cubes", score.getLabel());
        assertEquals(JoggleCube.getInstance().getOverallHighScores(), score.getTable().getItems());

    }

    /**
     * Tests highscore tabel
     */
    @Test
    public void populateTest() {

        assertEquals("All Cubes", score.getLabel());
        assertEquals(JoggleCube.getInstance().getOverallHighScores(), score.getTable().getItems());

        score.populateTableTest(null, "zero");

        assertEquals("zero", score.getLabel());
        assertEquals(null, score.getTable().getItems());
    }

    /**
     * Tests transition between highscore pages
     */
    @Test
    public void changePageTest() {

        assertEquals("All Cubes", score.getLabel());

        score.changePage();
        assertEquals("Current Cube", score.getLabel());

        score.changePage();
        assertEquals("All Cubes", score.getLabel());

    }
}
