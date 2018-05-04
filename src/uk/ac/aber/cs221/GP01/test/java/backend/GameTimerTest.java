/*
   * @(#) GameTimeTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.backend;

import uk.ac.aber.cs221.GP01.main.java.model.GameTimer;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.UI;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import javafx.application.Platform;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for GameTimer class
 *
 * @author Aleksandra Madej (alm82)
 * @version 1.1
 * @see GameTimer
 */
class GameTimerTest {

    GameTimer timer = new GameTimer();

    /**
     * Test if gameTimer resets
     */
    @Test
    public void testResetTime() {
        timer = new GameTimer();
        Thread t = new Thread(timer);
        t.start();
        timer.resetTime();
        assertEquals(Duration.ofSeconds(180), timer.getCurrentTime());

    }

    /**
     * Test if gameTimer starts
     *
     * @throws InterruptedException

    @Test
    public void testStartTimer() throws InterruptedException {

        Label timerLabel = new Label();
        GameView.getInstance().setTimerLabel(timerLabel);
        timer.startTimer();
        assertEquals(Duration.ZERO, timer.getCurrentTime());

        timer.startTimer();
        Thread.sleep(1000);
        assertEquals(Duration.ofSeconds(179), timer.getCurrentTime());
        assertEquals("2:59", GameView.getInstance().getTimerLabel().getText());
    }*/

    /**
     * Test if gameTimer stops

    @Test
    public void testFinishTimer() {

        timer.finishTimer();
        System.out.print(GameView.getInstance().getRoot().getChildren());
        //assertTrue(GameView.getInstance().getRoot().getChildren().contains(Navigation.getInstance().getScreens().get(ScreenType.END).getRoot()));
    }*/

    /**
     * Test if gameTimer interrupts
     */
    @Test
    public void testInterrupt() {

        assertFalse(timer.isInterrupt());
        timer.interrupt();
        assertTrue(timer.isInterrupt());

    }
}