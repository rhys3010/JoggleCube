package cs221.GP01.test.java;

import cs221.GP01.main.java.model.JoggleCubeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class JoggleCubeControllerTest {

    JoggleCubeController controller = new JoggleCubeController();

    @Test
    public void testLoadNewDictionary(){
        controller.loadNewDictionary();
        assertFalse(controller.getLoadedDictionaries().isEmpty());
        controller.setLanguage("cy");
        assertTrue(controller.getLoadedDictionaries().containsKey("cy"));
    }

    @Test
    public void testWordValidityTest(){

        controller.loadNewDictionary();
        assertTrue(controller.testWordValidity("egg"));
        assertFalse(controller.testWordValidity("egg"));
        assertFalse(controller.testWordValidity("sjfsdkjfnkjsd"));
    }

    @ParameterizedTest
    @CsvSource({"egg, 25", "i, 1"})
    public void testGetWordScore(String word, int score){
        controller.loadNewDictionary();
        assertEquals(score, controller.getWordScore(word));
    }

    @Test
    public void testSaveToFile(){

        // only works if load works correctly
        File file = new File("test2.txt");

        controller.generateRandomGrid();
        String[][][] generatedCube = controller.getCubeData();

        controller.saveGrid(file);
        controller.loadGrid(file);
        String[][][] savedCube = controller.getCubeData();

        assertArrayEquals(generatedCube, savedCube);
        // ...
        //assertTrue(file.exists());    not here
    }

    @ParameterizedTest
    @CsvSource({"0,0,0,A", "0,0,1,B", "0,0,2,C", "0, 1, 0, D", "0,1,1,E", "0,1,2,F", "0,2,0,G", "0,2,1,H", "0,2,2,I",
                "1,0,0,J", "1,0,1,K", "1,0,2,L", "1,1,0,M", "1,1,1,N", "1,1,2,O", "1,2,0,P", "1,2,1,Qu", "1,2,2,R",
                "2,0,0,S", "2,0,1,T", "2,0,2,U", "2,1,0,V", "2,1,1,W", "2,1,2,X", "2,2,0,Y", "2,2,1,Z", "2,2,2,A"})
    public void testLoadGridLetters(int x, int y, int z, String letter){
        File file = new File("test.txt");
        controller.loadGrid(file);
        String[][][] stringCube = controller.getCubeData();
        assertEquals(letter, stringCube[x][y][z]);
        // ...

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