/**
 * @author Cameron Humphreys - cah27
 */

package cs221.GP01.main.java.model;
import cs221.GP01.main.java.ui.NavigationController;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.controllers.BaseScreenController;
import cs221.GP01.main.java.ui.controllers.GameController;
import javafx.scene.control.Label;

import java.time.Duration;

public class GameTimer implements IGameTimer {

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



    public GameTimer(){}

    public void setCurrentTime(Duration currentTime) {
        this.currentTime = currentTime;
    }

    public Duration getCurrentTime() {
        return currentTime;
    }

    @Override
    public void resetTime() {
        currentTime = Duration.ofSeconds(180);
    }

    @Override
    public void startTimer() {
        //todo update the timer label with current time
        Label timerLabel = GameController.getInstance().getScoreLabel();
        //
        int timeLeft = 180;
        currentTime = Duration.ofSeconds(180);
        while(!(currentTime.equals(Duration.ZERO))){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime = Duration.ofSeconds(timeLeft - 1);
            timerLabel.setText(currentTime.toString());
        }
        finishTimer();
    }

    @Override
    public void finishTimer() {
        //ends the game
        NavigationController.getInstance().showOverlay(ScreenType.END, (BaseScreenController) GameController.getInstance());
    }
}
