package uk.ac.aber.cs221.GP01.test.java.ui.controllers;


import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import uk.ac.aber.cs221.GP01.Main;
import uk.ac.aber.cs221.GP01.main.java.model.JoggleCube;
import uk.ac.aber.cs221.GP01.main.java.ui.Dialog;
import uk.ac.aber.cs221.GP01.main.java.ui.Navigation;
import uk.ac.aber.cs221.GP01.main.java.ui.ScreenType;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;

import static org.junit.jupiter.api.Assertions.*;


public class GameViewTest  extends ApplicationTest {

    @Override
    public void start (Stage stage) throws Exception {
        Main m = new Main();
        m.start(stage);
    }


    private GameView view = GameView.getInstance();


    @BeforeEach
    public void reset() {
        JoggleCube.getInstance().resetGameState();
        JoggleCube.getInstance().generateRandomGrid();
        Platform.runLater(()-> Navigation.getInstance().switchScreen(ScreenType.GAME));
        Dialog d = GameView.getInstance().getDialog();
       // clickOn(700,320);
        Platform.runLater(()->GameView.getInstance().getDialog().getTextInputDialog().close());
    }



        @Test
        public void prepViewTest(){
            assertEquals("0", view.getScoreLabel().getText());
            assertEquals("3:00", view.getTimerLabel().getText());
            assertEquals("", view.getText());
            assertEquals("-fx-text-fill: white;", view.getTimerLabel().getStyle());
        }

        @Test
        public void testBtnClearClicked() {
            Platform.runLater(()->{
                assertEquals("", view.getText());
                for (int x = 0; x < 3; x++)
                    for (int y = 0; y < 3; y++)
                        for (int z = 0; z < 3; z++)
                            assertTrue(view.getGridDisplayer().isActive(x, y, z));


                view.setText("text");
                view.getGridDisplayer().setInActiveP(1,1,1);
                view.getGridDisplayer().setInActiveP(2,2,2);

                assertNotEquals("", view.getText());

                view.btnClearClicked();

                assertEquals("", view.getText());
                for (int x = 0; x < 3; x++)
                    for (int y = 0; y < 3; y++)
                        for (int z = 0; z < 3; z++)
                            assertTrue(view.getGridDisplayer().isActive(x, y, z));
            });
        }

    @Test
    public void testBtnSubmitClicked() {
        Platform.runLater(()-> {
            view.setText("");
            assertTrue(view.getFoundWords().isEmpty());
            view.btnSubmitClicked();
            view.btnMenuClicked();

            assertTrue(view.getFoundWords().isEmpty());
            view.setText("text");
            view.btnSubmitClicked();
            assertFalse(view.getFoundWords().isEmpty());
        });
    }

    @Test
    public void testBtnMenuClicked() {
        Platform.runLater(()-> {
            assertFalse(view.getHamburgerContext().isShowing());

            view.btnMenuClicked();

            assertTrue(view.getHamburgerContext().isShowing());

        });
    }

    @Test
    public void btnEndGameClicked(){
       /* Platform.runLater(()->{

            assertFalse(view.getHamburgerContext().isShowing());
            view.btnMenuClicked();
            assertTrue(view.getHamburgerContext().isShowing());
        });

        Parent root = GameView.getInstance().getRoot();
        clickOn((Button)from(root).lookup("#menuButton").query());*/

       // System.out.print(from(root).lookup("#quitBtn"));
       // clickOn((Button)from(root).lookup("#quitBtn"));

    }
}