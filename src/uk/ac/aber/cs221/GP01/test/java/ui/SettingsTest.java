/*
   * @(#) SettingsTest.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui;

import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.ui.ISettings;
import uk.ac.aber.cs221.GP01.main.java.ui.Settings;
import uk.ac.aber.cs221.GP01.main.java.ui.*;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.HighScore;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Settings class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
public class SettingsTest extends ApplicationTest {

    ISettings settings = Settings.getInstance();
    Parent root;

    /**
     * Creates instance of game for testing
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);
    }

    /**
     * Tests page layout
     */
    @Test
    public void testPrep() {
        assertFalse(settings.isColorBlindEnabled());
        assertEquals("English", Settings.getCurrLang());
        assertEquals(180, Settings.getTimerLength());
    }

    /**
     * Tests the setting of dictionaries
     */
    @Test
    public void testCurrLang() {
        Settings.setCurrLang("Cymraeg");
        assertEquals("Cymraeg", Settings.getCurrLang());
        Settings.setCurrLang("English");
        assertEquals("English", Settings.getCurrLang());
        Settings.setCurrLang("Polish");
        assertEquals("English", Settings.getCurrLang());
    }

    /**
     * Tests colour blind option
     */
    @Test
    public void testColorBlind() {
        ISettings settings = Settings.getInstance();
        assertEquals("#38aa38", settings.getCurrentlySelectedColor());
        assertEquals("#30599b", settings.getAvailableColor());
        assertEquals("#64846b", settings.getAlreadySelectedColor());

        Platform.runLater(() -> settings.toggleColourBlind());
        clickOn(400, 400);

        assertEquals("#cbc155", settings.getCurrentlySelectedColor());
        assertEquals("#b171cb", settings.getAvailableColor());
        assertEquals("#cb5758", settings.getAlreadySelectedColor());
        assertTrue(GameView.getInstance().getColorBlindIcon().isVisible());
    }
}
