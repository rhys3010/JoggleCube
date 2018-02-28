/*
 * @(#) HelpController.java 1.0 2018/02/24
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.UIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the Help.fxml file to handle the displaying of various help screens
 * @author Rhys Evans (rhe24)
 */
public class HelpController extends BaseOverlayController implements INeedPrep{

    /**
     * The file path prefix to the helppages
     */
    private static final String PAGES_PATH_PREFIX = "../../../resource/view/helppages/";

    /**
     * List to store all the help screens available as FXML parent nodes
     * so they can be injected / removed from the help screen with ease
     */
    private ArrayList<Parent> helpScreens = new ArrayList<>();

    /**
     * The currently indexed help page (used to display correct page etc)
     */
    private int currentPageIndex;

    /**
     * The FXML node of the subscene that will act as a container for the help page
     */
    @FXML
    private SubScene helpPageContainer;

    /**
     * The FXML node of the carousel indicator container
     */
    @FXML
    HBox carouselIndicatorContainer;


    /**
     * Constructor to ensure UIController object is passed
     * and help subscenes are instantiated
     * @param UIController
     */
    public HelpController(UIController UIController) throws IOException{
        super(UIController);

        // Create all the pages as FXML parent nodes
        helpScreens.add(createHelpPage("ExampleHelpPage.fxml"));
        helpScreens.add(createHelpPage("AnotherExampleHelpPage.fxml"));
        helpScreens.add(createHelpPage("LastHelpPageExample.fxml"));
    }

    /**
     * Prep the overlay by loading the default page every time
     */
    @Override
    public void prepView(){
        // Set current index to 0
        currentPageIndex = 0;

        // Create the indicators at the bottom of the screen
        createCarouselIndicators();

        // Add behaviour to all the carousel indicators
        for(Node button : carouselIndicatorContainer.getChildren()){

        }

        // Inject FXML of first entry in the helpScreens list to the subscene root
        changePage();
    }

    
    /**
     * Handles the close button of the overlay being clicked
     */
    @FXML
    private void closeBtnClicked(){
        UIController.getNavigationController().hideOverlay(ScreenType.HELP, parentController);
    }

    /**
     * Handle the right navigation being pressed by increasing the current help
     * screen index by one and looping around using modulo and updating currently displayed
     * page
     */
    @FXML
    private void btnRightNavClicked(){
        // Increment the page index
        currentPageIndex++;

        // Make sure the value is within the bounds of the array (if not loop around)
        currentPageIndex %= helpScreens.size();

        // Update the page to display the correct screen
        changePage();
    }

    /**
     * Handle the right navigation being pressed by decreasing the current help
     * screen index by one and looping around using modulo and updating currently displayed
     * page
     */
    @FXML
    private void btnLeftNavClicked(){
        currentPageIndex--;

        // Make sure the value is within the bounds of the array (if not loop around)
        // todo this is really long maybe it can be shortened?
        if(currentPageIndex < 0) currentPageIndex = helpScreens.size() - 1;
        //currentPageIndex = (((currentPageIndex % helpScreens.size()) + helpScreens.size()) % helpScreens.size());

        // Update the page to display the correct screen
        changePage();
    }


    /**
     * Utility function to create the carouselIndicator icons depending on size of the list
     */
    @FXML
    private void createCarouselIndicators(){

        // Iteratively create a carouselindicator for each help screen
        for(int i = 0; i < helpScreens.size(); i++){
            Button newButton = new Button();

            newButton.getStyleClass().add("carouselIndicator");
            newButton.setMinSize(16, 16);

            carouselIndicatorContainer.getChildren().add(newButton);
        }
    }

    /**
     * Utility function to create the help pages as an FXMLLoader
     * @param name - the name of the FXML file
     * @return loader - the created FXML parent node
     */
    private Parent createHelpPage(String name) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PAGES_PATH_PREFIX + name));

        return loader.load();
    }

    /**
     * Utility function to change the page that is currently displayed
     */
    private void changePage(){

        // Set the FXML root
        helpPageContainer.setRoot(helpScreens.get(currentPageIndex));

        // De-select other carousel indicators
        for(int i = 0; i < carouselIndicatorContainer.getChildren().size(); i++){
            carouselIndicatorContainer.getChildren().get(i).getStyleClass().remove("carouselIndicator-selected");
        }

        // Update the carousel indicator
        carouselIndicatorContainer.getChildren().get(currentPageIndex).getStyleClass().add("carouselIndicator-selected");
    }

}
