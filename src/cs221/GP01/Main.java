/*
   * @(#) Main.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01;

import cs221.GP01.java.model.IJoggleCubeController;
import cs221.GP01.java.model.JoggleCubeController;
import cs221.GP01.java.model.PretendBackEnd;
import cs221.GP01.java.ui.UIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        //todo launch the real back end
        IJoggleCubeController joggleCube = new PretendBackEnd();

        // Array to store all program icons
        Image icons[] = {
                new Image("cs221/GP01/resource/img/icon/icon16.png"),
                new Image("cs221/GP01/resource/img/icon/icon32.png"),
                new Image("cs221/GP01/resource/img/icon/icon64.png"),
                new Image("cs221/GP01/resource/img/icon/icon128.png")
        };

        // Create main scene and initialize with a dummy root node
        Pane dummyRoot = new Pane();
        Scene mainScene = new Scene(dummyRoot, 600, 600);

        // Create, Initialize and show the stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Joggle Cube");
        primaryStage.setResizable(false);

        // Add all icon sizes to stage
        // todo: is there a better way to do this? Probably. Maybe .ico support or some equivalent? ヽ༼ຈل͜ຈ༽ﾉ
        for(int i = 0; i < icons.length; i++){
            primaryStage.getIcons().add(icons[i]);
        }

        primaryStage.show();

        // Create a Joggle Cube object
        UIController UIController = new UIController(joggleCube);

        // Initialize UIController game
        UIController.initialize(mainScene);

    }

    /**
     * The main method
     *
     * @param args no launch arguments needed.
     */
    public static void main(String[] args) {
        //launch(args);
        JoggleCubeController main = new JoggleCubeController();
    }

}
