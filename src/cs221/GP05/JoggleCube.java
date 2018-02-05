/*
   * @(#) JoggleCube.java 1.1 2018/02/04
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP05;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JoggleCube - A class that starts the JoggleCube application.
 * <p>
 * Uses JavaFX to display the GUI
 * todo improve description
 *
 * <forRemovalLater>
 *  Useful Info:
 *
 *      To-do List:
 *          todo make a backend model
 *          todo make more GUIs: HighScore, pause, Loading etc read the spec.
 *          todo link game logic to GUI
 *          todo write documentation
 *          todo fix proportions in GameGUI, bigger cube? (can come back to this after model is sorted)
 *
 *          todo add flair to design and house style (low priority)
 *              - logo/banner?
 *              - start screen
 *
 *          todo add 'recents' list to previously played cubes
 *
 *          lol that was easy.
 *
 *      Changelog:
 *          > Made a changelog (meta right?) - rhe24 - 5/2/2018
 *          > Changed window title to JoggleCube - rhe24 - 5/2/2018
 *          > Added text field for manual word entry - rhe24 - 5/2/2018
 *
 *
 *      Hints and Suggestions:
 *          When writing code comment everythingL
 *          Leave todos where stuff might be added later so it's easy to find.
 * </forRemovalLater>
 *
 *
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1  DRAFT
 */
public class JoggleCube extends Application {

    /**
     * Starts The GUI
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @param primaryStage The main Stage(window) of the application upon creation)
     * @throws IOException  if the GameGUI.fxml is not found.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/StartGUI/StartGUI.fxml"));
        primaryStage.setTitle("JoggleCube");
        primaryStage.setScene(new Scene(root, 600, 600));
        //todo add methods to launch model, loading dictionary, Highscore data etc.
        //todo maybe a loading screen while this happens.
        primaryStage.show();
    }

    /**
     * The main method
     *
     * @author Nathan Williams (naw21)
     * @version 0.1 DRAFT
     * @param args no launch arguments needed.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
