package cs221.GP01.test.java.ui;

import cs221.GP01.Main;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.Settings;
import cs221.GP01.main.java.ui.UI;
import cs221.GP01.main.java.ui.controllers.BaseScreen;
import cs221.GP01.main.java.ui.controllers.Start;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import static cs221.GP01.main.java.ui.Settings.setCurrLang;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

class StartTest extends ApplicationTest {
    Start start = Start.getInstance();
    Parent rootNode;

    @AfterEach
    public void afterEachTest() throws TimeoutException {
       // FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }

    public void setRootNode(){
        rootNode = Navigation.getInstance().getMain().getRoot();
    }

    public void chooseWelsh() throws AWTException {
        setRootNode();
        ComboBox<String> language = from(rootNode).lookup("#languageSelector").query();

        clickOn(language);
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_DOWN);
        r.keyRelease(KeyEvent.VK_DOWN);
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    void prepView() throws AWTException {

        assertEquals("English",start.getLanguageSelector().getValue() );
        Settings.setCurrLang("Cymraeg");
        Start.getInstance().prepView();
        assertEquals("Cymraeg",start.getLanguageSelector().getValue() );

    }


    @Test
    void btnStartNewGridClicked() {

        setRootNode();
        Button button = from(rootNode).lookup("#btnStartNewGrid").query();
        assertEquals("Start New Grid", button.getText());
        clickOn(button);
        // deal with dialog

        //verifyThat();


    }

    @Test
    void btnLoadGridClicked() {

        setRootNode();
        Button button = from(rootNode).lookup("#btnLoadGrid").query();
        assertEquals("Load Grid", button.getText());
        clickOn(button);

        setRootNode();
        button = from(rootNode).lookup("#loadButton").query();
        assertEquals("Refresh", button.getText());
        button = from(rootNode).lookup("#startButton").query();
        assertEquals("Start Game", button.getText());
        System.out.print(rootNode.getStylesheets());   // compare regex with expected ( how? )


    }

    @Test
    void initialize() throws AWTException {
       assertEquals ("English", Settings.getCurrLang());
       chooseWelsh();
     //  Start.getInstance().initialize(); what arguments
        assertEquals("Cymraeg", Settings.getCurrLang() );
    }
}