package cs221.GP01.test.java.ui;


import cs221.GP01.Main;

import cs221.GP01.main.java.ui.IFrontend;
import cs221.GP01.main.java.ui.IViewNavigation;

import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.ScreenType;
import cs221.GP01.main.java.ui.UI;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UITest extends ApplicationTest {


    @Test
    void initialize() throws IOException {


        IViewNavigation nav = Navigation.getInstance();
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