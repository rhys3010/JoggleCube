/*
   * @(#) Main.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01;

import cs221.GP01.java.ui.Mediator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main - A class that starts the Main application.
 * <p>
 * Uses JavaFX to display the view
 *
 * @author Nathan Williams (naw21)
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.3
 */
public class Main extends Application {

    /**
     * Start the JavaFX
     *
     * @param primaryStage The main Stage(window) of the application upon creation)
     * @throws IOException  if the Start.fxml is not found.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        // Create main scene and initialize with a dummy root node
        Pane dummyRoot = new Pane();
        Scene mainScene = new Scene(dummyRoot, 600, 600);

        // Create, Initialize and show the stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Joggle Cube");
        primaryStage.setResizable(false);
        primaryStage.show();

        // Create a Joggle Cube object
        Mediator mediator = new Mediator();

        // Initialize Mediator game
        mediator.initialize(mainScene);

    }

    /**
     * The main method
     *
     * @param args no launch arguments needed.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
