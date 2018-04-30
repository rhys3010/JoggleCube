package cs221.GP01.test.java.ui;

import cs221.GP01.main.java.ui.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SettingsTest {

    Settings settings = Settings.getInstance();

    @BeforeEach
    public void reset() {

    }

    @Test
    public void testPrep() {

        assertFalse(settings.isColorBlindEnabled());
        assertTrue(settings.isMusicEnabled());
        assertTrue(settings.isSoundEffectsEnabled());
        assertEquals("English", settings.getCurrLang());
        assertEquals(180, settings.getTimerLength());
        assertEquals(75, settings.getVolume());

    }

    @Test
    public void testCurrLang(){

        settings.setCurrLang("Cymraeg");
        assertEquals("Cymraeg", settings.getCurrLang());
        settings.setCurrLang("English");
        assertEquals("English", settings.getCurrLang());
        settings.setCurrLang("Polish");
        assertEquals("Polish", settings.getCurrLang());

    }

    @Test
    public void testColorBlind() {

        settings.setColorBlindEnabled(true);
        assertTrue(settings.isColorBlindEnabled());
        settings.setColorBlindEnabled(false);
        assertFalse(settings.isColorBlindEnabled());

    }

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
}
