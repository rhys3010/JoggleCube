/*
   * @(#) GameViewTest.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.Dialog;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the GameView class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 */
public class GameViewTest  extends ApplicationTest {

    /**
     * Creates instance of game for testing
     */
    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);
    }


    private GameView view = GameView.getInstance();

    /**
     * Resets game before each test
     */
    @BeforeEach
    public void reset() {
        JoggleCube.getInstance().resetGameState();
        JoggleCube.getInstance().generateRandomGrid();
        Platform.runLater(()-> Navigation.getInstance().switchScreen(ScreenType.GAME));
        Dialog d = GameView.getInstance().getDialog();
        clickOn(700,320);
    }

    /**
     * Tests scoreLabel and timerLabel
     */
    @Test
        public void prepViewTest(){
            assertEquals("0", view.getScoreLabel().getText());
            assertEquals("3:00", view.getTimerLabel().getText());
            assertEquals("", view.getText());
            assertEquals("-fx-text-fill: white;", view.getTimerLabel().getStyle());
        }

    /**
     * Test clear button
     */
    @Test
        public void testBtnClearClicked() {
            Platform.runLater(()->{
                assertEquals("", view.getText());
                for (int x = 0; x < 3; x++)
                    for (int y = 0; y < 3; y++)
                        for (int z = 0; z < 3; z++)
                            assertTrue(view.getGridDisplayer().isActive(x, y, z));

                view.setText("text");
                view.getGridDisplayer().setInActiveP(1,1,1);
                view.getGridDisplayer().setInActiveP(2,2,2);

                assertNotEquals("", view.getText());

                view.btnClearClicked();

                assertEquals("", view.getText());
                for (int x = 0; x < 3; x++)
                    for (int y = 0; y < 3; y++)
                        for (int z = 0; z < 3; z++)
                            assertTrue(view.getGridDisplayer().isActive(x, y, z));
            });
        }

    /**
     * Tests submit button
     */
    @Test
    public void testBtnSubmitClicked() {
        Platform.runLater(()-> {
            view.setText("");
            assertTrue(view.getFoundWords().isEmpty());
            view.btnSubmitClicked();
            view.btnMenuClicked();

            assertTrue(view.getFoundWords().isEmpty());
            view.setText("text");
            view.btnSubmitClicked();
            assertFalse(view.getFoundWords().isEmpty());
        });
    }

    /**
     * Test hamburger button
     */
    @Test
    public void testBtnMenuClicked() {
        Platform.runLater(()-> {
            assertFalse(view.getHamburgerContext().isShowing());

            view.btnMenuClicked();

            assertTrue(view.getHamburgerContext().isShowing());
        });
    }

    /**
     * Test when end game button clicked
     */
    @Test
    public void btnEndGameClicked(){
        Platform.runLater(()->{
        });
    }
}