package cs221.GP01.test.java.ui.controllers;

import cs221.GP01.Main;
import cs221.GP01.main.java.ui.IViewNavigation;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.controllers.BaseScreen;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseScreenTest {

    BaseScreen base = new BaseScreen();
    IViewNavigation nav = Navigation.getInstance();


    @Test
    public void btnMenuClickedTest() {
        base.btnHighScoreClicked();
        assertEquals(nav.getMain().getRoot(), nav.getScreens().get(ScreenType.START));
    }

    @Test
    public void btnHighScoreClickedTest() {
        base.btnHighScoreClicked();
        assertEquals(nav.getMain().getRoot(), nav.getScreens().get(ScreenType.HIGH_SCORES));
    }

    @Test
    public void btnHelpClickedTest() {
        base.btnHighScoreClicked();
        assertEquals(nav.getMain().getRoot(), nav.getScreens().get(ScreenType.HELP));
    }

    @Test
    public void btnSettingsClickedTest() {
        base.btnHighScoreClicked();
        assertEquals(nav.getMain().getRoot(), nav.getScreens().get(ScreenType.SETTINGS));
    }
}
