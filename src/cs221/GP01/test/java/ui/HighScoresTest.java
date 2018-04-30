package cs221.GP01.test.java.ui;
package cs221.GP01.main.java.model.IScore;
import cs221.GP01.main.java.ui.controllers.HighScore;
import cs221.GP01.main.java.ui.controllers.BaseScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class HighScoresTest {
    HighScore score = HighScore.getInstance();

    @BeforeEach
    public void reset() {
        score.prepView();
    }

    @Test
    public void prepView(){

    }


}
