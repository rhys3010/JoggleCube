/*
   * @(#) NavigationTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui;

import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;

import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Help;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Navigation class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 */
public class NavigationTest extends ApplicationTest {

    Parent rootNode;

    @Override
    public void start(Stage stage) throws IOException {
        Main m = new Main();
        m.start(stage);
    }

    @AfterEach
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @BeforeEach
    public  void setup(){
        Navigation.getInstance().switchScreen(ScreenType.START);
    }

    public void setRootNode(){
        rootNode = Navigation.getInstance().getMain().getRoot();
    }

    @Test
    public void switchScreen() {

        Label label;

        setRootNode();
        label = from(rootNode).lookup("#title").query();
        assertEquals("JoggleCube", label.getText());

        Navigation.getInstance().switchScreen(ScreenType.HIGH_SCORES);
        setRootNode();
        label = from(rootNode).lookup("#title").query();
        assertEquals("High Scores", label.getText());

    }

    @Test
    public void showAndHideOverlay(){

        ObservableList<Node> children;

        Platform.runLater(()-> Navigation.getInstance().showOverlay(ScreenType.HELP, Start.getInstance()));
        clickOn(400,400);  //why

        assertTrue(Start.getInstance().getMainNode().isDisabled());

        children = Start.getInstance().getRoot().getChildren();
        assertEquals(2,children.size());

        Node expected = Navigation.getInstance().getScreens().get(ScreenType.HELP).getRoot();
        Node actual =  Start.getInstance().getRoot().getChildren().get(1);

        assertEquals(expected,actual);

        Platform.runLater(()-> Navigation.getInstance().hideOverlay(ScreenType.HELP, Start.getInstance()));
        clickOn(400,400);   //why ( random position )

        children = Start.getInstance().getRoot().getChildren();
        assertEquals(1,children.size());


    }




}
