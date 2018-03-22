package cs221.GP01.test.java.ui;

import cs221.GP01.Main;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.UI;
import cs221.GP01.main.java.ui.controllers.Start;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

class StartTest extends ApplicationTest {
    Start start = Start.getInstance();
    Main m = new Main();
    Parent rootNode;
    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }


    @Test
    void prepView() {
    }

    @Test
    void btnStartNewGridClicked() {

        rootNode = Navigation.getInstance().getMain().getRoot();
        Button button = from(rootNode).lookup("#btnStartNewGrid").query();
        assertEquals("Start New Grid", button.getText());
        clickOn(button);




        //verifyThat();


    }

    @Test
    void btnLoadGridClicked() {

        //verifyThat("#btnLoadGrid", hasText("Load Grid"));
        // clickOn(700,420);
        // FxRobot robot = new FxRobot();
        // lookup("#btnLoadGrid").query();
        //clickOn("#btnLoadGrid");
        rootNode = Navigation.getInstance().getMain().getRoot();
        Button button = from(rootNode).lookup("#btnLoadGrid").query();
        assertEquals("Load Grid", button.getText());
        clickOn(button);
        rootNode = Navigation.getInstance().getMain().getRoot();
        System.out.print(rootNode.getStylesheets());
    }

    @Test
    void initialize() {
    }
}