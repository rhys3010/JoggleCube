package cs221.GP01.java.model;

/**
 * The Interface which holds all the details for the Block, it is argueable that this class is unnessercary and
 * @author Samuel Jones - srj12
 * @version 0.1
 */

public interface IBlock {
    /**
     * Generates a letter using the weighted levels of the "Scrabble bag"
     */
    String getLetter();

    /**
     *  Sets the letter of the block and handles it
     * @param letter
     */
    void setLetter(String letter);
}
