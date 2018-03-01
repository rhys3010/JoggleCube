package cs221.GP01.java.tests;

import cs221.GP01.java.model.Cube;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CubeTests {

    Cube cube = new Cube();

    @Test
    public void testIsPopulateCubeRandom(){
        String[] letter = new String[3];

        for(int i=0; i<3; i++) {
            cube.populateCube("en_letters.txt");
            letter[i] = cube.getBlock(0, 0, 0).getLetter();
            System.out.println(letter[i]);
        }

    assertFalse(letter[0].equals(letter[1]) && letter[1].equals(letter[2]));
    }

    @Test
    public void testNeighboursNum(){

        cube.populateCube("en_letters.txt");
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
        cube.populateCube("en_letters.txt");
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
