/*
   * @(#) HandleInput.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.model;

import java.io.File;

/**
 * HandleInput - Temporary Class to test UI -> Backend interfacing
 * @author Rhys Evans (rhe24)
 * @version 0.1
 */
public class HandleInput {

    public void startRandomGame(){
        //todo load a random grid and start the timer
        System.out.println("Random Game Started");
    }

    public void startGame(File file){
        //todo load grid from file and start the timer
        System.out.println("Game Started from file");
    }

    public void pauseGame(){
        //todo pause the timer
        System.out.println("Game Paused");
    }

    public void resumeGame(){
        //todo resume the timer
        System.out.println("Game Resumed");
    }

    public void endGame(){
        //todo end the game early stop timer etc.
        System.out.println("Game Ended");
    }

    public boolean testWordvalidity(String word) {
        //todo test if word is valid in the dictionary or not.
        return true;
    }
}
