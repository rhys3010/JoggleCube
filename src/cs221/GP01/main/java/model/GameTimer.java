/**
 * @author Cameron Humphreys - cah27
 */

package cs221.GP01.main.java.model;
import cs221.GP01.main.java.ui.NavigationController;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.controllers.BaseScreenController;
import cs221.GP01.main.java.ui.controllers.GameController;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.Duration;

public class GameTimer implements IGameTimer, Runnable {

    /**
     * create instance of timer in joggle cube - private Timer timer
     * in the constructor
     * add start timer in joggle cube controller
     *
     * make sure timer is launched in seperate thread
     *
     *timer hits 0 called gamendclicked
     */

    private Duration currentTime;
    private boolean interupt = false;


    public GameTimer(){}

    public void setCurrentTime(Duration currentTime) {
        this.currentTime = currentTime;
    }

    public Duration getCurrentTime() {
        return currentTime;
    }

    public boolean isInterupt() { return interupt; }

    @Override
    public void resetTime() {
        currentTime = Duration.ofSeconds(180);
    }

    @Override
    public void startTimer() {
        Label timerLabel = GameController.getInstance().getTimerLabel();
        //
        int timeLeft = 180;
        currentTime = Duration.ofSeconds(180);
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
            if(interupt){
                break;
            }
        }
        if(!interupt){
            finishTimer();
        }
    }

    @Override
    public void finishTimer() {
        //ends the game
        Platform.runLater(() -> NavigationController.getInstance().showOverlay(ScreenType.END, (BaseScreenController) GameController.getInstance()));
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        startTimer();
    }

    public void interrupt() {
        interupt = true;
    }
}
