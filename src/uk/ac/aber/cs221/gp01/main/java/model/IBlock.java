/*
   * @(#) IBlock.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.gp01.main.java.model;

/**
 * The Interface which holds all the details for the Block
 * @author Samuel Jones - srj12
 * @version 1.1
 */

public interface IBlock {

    /**
     * Returns the letter in the block
     */
    String getLetter();

    /**
     *  Sets the letter of the block and handles it
     * @param letter
     */
    void setLetter(String letter);
}
