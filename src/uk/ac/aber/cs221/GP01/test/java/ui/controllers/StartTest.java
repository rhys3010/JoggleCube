/*
   * @(#) StartTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.Settings;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.testfx.api.FxRobot;
//import org.testfx.framework.junit5.ApplicationTest;

/**
 * Tests for the Start class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
class StartTest extends ApplicationTest {
    Start start = Start.getInstance();
    Parent rootNode;

    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    /**
     * Creates instance of game for testing
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }

    public void setRootNode() {
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

        assertEquals("English", start.getLanguageSelector().getValue());
        Platform.runLater(() -> Settings.setCurrLang("Cymraeg"));
        clickOn(400, 400);
        Platform.runLater(() -> Start.getInstance().prepView());
        clickOn(400, 400);
        assertEquals("Cymraeg", start.getLanguageSelector().getValue());

    }


    @Test
    void btnStartNewGridClicked() {

        setRootNode();
        Button button = from(rootNode).lookup("#btnStartNewGrid").query();
        assertEquals("Start New Grid", button.getText());
        clickOn(button);

        Platform.runLater(() -> GameView.getInstance().getDialog().getTextInputDialog().close());
        clickOn(400, 400);

        setRootNode();
        HBox box = from(rootNode).lookup("#gameBox").query();
        assertEquals("gameElementsContainer", box.getStyleClass().toString());


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


    }

    @Test
    void initialize() throws AWTException {

        Settings.setCurrLang("English");
        Platform.runLater(() -> start.getLanguageSelector().setValue("English"));
        assertEquals("English", Settings.getCurrLang());
        chooseWelsh();  // initializing on action?
        clickOn(400, 400);
        assertEquals("Cymraeg", Settings.getCurrLang());
    }
}