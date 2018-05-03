/*
   * @(#) HighScoresTest.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package cs221.GP01.test.java.backend;

import cs221.GP01.main.java.model.HighScores;
import cs221.GP01.main.java.model.IScore;
import cs221.GP01.main.java.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for HighScores class
 *
 * @author Aleksandra Madej (alm82)
 * @version 1.1
 * @see HighScores
 */
class HighScoresTest {

    HighScores scoreList;

    /**
     * Create scoreList before each test
     */
    @BeforeEach
    public void setUp(){
        scoreList = new HighScores();
    }

    /**
     * Test the loading of HighScores
     *
     * @throws FileNotFoundException
     */
    @Test
    void loadScores() throws FileNotFoundException {

        Scanner file = new Scanner(getClass().getResourceAsStream("/cs221/GP01/test/resource/scoreTest.txt"));
        scoreList.loadScores(file);

        assertEquals(3,scoreList.getScores().size());

        assertEquals("HighScores:[score =54, date ='2018/05/20 12:02', name ='player1', " +
                        "score =2, date ='2019/02/01 11:45', name ='player2', " +
                        "score =120, date ='2018/07/05 23:56', name ='player3']", scoreList.toString());
    }

    /**
     * Test the saving of HighScores
     *
     * @throws FileNotFoundException
     */
    @Test
    void saveScores() throws FileNotFoundException{

        Random rand = new Random();

        for(int i =0; i<3; i++) {
            int randScore = rand.nextInt(100);
            String name = "player" + Integer.toString(randScore);
            Score score = new Score(randScore, name);
            scoreList.addScore(score);
        }

        File file = new  File ("highscoresSaveTest.txt");
        PrintWriter pwfile = new PrintWriter(file);
        scoreList.saveScores(pwfile);
        pwfile.close();

        HighScores newScoreList = new HighScores();
        Scanner in = new Scanner(file);
        newScoreList.loadScores(in);
        in.close();
        assertIterableEquals(scoreList.getScores(), newScoreList.getScores());
        file.delete();
    }

    /**
     * Test the getting of HighScores
     *
     * @throws FileNotFoundException
     */
    @Test
    void getHighestScore() throws FileNotFoundException {

        Scanner file = new Scanner(getClass().getResourceAsStream("/cs221/GP01/test/resource/scoreTest.txt"));
        scoreList.loadScores(file);
        file.close();
        assertEquals((Integer)120, scoreList.getHighestScore().getScore());
    }
}