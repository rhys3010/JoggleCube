/*
   * @(#) NavigationTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java.ui;

import cs221.GP01.Main;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;


public class NavigationTest  extends ApplicationTest {
    @Override
    public void start(Stage stage) throws IOException {
        Main m = new Main();
        m.start(stage);
    }

    @Test
    public void switchScreen() {
        Navigation.getInstance().switchScreen(ScreenType.HIGH_SCORES);
        //todo hmmmmm
        verifyThat(lookup(".selectionContainer"), hasChildren(0,".highScoreTable"));

    }
}
