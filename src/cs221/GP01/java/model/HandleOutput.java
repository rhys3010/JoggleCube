package cs221.GP01.java.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class HandleOutput {

    public String[][][] getCubeData(){
        //todo create an actual list of data
        System.out.println("Loading Cube");

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Qu","R","S","T","U","V","W","X","Y","Z"};
        int N = alphabet.length;

        Random r = new Random();
        String[][][] letters = new String[3][3][3];
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                for (int k = 0; k<3; k++){
                    letters[i][j][k] = alphabet[r.nextInt(N)];
                }
            }
        }


        return letters;
    }

    public ObservableList<HighScore> getLoadedGridHighScores(){
        //todo create an actual list of data
        //if(gridloaded){
        // return list of scores;
        // } else {
        return null;
        // }
    }

    public ObservableList<HighScore> getOverallHighScores(){
        System.out.println("getting overall High Scores");
        //List of Example High Score entries
        //todo create an actual list of data
        ObservableList<HighScore> highScores = FXCollections.observableArrayList(
                new HighScore(5161, "John"),
                new HighScore(1415, "Adam"),
                new HighScore(2515, "Wendy"),
                new HighScore(5151, "Steve"),
                new HighScore(6711, "Pete"),
                new HighScore(1314, "Dave"),
                new HighScore(4215, "Joe"),
                new HighScore(1455, "Joan"),
                new HighScore(6161, "Sara"),
                new HighScore(3671, "Andrew"),
                new HighScore(1551, "James"),
                new HighScore(2565, "Levi")
        );

        return highScores;
    }

    /**
     * Get recently played/loaded grids
     * @return a list of the recently opened grids
     */
    public ObservableList<String> getRecentGrids() {
        System.out.println("getting recent grids");
        //todo create an actual list of data
        //List of Example files to load
        ObservableList<String> recentGrids = FXCollections.observableArrayList (
                "something/something/grid01.grid",
                "grid02.grid",
                "grid03.grid",
                "grid04.grid"
        );
        return recentGrids;
    }
}
