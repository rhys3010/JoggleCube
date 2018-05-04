/*
   * @(#) BaseScreenTest.java 1.0 2018/03/05
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.ui.INavigation;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.BaseScreen;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.HighScore;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the BaseScreen class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
public class BaseScreenTest extends ApplicationTest {

    BaseScreen base = new BaseScreen();
    INavigation nav = Navigation.getInstance();

    /**
     * Creates instance of game for testing
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);
    }

    /**
     * Test to check if home menu button works
     */
    @Test
    public void btnMenuClickedTest() {
        Platform.runLater(() -> base.btnMenuClicked());
        assertEquals(nav.getMain().getRoot(), Start.getInstance().getRoot());
    }

    /**
     * Test to check if highscore menu button works
     */
    @Test
    public void btnHighScoreClickedTest() {
        Platform.runLater(() -> base.btnHighScoreClicked());
        clickOn(400, 400);
        assertEquals(nav.getMain().getRoot(), HighScore.getInstance().getRoot());
    }

    /**
     * Test to check if help menu button works
     */
    @Test
    public void btnHelpClickedTest() {
        base = Start.getInstance();
        Platform.runLater(() -> base.btnHelpClicked());
        clickOn(400, 400);

        Node expected = Navigation.getInstance().getScreens().get(ScreenType.HELP).getRoot();
        Node actual = Start.getInstance().getRoot().getChildren().get(1);

        assertEquals(expected, actual);
    }

    /**
     * Test to check if settings menu button works
     */
    @Test
    public void btnSettingsClickedTest() {
        base = Start.getInstance();
        Platform.runLater(() -> base.btnSettingsClicked());
        clickOn(400, 400);

        Node expected = Navigation.getInstance().getScreens().get(ScreenType.SETTINGS).getRoot();
        Node actual = Start.getInstance().getRoot().getChildren().get(1);

        assertEquals(expected, actual);
    }
}
