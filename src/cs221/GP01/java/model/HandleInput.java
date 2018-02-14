/*
   * @(#) HandleInput.java 1.1 2018/02/12
   *
   * Copyright (c) 2012 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01.java.model;

/**
 * HandleInput - Temporary Class to test UI -> Backend interfacing
 * @author Rhys Evans (rhe24)
 * @version 0.1
 */
public class HandleInput {

    public void loadMenu(){
        System.out.println("Menu Loaded");
    }

    public void startGame(){
        System.out.println("Game Started");
    }

    public void pauseGame(){
        System.out.println("Game Paused");
    }

    public void resumeGame(){
        System.out.println("Game Resumed");
    }

    public void endGame(){
        System.out.println("Game Ended");
    }

    public void loadCube(){
        System.out.println("Loading Cube");
    }

    public void showHighScores(){
        System.out.println("Showing High Scores");
    }
}
