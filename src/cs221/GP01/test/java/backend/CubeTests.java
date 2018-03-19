package cs221.GP01.test.java.backend;

import cs221.GP01.main.java.model.Block;
import cs221.GP01.main.java.model.Cube;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class CubeTests {

    Cube cube;

    @BeforeEach
    public void load() {
        cube = new Cube("en_letters");
        cube.populateCube();
    }

    @Test
    public void testOccurrences(){

        ArrayList<String> cubeLetters = new ArrayList<>();

        for(int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cubeLetters.add(cube.getBlock(i,j,k).getLetter());
                }
            }
        }
        assertTrue(Collections.frequency(cubeLetters, "A") <= 9);
        assertTrue(Collections.frequency(cubeLetters, "B") <= 2);
        assertTrue(Collections.frequency(cubeLetters, "I") <= 9);
        assertTrue(Collections.frequency(cubeLetters, "Qu") <= 1);
        assertTrue(Collections.frequency(cubeLetters, "Y") <= 2);
        assertTrue(Collections.frequency(cubeLetters, "Z") <= 1);

    }

    @Test
    public void testLoadBagOfLetters (){
        cube = new Cube("en_letters");
        ArrayList<String> bagOfLetters = cube.getBagOfLetters();
        assertEquals(97, bagOfLetters.size());
    }

    @Test
    public void testLetterScores (){

        assertEquals(1,Integer.parseInt(cube.getScores().get("A")));
        assertEquals(3,Integer.parseInt(cube.getScores().get("B")));
        assertEquals(4,Integer.parseInt(cube.getScores().get("F")));
        assertEquals(8,Integer.parseInt(cube.getScores().get("Qu")));
        assertEquals(4,Integer.parseInt(cube.getScores().get("Y")));
        assertEquals(10,Integer.parseInt(cube.getScores().get("Z")));

    }
    @Test
    public void testIsPopulateCubeRandom(){
        String[] letter = new String[3];

        for(int i=0; i<3; i++) {
            cube.populateCube();
            letter[i] = cube.getBlock(0, 0, 0).getLetter();
            System.out.println(letter[i]);
        }

    assertFalse(letter[0].equals(letter[1]) && letter[1].equals(letter[2]));
    }

    @Test
    public void testNeighboursNum(){

        ArrayList<int[]> neighbours;

        neighbours = cube.getNeighbours(0,0,0);
        assertEquals(7, neighbours.size());

        neighbours = cube.getNeighbours(1,1,1);
        assertEquals(26, neighbours.size());

        neighbours = cube.getNeighbours(0,1,0);
        assertEquals(11, neighbours.size());

        neighbours = cube.getNeighbours(0,1,1);
        assertEquals(17, neighbours.size());

    }

    @Test
    public void testNeighbourPos (){

        ArrayList<int[]> neighbours;

        int x; int z; int y;
        x = y = z = 0;

        neighbours = cube.getNeighbours(x,y,z);

        for (int[] i: neighbours){
            assertTrue(Math.abs(i[0] - x) <= 1);
            assertTrue(Math.abs(i[1] - y) <= 1);
            assertTrue(Math.abs(i[2] - z) <= 1);
        }
    }

}
