package com.db.app.travelersapp.constant;

import com.db.app.travelersapp.WelcomeActivity;

/**
 * Constants related to database file
 * Such as file path and file name
 */
public abstract class DBConstant
{
    //database file directory
    public static String DATABASE_PATH = "/data/data/"+ WelcomeActivity.PACKAGE_NAME+"/databases";
    //database file name
    public static String DATABASE_FILE = "travelapp.db";
    //database version
    public static int DATABASE_VERSION = 1;
}
