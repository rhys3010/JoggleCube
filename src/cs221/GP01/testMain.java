package cs221.GP01;


import cs221.GP01.main.java.model.HighScores;
import cs221.GP01.main.java.model.IHighScores;
import cs221.GP01.main.java.model.IScore;
import cs221.GP01.main.java.model.Score;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;



public class testMain {

    public static void main(String[] args) throws FileNotFoundException {
        testMain main = new testMain();
        main.generateData();
        main.loadData();
    }

    private void generateData() throws FileNotFoundException {
        IHighScores highScores = new HighScores();
        IScore s1 = new Score(13, "john");
        highScores.addScore(s1);
        IScore s2 = new Score(17, "nick");
        highScores.addScore(s2);
        IScore s3 = new Score(21, "peter");
        highScores.addScore(s3);
        IScore s4 = new Score(25, "pete");
        highScores.addScore(s4);
        IScore highS = highScores.getHighScore();
        System.out.println(highS.toString());
        PrintWriter names = new PrintWriter(new File("file.highscores"));
        highScores.saveScores(names);
        names.close();
    }

    private void loadData() throws FileNotFoundException {
        Scanner file = new Scanner(new File("file.highscores"));
        IHighScores highScores = new HighScores();
        highScores.loadScores(file);
        System.out.println(highScores.toString());
    }
}
