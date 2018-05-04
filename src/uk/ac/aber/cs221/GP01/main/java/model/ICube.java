/*
   * @(#) ICube.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.GP01.main.java.model;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Interface which handles and looks after the Cube
 * @author Samuel Jones - srj12
 * @version 1.1
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

    /**
     * Saves cube to specified file
     *
     * @param file filename
     * @return
     */
    boolean saveCube(PrintWriter file);

    /**
     * Loads cube from specified file
     *
     * @param file filename
     * @return true if successful
     */
    boolean loadCube(Scanner file);
    /**
     *
     * @param x Coordinate x
     * @param y Coordinate y
     * @param z Coorinnate z
     * @return true if successful
     */
    ArrayList<int[]> getNeighbours(int x, int y, int z);


}
