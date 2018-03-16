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
