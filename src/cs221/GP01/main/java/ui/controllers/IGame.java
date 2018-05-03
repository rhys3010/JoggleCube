/*
 * @(#) IGame.java 1.1 2018/04/02
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui.controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

/**
 * GameView - An interface that controls the Game scene that is defined in Game.fxml
 * Used with Game.fxml
 * This controller controller the game view it contains the timer update methods, score update methods and runs the front end logic changing the colours of the grid with the help of GridDisplayer
 *
 * @see GridDisplayer
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Samuel Jones - srj12@aber.ac.uk
 * @version 1.1
 */
public interface IGame {

    /**
     * Returns the list of found words so it can be used in the backend as it is currently only stored in the frontend
     *
     * @return returns an observableList of strings of all currently found words
     * @author Samuel Jones - srj12
     */
    ObservableList<String> getFoundWords();

    /**
     * gets timerTable
     *
     * @return timerLabel
     */
    Label getTimerLabel();

    /**
     * gets scoreLabel
     *
     * @return scoreLabel
     */
    Label getScoreLabel();

    /**
     * gets cubeContainer
     *
     * @return cubeContainer
     */
    TabPane getCubeContainer();
}
