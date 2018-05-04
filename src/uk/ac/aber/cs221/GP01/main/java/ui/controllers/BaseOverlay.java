/*
 * @(#) BaseOverlayController.java 1.1 2018/02/23
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.GP01.main.java.ui.controllers;

/**
 * The Parent Class of any overlay controller
 *
 * @author Rhys Evans (rhe24@aber.ac.uk)
 * @author Nathan Williams (naw21@aber.ac.uk)
 * @version 1.1
 */
public class BaseOverlay {

    /**
     * An instance of the overlay's parent controller
     */
    protected BaseScreen parentController;


    /**
     * Set the Overlay's parent controller
     *
     * @param parent The controller of the parents scene
     */
    public void setParentController(BaseScreen parent){
        this.parentController = parent;
    }

}
