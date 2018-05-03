/*
   * @(#) StartController.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java.ui;


import cs221.GP01.main.java.ui.IFrontend;
import cs221.GP01.main.java.ui.INavigation;

import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.UI;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the UI class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 */
class UITest extends ApplicationTest {


    @Test
    void initialize() throws IOException {


        INavigation nav = Navigation.getInstance();
        IFrontend ui = UI.getInstance();

        assertTrue(nav.getScreens().isEmpty());

        Scene scene = new Scene(new Pane(),600,300);
        ui.initialize(scene);

        assertEquals(300.0, nav.getMain().getHeight());
        assertEquals(600.0, nav.getMain().getWidth());

        assertEquals(7, nav.getScreens().size());
        assertTrue(nav.getScreens().containsKey(ScreenType.END));
        assertEquals("class cs221.GP01.main.java.ui.controllers.End",
                nav.getScreens().get(ScreenType.END).getController().getClass().toString());



    }
}