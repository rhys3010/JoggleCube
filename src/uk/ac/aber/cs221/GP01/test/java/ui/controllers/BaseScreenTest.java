/*
   * @(#) StartController.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.ui.INavigation;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.BaseScreen;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.HighScore;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the BaseScreen class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 */
public class BaseScreenTest extends ApplicationTest {

    BaseScreen base = new BaseScreen();
    INavigation nav = Navigation.getInstance();
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
        assertEquals(nav.getMain().getRoot(), Start.getInstance().getRoot());
    }

    @Test
    public void btnHighScoreClickedTest() {
        Platform.runLater(()->base.btnHighScoreClicked());
        clickOn(400,400);
        assertEquals(nav.getMain().getRoot(), HighScore.getInstance().getRoot());

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
