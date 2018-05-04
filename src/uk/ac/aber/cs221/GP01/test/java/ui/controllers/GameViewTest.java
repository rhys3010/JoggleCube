package uk.ac.aber.cs221.GP01.test.java.ui.controllers;

import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GameView;
import uk.ac.aber.cs221.GP01.main.java.ui.controllers.GridDisplayer;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GameViewTest {

    GameView view = GameView.getInstance();



    @BeforeEach
    public void reset() {
        view.prepView();
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

    }


    @Test
    public void testBtnSubmitClicked() {

        view.setText("");
        assertTrue(view.getFoundWords().isEmpty());
        view.btnSubmitClicked();
        assertTrue(view.getFoundWords().isEmpty());
        view.setText("text");
        view.btnSubmitClicked();
        assertFalse(view.getFoundWords().isEmpty());
    }


    @Test
    public void testBtnMenuClicked() {

        assertFalse(view.getHamburgerContext().isShowing());

        view.btnMenuClicked();

        assertTrue(view.getHamburgerContext().isShowing());

        Point2D screenPos = view.getMenuButton().localToScreen(view.getMenuButton().getLayoutX(), view.getMenuButton().getLayoutY());
        assertTrue(view.getHamburgerContext().getX() == screenPos.getX() - 100);
        assertTrue(view.getHamburgerContext().getY() == screenPos.getY() + 20);
    }
}
