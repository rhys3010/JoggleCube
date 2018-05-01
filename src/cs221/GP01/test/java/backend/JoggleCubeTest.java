/*
   * @(#) JoggleCubeTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java.backend;

import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for JoggleCube class
 *
 * @author Aleksandra Madej (alm82)
 * @version 1.1
 * @see JoggleCube
 */
class JoggleCubeTest {

    JoggleCube controller = JoggleCube.getInstance();

    /**
     * Reset GoggleCube before each test
     */
    @BeforeEach
    public void reset(){
       // controller.resetGameState();
        Settings.setCurrLang("en");
        controller.generateRandomGrid();
    }

    /**
     * Test the loading of a new dictionary
     */
    @Test
    public void testLoadNewDictionary(){
        assertFalse(controller.getLoadedDictionaries().isEmpty());
        Settings.setCurrLang("cy");
        assertTrue(controller.getLoadedDictionaries().containsKey("cy"));
    }

    /**
     * Test for valid words
     */
    @Test
    public void testWordValidityTest(){
        assertTrue(controller.testWordValidity("egg"));
        assertFalse(controller.testWordValidity("sjfsdkjfnkjsd"));
    }

    /**
     * Test word score of given word
     *
     * @param word word to test
     * @param score score of word
     */
    @ParameterizedTest
    @CsvSource({"egg, 25", "i, 1"})
    public void testGetWordScore(String word, int score){
        assertEquals(score, controller.getWordScore(word));
    }

    /**
     * Test saving to file
     */
   @Test
    public void testSaveToFile(){
        controller.generateRandomGrid();
        String[][][] generatedCube = controller.getCubeData();

        controller.saveGrid("test2.txt");
        controller.loadGrid("test2.txt");
        String[][][] savedCube = controller.getCubeData();

        assertArrayEquals(generatedCube, savedCube);
        // ...
        //assertTrue(file.exists());    not here
    }

    @ParameterizedTest
    @CsvSource({"0,0,0,E", "0,0,1,R", "0,0,2,T", "0, 1, 0, L", "0,1,1,A", "0,1,2,P", "0,2,0,O", "0,2,1,Qu", "0,2,2,T",
                "1,0,0,M", "1,0,1,N", "1,0,2,B", "1,1,0,L", "1,1,1,A", "1,1,2,P", "1,2,0,Qu", "1,2,1,U", "1,2,2,I",
                "2,0,0,Z", "2,0,1,M", "2,0,2,A", "2,1,0,Y", "2,1,1,T", "2,1,2,R", "2,2,0,D", "2,2,1,F", "2,2,2,G"})
    public void testLoadGridLetters(int x, int y, int z, String letter){
        controller.loadGrid("grid_2");
        String[][][] stringCube = controller.getCubeData();
        assertEquals(letter, stringCube[x][y][z]);
        //todo maybe not load the file each time....
    }

    /**
     * Test the setting of dictionaries
     */
    @Test
    public void setLanguageTest(){
        Settings.setCurrLang("en");
        assertTrue(controller.testWordValidity("egg"));
        assertFalse(controller.testWordValidity("Prifysgol"));

        Settings.setCurrLang("cy");
        assertTrue(controller.testWordValidity("Prifysgol"));
        assertFalse(controller.testWordValidity("egg"));
    }

}
