package cs221.GP01.test.java.ui;

import cs221.GP01.Main;
<<<<<<< HEAD
import cs221.GP01.main.java.ui.ISettings;
import cs221.GP01.main.java.ui.Settings;
=======
import cs221.GP01.main.java.ui.*;
import cs221.GP01.main.java.ui.controllers.GameView;
import cs221.GP01.main.java.ui.controllers.HighScore;
import cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
>>>>>>> dev-testing
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.invoke.SerializedLambda;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SettingsTest extends ApplicationTest {

    ISettings settings = Settings.getInstance();
    Parent root;

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }


    @Test
    public void testPrep() {

        assertFalse(settings.isColorBlindEnabled());
        assertEquals("English", Settings.getCurrLang());
        assertEquals(180, Settings.getTimerLength());
<<<<<<< HEAD
=======

>>>>>>> dev-testing
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

<<<<<<< HEAD
=======
    @Test
    public void testColorBlind() {

        ISettings settings = Settings.getInstance();
        assertEquals("#38aa38", settings.getCurrentlySelectedColor());
        assertEquals("#30599b", settings.getAvailableColor());
        assertEquals("#64846b", settings.getAlreadySelectedColor());

        Platform.runLater(()->settings.toggleColourBlind());
        clickOn(400,400);

        assertEquals("#cbc155", settings.getCurrentlySelectedColor());
        assertEquals("#b171cb", settings.getAvailableColor());
        assertEquals("#cb5758", settings.getAlreadySelectedColor());
        assertTrue(GameView.getInstance().getColorBlindIcon().isVisible());


    }


>>>>>>> dev-testing
}
