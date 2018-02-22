package cs221.GP01.java.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Class which handles and looks after the Cube
 * @author Samuel Jones - srj12
 * @version 0.1
 */

public class Cube implements ICube{
    private Block cube[][][] = new Block[3][3][3];
    private ArrayList<String> bagOfLetters = new ArrayList<>();

    public Cube(){}

    @Override
    public Block getBlock(int x, int y, int z) {
        return cube[x][y][z];
    }

    @Override
    public void populateCube(String letterFilename) {
        loadBagOfLetters(letterFilename);
        Random randomNumGen = new Random();
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                for(int k = 0; k<3; k++){
                    //Generate a random index for the ArrayList, and add the letter from that point into the Cube
                    int randomNumber = randomNumGen.nextInt(bagOfLetters.size());
                    cube[i][j][k] = new Block(bagOfLetters.get(randomNumber));
                    //Make sure that the letter is removed from the "Bag O'Letters"
                    bagOfLetters.remove(randomNumber);
                }
            }
        }
    }

    @Override
    public ArrayList<int[]> getNeighbours(int x, int y, int z) {
        ArrayList<int[]> neighbours = new ArrayList<>();

        int originalX = x;
        int originalY = y;
        int originalZ = z;

        //Create an arraylist of potential neighbours
        for(int i = -1; i<2; i++){
            x = originalX;
            x += i;
            for(int j = -1; j<2; j++){
                y = originalY;
                y+=j;
                for(int k = -1; k<2; k++){
                    z = originalZ;
                    z += k;

                    int nextNeighbour[] = new int[3];
                    nextNeighbour[0] = x;
                    nextNeighbour[1] = y;
                    nextNeighbour[2] = z;
                    neighbours.add(nextNeighbour);
                }
            }
        }

        //Remove incorrect/impossible positions
        int arraySize = neighbours.size();
        for(int i = 0; i<arraySize; i++){
            //If the neighbour is equal to the original of the co-ordinates
            if(neighbours.get(i)[0] == originalX
                    && neighbours.get(i)[1] == originalY
                    && neighbours.get(i)[2] == originalZ){
                neighbours.remove(i);
                i--;
                arraySize--;
            } else

            //If the neighbours co-ords can not be possible then remove.
                if(neighbours.get(i)[0] < 0 || neighbours.get(i)[0] > 2
                        || neighbours.get(i)[1] < 0 || neighbours.get(i)[1] > 2
                        || neighbours.get(i)[2] < 0 || neighbours.get(i)[2] > 2){
                    neighbours.remove(i);
                    i--;
                    arraySize--;
            }
        }

        //return the list of neighbours
        return neighbours;
    }

    private void loadBagOfLetters(String lettersFilename){
        bagOfLetters.clear();
        File file = new File("src\\cs221\\GP01\\resource\\letters\\" + lettersFilename);

        String input;
        try{
            Scanner in = new Scanner(file);
            while(in.hasNext()){
                input = in.nextLine();
                bagOfLetters.add(input);
            }
        } catch(FileNotFoundException e){
            //An error in file name
            System.out.println("Dictionary file not found");
        }
    }
}
