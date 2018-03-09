package cs221.GP01.test.java;

import cs221.GP01.main.java.model.GameTimer;
import cs221.GP01.main.java.model.JoggleCubeController;
import cs221.GP01.main.java.ui.UIController;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {
    JoggleCubeController controller = JoggleCubeController.getInstance();
    UIController uicontroller = UIController.getInstance();
    GameTimer timer = new GameTimer();

    @Test
    public void testResetTime(){
        timer.resetTime();
        assertEquals(Duration.ofSeconds(180),timer.getCurrentTime());

    }

    @Test
    public void testStartTimer(){
        assertNotNull(timer.getCurrentTime());
        // ?
    }

    @Test
    public void testFinishTimer(){
        // ?
    }
}