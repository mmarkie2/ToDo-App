package com.example.todo2.model;

import android.app.Application;
import android.content.Context;

import java.io.File;

public class ModelApplication extends Application {

    static private DatabaseHelper databaseHelper;

    public static void setDatabaseHelper(Context ctx, String dbFileName, File storageDirectory, boolean forceNewDBGeneration) {


        FileHelper fileHelper = new FileHelper(ctx, "tasks.db", storageDirectory);
        if (!fileHelper.getOutFile().exists() || forceNewDBGeneration) {
            fileHelper.copyFile();
        }

        //creating helper and testing if db file wont crash app, if so resetting db File
        try {
            ModelApplication.databaseHelper = new DatabaseHelper(ctx, dbFileName, storageDirectory);
//tests db
            ModelApplication.getDatabaseHelper().selectFromTable
                    ("tasks", "id, name, type, year, month, dayOfMonth");
        } catch (Exception e) {
            fileHelper.copyFile();
            ModelApplication.databaseHelper = new DatabaseHelper(ctx, dbFileName, storageDirectory);
        }


    }

    public static DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }


}
