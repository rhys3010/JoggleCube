/*
 * @(#) BaseOverlayController.java 0.1 2018/02/23
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.java.ui.controllers;

import cs221.GP01.java.ui.ScreenType;
import cs221.GP01.java.ui.UIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * The Parent Class of any overlay controller
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @version 0.1
 */
public class BaseOverlayController {

    /**
     * An instance of the UIController object to interface with backend
     */
    protected UIController UIController;

    /**
     * An instance of the overlay's parent controller
     */
    protected BaseScreenController parentController;


    /**
     * Constructor to ensure UIController object is passed
     * @param UIController
     */
    public BaseOverlayController(UIController UIController){
        this.UIController = UIController;
    }

    /**
     * Set the Overlay's parent controller
     * @param parent
     */
    public void setParentController(BaseScreenController parent){
        this.parentController = parent;
    }

}
