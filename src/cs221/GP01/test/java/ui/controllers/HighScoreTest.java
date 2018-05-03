/*
   * @(#) StartController.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java.ui.controllers;

import cs221.GP01.Main;
import cs221.GP01.main.java.model.IJoggleCube;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.controllers.HighScore;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the HighScore class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 */
public class HighScoreTest extends ApplicationTest{

    HighScore score = HighScore.getInstance();
    IJoggleCube cube = JoggleCube.getInstance();

    @BeforeEach
    public void reset() {

        score.prepView();

    }

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }

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

    @Test
    public void populateTest() {

        assertEquals("All Cubes", score.getLabel());
        assertEquals(JoggleCube.getInstance().getOverallHighScores(), score.getTable().getItems());

        score.populateTableTest(null, "zero");

        assertEquals("zero", score.getLabel());
        assertEquals(null, score.getTable().getItems());

    }


    @Test
    public void changePageTest() {

        assertEquals("All Cubes", score.getLabel());

        score.changePage();
        assertEquals("Current Cube", score.getLabel());

        score.changePage();
        assertEquals("All Cubes", score.getLabel());

    }
}
