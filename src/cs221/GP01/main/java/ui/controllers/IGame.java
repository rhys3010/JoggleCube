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

public interface IGame {

    ObservableList<String> getFoundWords();

    Label getTimerLabel();

    Label getScoreLabel();

    TabPane getCubeContainer();
}
