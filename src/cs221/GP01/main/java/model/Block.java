/*
   * @(#) Block.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.main.java.model;

/**
 * The Interface which holds all the details for the Block
 *
 * @author Samuel Jones - srj12
 * @version 1.1
 * @see IBlock
 */

public class Block implements IBlock {
    private String letter;

    /**
     * Constructor for class Block
     *
     * @param newLetter
     */
    public Block(String newLetter){
        this.letter = newLetter;
    }

    /**
     * Sets the letter of the block and handles it
     *
     * @param newLetter
     */
    @Override
    public void setLetter(String newLetter) {
        letter = newLetter;
    }

    /**
     * Returns the letter in the block
     */
    public String getLetter(){
        return letter;
    }
}
