package com.example.todo2.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int databaseVersion = 1;
    private final String DBName;
    private final Context context;
    private final String DBDirectoryPath;
    private SQLiteDatabase DBClass;


    public DatabaseHelper(Context context, final String databaseName, File storageDirectory) {

        super(context, databaseName, null, databaseVersion);
        this.DBName = databaseName;
        this.context = context;
        this.DBDirectoryPath = storageDirectory.getPath();


        this.openDataBase();

    }


    public boolean openDataBase() throws SQLException {
        String mPath = DBDirectoryPath + "/" + DBName;

        DBClass = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        return DBClass != null;
    }

    @Override
    public synchronized void close() {
        if (DBClass != null)
            DBClass.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertIntoTable(String tableName, ContentValues values) {
//insert function which is safe from typical sql injection
        return this.DBClass.insert(tableName, null, values);
    }

    public long deleteFromTable(String tableName, int id) {

        return this.DBClass.delete(tableName, "id=" + id, null);
    }

    public ArrayList<ArrayList<String>> selectFromTable(String tableName, String columnsStr) {
        String query1 = "SELECT " + columnsStr + " FROM " + tableName;

        Cursor cursor = DBClass.rawQuery(query1, null);
        ArrayList<ArrayList<String>> returnedArray = new ArrayList<ArrayList<String>>();
        if (cursor.moveToFirst()) {
            do {
                ArrayList<String> buf = new ArrayList<String>();
                for (int i = 0; i < cursor.getColumnCount(); ++i) {
                    String cell = cursor.getString(i);
                    buf.add(cell);
                }
                returnedArray.add(buf);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return returnedArray;
    }
}