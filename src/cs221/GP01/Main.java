/*
   * @(#) JoggleCube.java 1.1 2018/02/04
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01;

import cs221.GP01.model.JoggleCube;
import cs221.GP01.views.Start.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main - A class that starts the Main application.
 * <p>
 * Uses JavaFX to display the views
 * todo improve description
 *
 * <forRemovalLater>
 *  Useful Info:
 *
 *      To-do List:
 *          todo make a backend model
 *          todo maybe get a loading screen?
 *          todo write documentation
 *          todo fix proportions in Game, bigger cube? (can come back to this after model is sorted)
 *
 *          todo add flair to design and house style (low priority)
 *              - logo/banner?
 *              - start screen
 *
 *          todo add 'recents' list to previously played cubes
 *
 *
 *      Changelog:
 *          > Made a changelog (meta right?) - rhe24 - 5/2/2018
 *          > Changed window title to Main - rhe24 - 5/2/2018
 *          > Added text field for manual word entry - rhe24 - 5/2/2018
 *          > Re-worked how screens are changed so the controllers can talk to the backend
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
 * @version 0.2  DRAFT
 */
public class Main extends Application {

    /**
     * Starts The GUI and backend
     *
     * @author Nathan Williams (naw21)
     * @version 0.2 DRAFT
     * @param primaryStage The main Stage(window) of the application upon creation)
     * @throws IOException  if the Start.fxml is not found.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //todo add methods to launch model, loading dictionary, Highscore data etc.
        //todo maybe a loading screen while this happens.
        //launch the backend.
        JoggleCube joggleCube = new JoggleCube();


        //Load the first GUI from fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/Start/Start.fxml"));
        Parent root = fxmlLoader.load();
        //get the controller
        StartController startController = fxmlLoader.getController();

        //tell the controller about the backend
        startController.setJoggleCube(joggleCube);

        //Display the start Screen
        primaryStage.setTitle("Main");
        primaryStage.setScene(new Scene(root, 600, 600));
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
