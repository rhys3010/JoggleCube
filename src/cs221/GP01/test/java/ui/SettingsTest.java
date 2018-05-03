package cs221.GP01.test.java.ui;

import cs221.GP01.Main;
import cs221.GP01.main.java.ui.ISettings;
import cs221.GP01.main.java.ui.Settings;
import javafx.stage.Stage;
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
        assertEquals("English", Settings.getCurrLang());
        assertEquals(180, Settings.getTimerLength());
    }

    @Test
    public void testCurrLang(){

        Settings.setCurrLang("Cymraeg");
        assertEquals("Cymraeg", Settings.getCurrLang());
        Settings.setCurrLang("English");
        assertEquals("English", Settings.getCurrLang());
        Settings.setCurrLang("Polish");
        assertEquals("English", Settings.getCurrLang());

    }

}
