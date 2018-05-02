/*
 * @(#) IDialog.java 0.1 2018/04/30
 *
 * Copyright (c) 2012 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;


import javafx.scene.image.ImageView;

import java.util.Optional;

/**
 * IDialog - A central class to create and control and dialog popups used in the system
 * <p>
 * @author Rhys Evans (rhe24)
 * @version 0.1
 */
public interface IDialog {

    /**
     * Create a text input dialog to prompt the user for information
     * @param headerText
     * @param contentText
     * @param defaultValue
     * @param graphic
     * @param allowCancel
     * @return the text inputted by the user
     */
    String showInputDialog(String headerText, String contentText, String defaultValue, ImageView graphic, boolean allowCancel);

    /**
     * Create a confirmation dialog to prompt the user for confirmation for a given action
     * @param title
     * @param contentText
     * @return the user's choice
     */
    Optional showConfirmationDialog(String title, String contentText);

    /**
     * Create a information dialog to notify the user
     * @param title
     * @param contentText
     */
    void showInformationDialog(String title, String contentText);

    /**
     * Validate user input to ensure correctness
     * @param input - the user's input
     */
    boolean isValidInput(String input);

}
