package cs221.GP01.test.java.ui;

import cs221.GP01.Main;
import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.Navigation;
import cs221.GP01.main.java.ui.controllers.LoadGrid;
import cs221.GP01.main.java.ui.controllers.Start;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class LoadGridTest extends ApplicationTest {

    Parent rootNode;
    @BeforeEach
    void setUp() {
    }

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);

    }

    @Test
    void prepView() {

        Start.getInstance().btnLoadGridClicked();
        rootNode = Navigation.getInstance().getMain().getRoot();
        ListView<String> items = from(rootNode).lookup("#listViewRecents").query();
        assertEquals(JoggleCube.getInstance().getRecentGrids(), items.getItems());

    }

    @Test
    void btnStartGridClicked() {

        Start.getInstance().btnLoadGridClicked();
        ListView list = from(rootNode).lookup("#listViewRecents").query();
        list.getSelectionModel().selectFirst();
        LoadGrid.getInstance().btnStartGridClicked();

        rootNode = Navigation.getInstance().getMain().getRoot();
        HBox box = from(rootNode).lookup("#gameBox").query();
        assertEquals("gameElementsContainer",box.getStyle() );
    }

    @Test
    void btnBackClicked() {

        Start.getInstance().btnLoadGridClicked();
        LoadGrid.getInstance().btnBackClicked();

        rootNode = Navigation.getInstance().getMain().getRoot();
        Label pageType = from(rootNode).lookup("#pageType").query();
        assertEquals("JoggleCube",pageType.getText() );

    }

    @Test
    void handleMouseClick() {
    }
}