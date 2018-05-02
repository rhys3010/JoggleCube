package cs221.GP01.test.java.ui;

import cs221.GP01.main.java.ui.Settings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSettingsTest {

    @Test
    void setCurrLang() {

        Settings.setCurrLang("cy");
        assertEquals("Cymraeg", Settings.getCurrLang());
        Settings.setCurrLang("engl");
        assertEquals("Cymraeg", Settings.getCurrLang());
        Settings.setCurrLang("ENGLISH");
        assertEquals("English", Settings.getCurrLang());
    }

    @Test
    void clearHighScores() {

        // doesnt do anything yet

    }
}