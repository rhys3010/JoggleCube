package cs221.GP01.test.java.ui.controllers;

import cs221.GP01.main.java.model.IJoggleCube;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.IViewNavigation;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.controllers.End;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndTest {

    End end = End.getInstance();
    IJoggleCube cube = JoggleCube.getInstance();
    IViewNavigation nav = Navigation.getInstance();


    @BeforeEach
    public void reset() {
        end.prepView();
    }

    @Test
    public void testPrepView() {
        end.prepView();
        assertEquals(cube.getScore() + "", end.getScore());
        assertEquals(cube.getHighestScore() + "", end.getHighScore());
    }

    @Test
    public void btnHighScoreClickedTest() {
        end.btnHighScoreClicked();
        assertEquals(nav.getMain().getRoot(), nav.getScreens().get(ScreenType.HIGH_SCORES));
    }

    @Test
    public void btnMenuClickedTest() {
        end.btnMenuClicked();
        assertEquals(nav.getMain().getRoot(), nav.getScreens().get(ScreenType.START));
    }

    @Test
    public void btnReplayClickedTest() {
        end.btnReplayClicked();
        assertEquals(nav.getMain().getRoot(), nav.getScreens().get(ScreenType.GAME));
    }
}
