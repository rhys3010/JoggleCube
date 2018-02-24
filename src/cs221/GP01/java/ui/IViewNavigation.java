package cs221.GP01.java.ui;

import cs221.GP01.java.ui.controllers.BaseScreenController;
import javafx.fxml.FXMLLoader;

public interface IViewNavigation {

    void add(ScreenType name, FXMLLoader loader);

    void remove(ScreenType name);

    void switchScreen(ScreenType newScreen);

    void showOverlay(ScreenType overlay, BaseScreenController parent);

    void hideOverlay(ScreenType overlay, BaseScreenController parent);


}
