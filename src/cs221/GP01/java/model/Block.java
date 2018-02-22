package cs221.GP01.java.model;

/**
 * The Interface which holds all the details for the Block
 * @author Samuel Jones - srj12
 * @version 0.1
 */

public class Block implements IBlock {
    private String letter;

    public Block(String newLetter){
        this.letter = newLetter;
    }

    /**
     * @param newLetter
     */
    @Override
    public void setLetter(String newLetter) {
        letter = newLetter;
    }

    public String getLetter(){
        return letter;
    }
}
