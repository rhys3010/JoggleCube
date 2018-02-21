package cs221.GP01.java.model;

/**
 * The Interface which handles and looks after the Cube
 * @author Samuel Jones - srj12
 * @version 0.1
 */

public interface ICube {
    /**
     * Returns the block at the given co-ordinates xyz as the block object
     * @param x
     * @param y
     * @param z
     * @return
     */
    Block getBlock(int x, int y, int z);

    /**
     * Generates the cube using sudo random Java functions in the block objects
     */
    void populateCube();


    ArrayList<Block*> getNeighbours(int x, int y, int z);
}
