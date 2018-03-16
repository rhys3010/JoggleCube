/*
   * @(#) Main.java 1.1 2018/02/04
   *
   * Copyright (c) 2018 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package cs221.GP01;

import cs221.GP01.main.java.model.JoggleCube;
import cs221.GP01.main.java.ui.UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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

        //As a fix for the inability to load custom fonts on some computers (apparently a common bug in JavaFX8+)
        Font.loadFont(getClass().getResourceAsStream("/cs221/GP01/main/resource/font/DS-DIGI.TTF"), 16);

        //start the backend
        JoggleCube.getInstance();

        // Create main scene and initialize with a dummy root node
        Pane dummyRoot = new Pane();
        Scene mainScene = new Scene(dummyRoot, 600, 600);

        // Create, Initialize and show the stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Joggle Cube");
        primaryStage.setResizable(false);

        // Add all icon sizes to stage
        for(int i = 0; i < 4; i++){
            primaryStage.getIcons().add(new Image("/cs221/GP01/main/resource/img/icon/icon" + (int)Math.pow(2,(i+4)) + ".png"));
        }

        primaryStage.setOnCloseRequest(e ->{


            // Display 'are you sure' overlay
            Alert sureAlert = new Alert(Alert.AlertType.CONFIRMATION);
            sureAlert.setTitle("Quit Game");
            sureAlert.setHeaderText(null);
            sureAlert.setContentText("Are you sure you want to quit the game?");
            Optional<ButtonType> result = sureAlert.showAndWait();

            if (!(result.get() == ButtonType.OK)) {
                sureAlert.close();
                // Consume the event and stop the program from closing
                e.consume();
            } else {
                // Save the highscores and allow the program to quit
                JoggleCube.getInstance().saveOverallScores();
            }
        });
        primaryStage.show();

        // Initialize UI game
        UI.getInstance().initialize(mainScene);
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
