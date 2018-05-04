/*
   * @(#) GameTimer.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package uk.ac.aber.cs221.GP01.main.java.model;

import javafx.application.Platform;
import javafx.scene.control.Label;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.Settings;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.BaseScreen;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;

import java.time.Duration;

/**
 * GameTimer - A class that manages the running of the game timer
 *
 *   @author Cameron Humphreys - cah27
 *   @version 1.1
 *   @see IGameTimer
 */
public class GameTimer implements IGameTimer, Runnable {

    private Duration currentTime;
    private boolean interrupt = false;
    Label timerLabel;

    public GameTimer(){}

    /**
     * Resets currentTime back to default duration
     */
    @Override
    public void resetTime() {
        currentTime = Duration.ofSeconds(180);
    }

    /**
     * Launches and controls the game timer in a separate thread
     */
    @Override
    public void startTimer() {
        timerLabel = GameView.getInstance().getTimerLabel();
        int timerLength = Settings.getTimerLength();
        int timeLeft = timerLength;
        currentTime = Duration.ofSeconds(timerLength);
        while(!(currentTime.equals(Duration.ZERO))){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeLeft = timeLeft - 1;
            currentTime = Duration.ofSeconds(timeLeft);

            // Change color of timer label depending on currentTime
            if(currentTime.getSeconds() <= 10){
                timerLabel.setStyle("-fx-text-fill: -fx-timer-red;");

            }else if(currentTime.getSeconds() < 30 && currentTime.getSeconds() > 10){
                timerLabel.setStyle("-fx-text-fill: -fx-timer-amber;");

            }else{
                timerLabel.setStyle("-fx-text-fill: white;");
            }

            Platform.runLater(() -> timerLabel.setText(String.format("%2d:%02d", currentTime.getSeconds()/60, currentTime.getSeconds() % 60)));
            if(interrupt){
                break;
            }
        }
        if(!interrupt){
            finishTimer();
        }
    }

    /**
     * Ends the game
     */
    @Override
    public void finishTimer() {
        Platform.runLater(() -> Navigation.getInstance().showOverlay(ScreenType.END, (BaseScreen) GameView.getInstance()));
    }

    /**
     * Returns the boolean interrupt
     *
     * @return interrupt
     */
    public boolean isInterrupt() { return interrupt; }

    /**
     * Sets interrupt variable to true
     */
    public void interrupt() {
        interrupt = true;
    }

    /**
     * Launches startTimer in a seperate thread
     */
    @Override
    public void run() {
        startTimer();
    }

    /**
     * Returns the current Duration
     *
     * @return currentTime
     */
    public Duration getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets the Duration of currentTime
     *
     * @param currentTime Duration in seconds
     */
    public void setCurrentTime(Duration currentTime) {
        this.currentTime = currentTime;
    }


}

