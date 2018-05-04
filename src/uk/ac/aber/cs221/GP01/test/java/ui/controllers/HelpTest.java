/*
   * @(#) HelpTest.java 1.0 2018/03/05
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Help;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.gp01.Main;
import uk.ac.aber.cs221.gp01.main.java.ui.controllers.Help;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Help class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
class HelpTest extends ApplicationTest {

    Help help;
    Parent root;
    Label label;

    /**
     * Creates instance of game for testing
     */
    @Override
    public void start(Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);
    }

    /**
     * Load help page before each test
     */
    @BeforeEach
    void setUp() {
        help = Help.getInstance();
    }

    /**
     * Close page after each test
     *
     * @throws TimeoutException
     */
    @AfterEach
    void tearDown() throws TimeoutException {
        FxToolkit.hideStage();
    }

    /**
     * Test right navigation button
     */
    @Test
    void btnRightNavClicked() {
        Platform.runLater(() -> Help.getInstance().btnRightNavClicked());
        clickOn(400, 400);
        root = help.getHelpPageContainer().getRoot();
        label = from(root).lookup("#content").query();
        assertEquals("To ROTATE ", label.getText().substring(0, 10));
    }

    /**
     * Test left navigation button
     *
     * @throws Exception
     */
    @Test
    void btnLeftNavClicked() throws Exception {
        Platform.runLater(() -> Help.getInstance().btnLeftNavClicked());
        clickOn(400, 400);
        root = help.getHelpPageContainer().getRoot();
        label = from(root).lookup("#title").query();
        System.out.print(help.getCurrentPageIndex());
        assertEquals("Toggling Colourblind Mode: ", label.getText());
    }

    /**
     * Tests page layout
     */
    @Test
    void prepView() {
        help.prepView();
        root = help.getHelpPageContainer().getRoot();
        label = from(root).lookup("#title").query();
        assertEquals("What is Joggle Cube?", label.getText());
    }
}