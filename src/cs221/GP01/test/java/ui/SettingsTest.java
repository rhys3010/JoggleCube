/*
   * @(#) StartController.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java.ui;

import cs221.GP01.main.java.ui.ISettings;
import cs221.GP01.main.java.ui.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Settings class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 */
public class SettingsTest {

    ISettings settings = Settings.getInstance();

    @BeforeEach
    public void reset() {

    }

    @Test
    public void testPrep() {

        assertFalse(settings.isColorBlindEnabled());
        //assertTrue(settings.isMusicEnabled());
        //assertTrue(settings.isSoundEffectsEnabled());
        assertEquals("English", Settings.getCurrLang());
        assertEquals(180, Settings.getTimerLength());
        //assertEquals(75, settings.getVolume());

    }

    @Test
    public void testCurrLang(){

        Settings.setCurrLang("Cymraeg");
        assertEquals("Cymraeg", Settings.getCurrLang());
        Settings.setCurrLang("English");
        assertEquals("English", Settings.getCurrLang());
        Settings.setCurrLang("Polish");
        assertEquals("Polish", Settings.getCurrLang());

    }

    @Test
    public void testColorBlind() {

        settings.toggleColourBlind();
        assertTrue(settings.isColorBlindEnabled());
        settings.toggleColourBlind();
        assertFalse(settings.isColorBlindEnabled());

    }
}
