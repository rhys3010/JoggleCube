/*
   * @(#) GameTimeTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.backend;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.main.java.model.GameTimer;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for GameTimer class
 *
 * @author Aleksandra Madej (alm82)
 * @version 1.1
 * @see GameTimer
 */
class GameTimerTest extends ApplicationTest {

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
     * Test if gameTimer interrupts
     */
    @Test
    public void testInterrupt() {

        assertFalse(timer.isInterrupt());
        timer.interrupt();
        assertTrue(timer.isInterrupt());

    }
}