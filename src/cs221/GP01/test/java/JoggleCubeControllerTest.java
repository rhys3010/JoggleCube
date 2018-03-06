package cs221.GP01.test.java;

import cs221.GP01.main.java.model.JoggleCubeController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoggleCubeControllerTest {

    JoggleCubeController controller = new JoggleCubeController();

    @Test
    public void testWordValidityTest(){

        controller.loadNewDictionary();
        assertTrue(controller.testWordValidity("egg"));
        assertFalse(controller.testWordValidity("sjfsdkjfnkjsd"));
    }

    @Test
    public void setLanguageTest(){

        controller.setLanguage("en");
        assertTrue(controller.testWordValidity("egg"));
        assertFalse(controller.testWordValidity("Prifysgol"));

        controller.setLanguage("cy");
        assertTrue(controller.testWordValidity("Prifysgol"));
        assertFalse(controller.testWordValidity("egg"));
    }
    
}