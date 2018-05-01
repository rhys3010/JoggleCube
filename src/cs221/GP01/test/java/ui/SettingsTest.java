package cs221.GP01.test.java.ui;

import cs221.GP01.main.java.ui.ISettings;
import cs221.GP01.main.java.ui.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
/*
    @Test
    public void testMusic(){

        settings.setMusicEnabled(false);
        assertFalse(settings.isMusicEnabled());
        settings.setMusicEnabled(true);
        assertTrue(settings.isMusicEnabled());
    }

    @Test
    public void testSound(){

        settings.setMusicEnabled(false);
        assertFalse(settings.isSoundEffectsEnabled());
        settings.setMusicEnabled(true);
        assertTrue(settings.isSoundEffectsEnabled());
    }

    @Test
    public void testVolume() {

        settings.setVolume(100);
        assertEquals(100, settings.getVolume());
        settings.setVolume(0);
        assertEquals(0, settings.getVolume());
        settings.setVolume(150);
        assertEquals(0, settings.getVolume());
    }
*/
}
