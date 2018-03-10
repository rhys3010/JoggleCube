package cs221.GP01.main.java.ui;

import cs221.GP01.main.java.ui.controllers.BaseScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public interface IViewNavigation {

    void add(ScreenType name, FXMLLoader loader);

    void remove(ScreenType name);

    void switchScreen(ScreenType newScreen);

    void showOverlay(ScreenType overlay, BaseScreenController parent);

    void hideOverlay(ScreenType overlay, BaseScreenController parent);


    void setMainScene(Scene main);
}
