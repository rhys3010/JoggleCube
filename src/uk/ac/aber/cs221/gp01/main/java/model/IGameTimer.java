/*
   * @(#) IGameTimer.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package uk.ac.aber.cs221.gp01.main.java.model;
import java.time.Duration;

/**
 * IGameTimer - An interface that manages the running of the game timer
 *
 *     @author Cameron Humphreys - cah27
 *     @version 1.1
 */
public interface IGameTimer {

    /**
     * resets currentTime back to default duration
     */
    void resetTime();

    /**
     * Launches and controls the game timer in a separate thread
     */
    void startTimer();

    /**
     * Ends the game
     */
    void finishTimer();

    /**
     * Returns the boolean interrupt
     *
     * @return interrupt
     */
    boolean isInterrupt();

    /**
     * Sets interrupt variable to true
     */
    void interrupt();

    /**
     * Launches startTimer in a seperate thread
     */
    void run();

    /**
     * Returns the current Duration
     *
     * @return currentTime
     */
    Duration getCurrentTime();

    /**
     * Sets the Duration of currentTime
     *
     * @param currentTime
     */
    void setCurrentTime(Duration currentTime);



}
