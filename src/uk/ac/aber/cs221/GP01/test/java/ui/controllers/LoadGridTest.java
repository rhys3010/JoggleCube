/*
   * @(#) LoadGridTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.LoadGrid;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.Start;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for the LoadGrid class
 *
 * @author Agata Lefek agl6
 * @author Aleksandra Madej alm82
 * @version 1.0
 */
class LoadGridTest extends ApplicationTest {

    Parent rootNode;
    LoadGrid load = LoadGrid.getInstance();

    @BeforeEach
    void setUp() {
        Start.getInstance().btnLoadGridClicked();
    }

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

    /**
     * Tests page layout
     */
    @Test
    void prepView() {
        setRootNode();
        ListView<String> items = from(rootNode).lookup("#listViewRecents").query();
        assertEquals(JoggleCube.getInstance().getRecentGrids(), items.getItems());
    }

    @Test
    void btnStartGridClicked() {
        setRootNode();
        ListView<String> list = from(rootNode).lookup("#listViewRecents").query();
        list.getSelectionModel().selectFirst();
        load.handleMouseClick(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY,
                1, true, true, true, true, true,
                true, true, true, true, true, null));
        Platform.runLater(() -> load.btnStartGridClicked());
        clickOn(400, 400);

        Platform.runLater(() -> GameView.getInstance().getDialog().getTextInputDialog().close());

        clickOn(400, 400); // everytime after run later -> wait?

        setRootNode();
        HBox box = from(rootNode).lookup("#gameBox").query();
        assertEquals("gameElementsContainer", box.getStyleClass().toString());
    }

    /**
     * Tests when back button is pressed
     */
    @Test
    void btnBackClicked() {
        Platform.runLater(() -> LoadGrid.getInstance().btnBackClicked());  // why doesn't work with load instead LoadGrid.getInstance()?
        clickOn(400, 400);

        setRootNode();
        Label pageType = from(rootNode).lookup("#title").query();
        assertEquals("JoggleCube", pageType.getText());
    }

    /**
     * Needs fresh install to test properly...
     */
    @Test
    void handleMouseClick() {
        setRootNode();
        ListView<String> list = from(rootNode).lookup("#listViewRecents").query();
        list.getSelectionModel().selectFirst();
        load.handleMouseClick(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY,
                1, true, true, true, true, true,
                true, true, true, true, true, null));

        assertNotNull(load.getFileName());
        assertEquals("grid_1", load.getFileName());

        list.getSelectionModel().select(2);
        load.handleMouseClick(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY,
                1, true, true, true, true, true,
                true, true, true, true, true, null));

        assertEquals("grid_3", load.getFileName());
    }

}