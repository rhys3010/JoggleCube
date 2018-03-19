package cs221.GP01.test.java;

import cs221.GP01.main.java.model.GameTimer;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.UI;
import cs221.GP01.main.java.ui.controllers.GameView;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {
    JoggleCube controller = JoggleCube.getInstance();
    UI uicontroller = UI.getInstance();
    GameTimer timer = new GameTimer();

    @Test
    public void testResetTime(){
        timer = new GameTimer();
        Thread t = new Thread(timer);
        t.start();
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
      assertEquals("2:59", GameView.getInstance().getTimerLabel().getText());


    }

    @Test
    public void testFinishTimer(){
        timer.finishTimer();
        // ??
        assertTrue(GameView.getInstance().getRoot().getChildren().contains(Navigation.getInstance().getScreens().get(ScreenType.END).getRoot()));
    }


    @Test
    public void testInterrupt() {

        assertFalse(timer.isInterrupt());
        timer.interrupt();
        assertTrue(timer.isInterrupt());

    }
}