package cs221.GP01.java.ui.controllers;

import javafx.scene.control.Label;
import javafx.scene.control.TabPane;

public interface IGameController {

    Label getTimerLabel();

    Label getScoreLabel();

    TabPane getCubeContainer();
}
