package cs221.GP01.test.java.ui.controllers;

import cs221.GP01.Main;
import cs221.GP01.main.java.ui.IViewNavigation;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.controllers.BaseScreen;
import cs221.GP01.main.java.ui.controllers.HighScore;
import cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseScreenTest extends ApplicationTest {

    BaseScreen base = new BaseScreen();
    IViewNavigation nav = Navigation.getInstance();
    Parent rootNode;

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }
    public void setRootNode(){
        rootNode = Navigation.getInstance().getMain().getRoot();
    }

    @Test
    public void btnMenuClickedTest() {
        Platform.runLater(()->base.btnMenuClicked());
        assertEquals(Start.getInstance().getRoot(), nav.getMain().getRoot());
    }

    @Test
    public void btnHighScoreClickedTest() {
        Platform.runLater(()->base.btnHighScoreClicked());
        clickOn(400,400);
        assertEquals( HighScore.getInstance().getRoot(), nav.getMain().getRoot());

    }

    @Test
    public void btnHelpClickedTest() {
        base = Start.getInstance();
        Platform.runLater(()->base.btnHelpClicked());
        clickOn(400, 400);

        Node expected = Navigation.getInstance().getScreens().get(ScreenType.HELP).getRoot();
        Node actual =  Start.getInstance().getRoot().getChildren().get(1);

        assertEquals(expected,actual);

    }

    @Test
    public void btnSettingsClickedTest() {
        base = Start.getInstance();
        Platform.runLater(()->base.btnSettingsClicked());
        clickOn(400, 400);

        Node expected = Navigation.getInstance().getScreens().get(ScreenType.SETTINGS).getRoot();
        Node actual =  Start.getInstance().getRoot().getChildren().get(1);

        assertEquals(expected,actual);
    }
}
