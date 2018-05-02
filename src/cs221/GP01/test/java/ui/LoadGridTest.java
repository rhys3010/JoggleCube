/*
   * @(#) LoadGridTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java.ui;

import cs221.GP01.Main;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.controllers.GameView;
import cs221.GP01.main.java.ui.controllers.LoadGrid;
import cs221.GP01.main.java.ui.controllers.Start;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.net.URL;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

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

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }

    public void setRootNode(){
        rootNode = Navigation.getInstance().getMain().getRoot();
    }

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
        load.handleMouseClick();
        Platform.runLater(()->load.btnStartGridClicked());
        clickOn(400,400);
        //Platform.runLater(()->GameView.getInstance().dialog.close());
        clickOn(400,400); // everytime after run later -> wait?

        setRootNode();
        HBox box = from(rootNode).lookup("#gameBox").query();
        assertEquals("gameElementsContainer",box.getStyleClass().toString() );
    }

    @Test
    void btnBackClicked() {

        Platform.runLater(()->LoadGrid.getInstance().btnBackClicked());  // why doesn't work with load instead LoadGrid.getInstance()?
clickOn(400,400);

        setRootNode();
        Label pageType = from(rootNode).lookup("#title").query();
        assertEquals("JoggleCube",pageType.getText() );

    }

    @Test
    void handleMouseClick() {

        setRootNode();
        ListView<String> list = from(rootNode).lookup("#listViewRecents").query();
        list.getSelectionModel().selectFirst();
        load.handleMouseClick();

        assertNotNull(load.getFileName());
        assertEquals("grid_1",load.getFileName());

        list.getSelectionModel().select(2);
        load.handleMouseClick();

        assertEquals("grid_3", load.getFileName() );


    }
}