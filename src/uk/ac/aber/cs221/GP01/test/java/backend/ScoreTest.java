/*
   * @(#) ScoreTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.test.java.backend;

import org.junit.jupiter.api.Test;
import uk.ac.aber.cs221.GP01.main.java.model.Score;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for score class
 *
 * @author Aleksandra Madej (alm82)
 * @version 1.1
 * @see Score
 */
class ScoreTest {

    /**
     * Test creation of score
     */
    @Test
    public void createScore() {
        Score score = new Score(5, "egg");
        assertTrue(score.getDate().matches("^20\\d{2}/\\d{2}/\\d{2} \\d{2}:\\d{2}"));
    }

    /**
     * Test creation of score from file
     *
     * @throws FileNotFoundException
     */
    @Test
    public void createScoreFromFile() throws FileNotFoundException {
        Scanner in = new Scanner(getClass().getResourceAsStream("/uk/ac/aber/cs221/GP01/test/resource/scoreTest.txt"));
        Score score = new Score(in);
        in.close();
        assertEquals("2018/05/20 12:02", score.getDate());
        assertEquals("player1", score.getName());
        assertEquals((Integer) 54, score.getScore());
    }

    /**
     * Test saving of scores
     *
     * @throws FileNotFoundException
     */
    @Test
    public void saveScore() throws FileNotFoundException {
        Random rand = new Random();
        int randScore = rand.nextInt(100);

        Score score = new Score(randScore, "playerName");
        File file = new File("scoreSaveTest.txt");
        PrintWriter pwfile = new PrintWriter(file);
        score.saveScore(pwfile);
        pwfile.close();

        Scanner in = new Scanner(file);
        Score newScore = new Score(in);
        in.close();
        assertEquals(score, newScore);
        file.delete();
    }
}