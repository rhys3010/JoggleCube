/*
   * @(#) UITest.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui;


import uk.ac.aber.cs221.GP01.main.java.ui.IFrontend;
import uk.ac.aber.cs221.GP01.main.java.ui.INavigation;

import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.UI;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the UI class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
class UITest extends ApplicationTest {

    /**
     * Tests screen dimensions and class path
     * @throws IOException
     */
    @Test
    void initialize() throws IOException {
        INavigation nav = Navigation.getInstance();
        IFrontend ui = UI.getInstance();

        assertTrue(nav.getScreens().isEmpty());

        Scene scene = new Scene(new Pane(), 600, 300);
        ui.initialize(scene);

        assertEquals(300.0, nav.getMain().getHeight());
        assertEquals(600.0, nav.getMain().getWidth());

        assertEquals(7, nav.getScreens().size());
        assertTrue(nav.getScreens().containsKey(ScreenType.END));
        assertEquals("class uk.ac.aber.cs221.GP01.main.java.ui.controllers.End",
                nav.getScreens().get(ScreenType.END).getController().getClass().toString());
    }
}