package cs221.GP01.test.java;

import cs221.GP01.main.java.model.Score;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    public void createScore(){

        Score score = new Score(5, "egg");
        assertTrue(score.getDate().matches("^20\\d{2}/\\d{2}/\\d{2} \\d{2}:\\d{2}"));

    }

    @Test
    public void createScoreFromFile() throws FileNotFoundException {

        File file = new File("scoreTest.txt");
        Scanner in = new Scanner(file);
        Score score = new Score(in);
        assertEquals("2018/05/20 12:02", score.getDate());
        assertEquals("player1", score.getName());
        assertEquals((Integer)54, score.getScore());

    }

    @Test
    public void saveScore() throws FileNotFoundException {

        Random rand = new Random();
        int randScore = rand.nextInt(100);

        Score score = new Score(randScore,"playerName");
        File file = new File("scoreSaveTest.txt");
        PrintWriter pwfile = new PrintWriter(file);
        score.saveScore(pwfile);
        pwfile.close();

        Scanner in = new Scanner(file);
        Score newScore = new Score(in);

        assertEquals(score, newScore);

    }

}