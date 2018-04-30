/*
 * @(#) Dialog.java 0.1 2018/04/30
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import java.util.Optional;

public class Dialog implements IDialog {


    /**
     * Create a text input dialog to prompt the user for information
     * @param headerText
     * @param contentText
     * @param defaultValue
     * @param graphic
     * @param allowCancel
     * @return the text inputted by the user
     */
    public Optional showInputDialog(String headerText, String contentText, String defaultValue, ImageView graphic, boolean allowCancel) {
        TextInputDialog textInputDialog = new TextInputDialog(defaultValue);
        textInputDialog.setHeaderText(headerText);
        textInputDialog.setContentText(contentText);
        textInputDialog.setGraphic(graphic);
        textInputDialog.initStyle(StageStyle.UNDECORATED);
        textInputDialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(!allowCancel);


        return textInputDialog.showAndWait();
    }


    /**
     * Create a confirmation dialog to prompt the user for confirmation for a given action
     * @param title
     * @param contentText
     * @return the user's choice
     */
    public Optional showConfirmationDialog(String title, String contentText) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setTitle(title);
        confirmationDialog.setContentText(contentText);

        return confirmationDialog.showAndWait();
    }

    /**
     * Create a information dialog to notify the user
     * @param title
     * @param contentText
     */
    public void showInformationDialog(String title, String contentText) {
        Alert informationDialog = new Alert(Alert.AlertType.INFORMATION);
        informationDialog.setHeaderText(null);
        informationDialog.setTitle(title);
        informationDialog.setContentText(contentText);
    }
}
