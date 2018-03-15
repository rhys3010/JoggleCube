package cs221.GP01.test.java;

import cs221.GP01.main.java.model.GameTimer;
import cs221.GP01.main.java.model.JoggleCubeController;
import cs221.GP01.main.java.ui.NavigationController;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.UIController;
import cs221.GP01.main.java.ui.controllers.GameController;
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
    public void testStartTimer() throws InterruptedException {

      timer.startTimer();
      assertEquals(Duration.ZERO, timer.getCurrentTime());

      timer.startTimer();
      Thread.sleep(1000);
      assertEquals(Duration.ofSeconds(179),timer.getCurrentTime());
      assertEquals("2:59", GameController.getInstance().getTimerLabel().getText());


    }

    @Test
    public void testFinishTimer(){
        timer.finishTimer();
        // ??
        assertTrue(GameController.getInstance().getRoot().getChildren().contains(NavigationController.getInstance().getScreens().get(ScreenType.END).getRoot()));
    }


    @Test
    public void testInterrupt() {

        assertFalse(timer.isInterrupt());
        timer.interrupt();
        assertTrue(timer.isInterrupt());

    }
}