package cs221.GP01.java.ui.controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public interface IGameController {

    ObservableList<String> getFoundWords();

    Label getTimerLabel();

    Label getScoreLabel();

    TabPane getCubeContainer();
}
