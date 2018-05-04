/*
   * @(#) StartTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.Settings;
import uk.ac.aber.cs221.GP01.main.java.ui.UI;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.BaseScreen;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
//import org.testfx.api.FxRobot;
//import org.testfx.framework.junit5.ApplicationTest;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import static uk.ac.aber.cs221.GP01.main.java.ui.Settings.setCurrLang;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Tests for the Start class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
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
        Platform.runLater(()->Settings.setCurrLang("Cymraeg"));
        clickOn(400,400);
        Platform.runLater(()->Start.getInstance().prepView());
        clickOn(400,400);
        assertEquals("Cymraeg",start.getLanguageSelector().getValue() );

    }


    @Test
    void btnStartNewGridClicked() {

        setRootNode();
        Button button = from(rootNode).lookup("#btnStartNewGrid").query();
        assertEquals("Start New Grid", button.getText());
        clickOn(button);

        Platform.runLater(()-> GameView.getInstance().getDialog().getTextInputDialog().close());
        clickOn(400,400);

        setRootNode();
        HBox box = from(rootNode).lookup("#gameBox").query();
        assertEquals("gameElementsContainer",box.getStyleClass().toString() );


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
        Platform.runLater(()->start.getLanguageSelector().setValue("English"));
       assertEquals ("English", Settings.getCurrLang());
       chooseWelsh();  // initializing on action?
       clickOn(400,400);
        assertEquals("Cymraeg", Settings.getCurrLang() );
    }
}