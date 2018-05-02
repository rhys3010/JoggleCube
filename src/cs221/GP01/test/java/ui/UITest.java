package cs221.GP01.test.java.ui;

import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.UI;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    @Test
    void initialize() throws IOException {

        System.out.print(Navigation.getInstance().getMain());
        Scene scene = new Scene(new Pane(),600,600);
        UI.getInstance().initialize(scene);
        System.out.print(Navigation.getInstance().getMain());

    }
}