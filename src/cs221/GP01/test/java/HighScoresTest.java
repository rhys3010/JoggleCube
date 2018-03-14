package cs221.GP01.test.java;

import cs221.GP01.main.java.model.HighScores;
import cs221.GP01.main.java.model.IScore;
import cs221.GP01.main.java.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HighScoresTest {

    HighScores scoreList;

    @BeforeEach
    public void setUp(){
        scoreList = new HighScores();
    }

    @Test
    void loadScores() throws FileNotFoundException {

        Scanner file = new Scanner(new File("scoreTest.txt"));
        scoreList.loadScores(file);

        assertEquals(3,scoreList.getScores().size());

        assertEquals("HighScores:[score =54, date ='2018/05/20 12:02', name ='player1', " +
                        "score =2, date ='2019/02/01 11:45', name ='player2', " +
                        "score =120, date ='2018/07/05 23:56', name ='player3']", scoreList.toString());


    }

    @Test
    void saveScores() throws FileNotFoundException{

        Random rand = new Random();

        for(int i =0; i<3; i++) {
            int randScore = rand.nextInt(100);
            String name = "player" + Integer.toString(randScore);
            Score score = new Score(randScore, name);
            scoreList.addScore(score);
        }

        File file = new File("highscoresSaveTest.txt");
        PrintWriter pwfile = new PrintWriter(file);
        scoreList.saveScores(pwfile);
        pwfile.close();

        HighScores newScoreList = new HighScores();
        Scanner in = new Scanner(file);
        newScoreList.loadScores(in);

        assertIterableEquals(scoreList.getScores(), newScoreList.getScores());


    }


    @Test
    void getHighestScore() throws FileNotFoundException {

        Scanner file = new Scanner(new File("scoreTest.txt"));
        scoreList.loadScores(file);

        assertEquals((Integer)120, scoreList.getHighestScore().getScore());

    }


}