/*
   * @(#) DictionaryTests.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.gp01.test.java.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.aber.cs221.gp01.main.java.model.Dictionary;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Dictionary class
 *
 * @author Aleksandra Madej (alm82)
 * @version 1.1
 * @see Dictionary
 */
class DictionaryTests {

    Dictionary dic = new Dictionary();

    /**
     * Load dictionary before each test
     */
    @BeforeEach
    public void setUp() {
        dic.loadDictionary("test_dictionary");
    }


    /**
     * Test to see if dictionary loads
     */
    @Test
    public void testDictionaryLoad() {
        assertEquals(7, dic.getDictionarySize());

    }

    /**
     * Test to check if real words are contained within dictionary
     */
    @Test
    public void testSearchDictionary() {
        assertTrue(dic.searchDictionary("apron"));
        assertFalse(dic.searchDictionary("wordThatIsNotInDictionary"));
    }

}