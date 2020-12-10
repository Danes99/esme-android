package org.libreapps.thinkit.data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper DatabaseHelper = null;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "thinkit_db";


    //Singleton method to get DatabaseHelper instance
    public static DatabaseHelper getInstance(Context context) {
        if (DatabaseHelper == null) {
            DatabaseHelper = new DatabaseHelper(context);
        }
        return DatabaseHelper;
    }

    //Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Database Create method
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbConstants.SQL_CREATE_POST_ENTRIES);
    }

    //Database Upgrade method
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbConstants.SQL_DELETE_POST_ENTRIES);

        onCreate(db);
    }

    //Database Downgrade method
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}

