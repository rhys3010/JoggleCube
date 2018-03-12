/*
 * @(#) Settings.java 1.0 2018/03/05
 *
 * Copyright (c) 2018 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package cs221.GP01.main.java.ui;

/**
 * Handle the system settings from the settings overlay
 * @author Rhys Evans
 * @author Nathan Williams (naw21)
 * @version 0.2  DRAFT
 */
public class Settings {

    private static Settings settings;

    private Settings(){}

    public static Settings getInstance(){
        if(settings == null){
            synchronized (Settings.class){
                if(settings == null){
                    settings = new Settings();
                }
            }
        }
        return settings;
    }



}
