package cs221.GP01.main.java.ui;

import cs221.GP01.main.java.model.IJoggleCubeController;
import cs221.GP01.main.java.ui.controllers.*;

public interface IUIController {

    IGameController getGameController();

    StartController getStartController();

    HighScoreController getHighScoreController();

    LoadGridController getLoadGridController();

    EndController getEndController();

    SettingsController getSettingsController();

    HelpController getHelpController();

    IViewNavigation getNavigationController();

    IJoggleCubeController getJoggleCube();
}
