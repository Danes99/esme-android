package org.libreapps.thinkit.data.sqlite;

import android.provider.BaseColumns;

public class DbConstants implements BaseColumns {

    // commons
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    public static final String COLUMN_NAME_NULLABLE = null;

    // result
    public static final String POST_TABLE_NAME = "post";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String OWNER = "owner";
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";
    public static final String VERSION = "__v";
    public static final String IS_COMPLETE = "complete";
    public static final String IS_FAVORITE = "isFavorite";


    //Database Table creation query
    public static final String SQL_CREATE_POST_ENTRIES =
            "CREATE TABLE " + POST_TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    ID + TEXT_TYPE + COMMA_SEP +
                    TITLE + TEXT_TYPE + COMMA_SEP +
                    CONTENT + TEXT_TYPE + COMMA_SEP +
                    OWNER + TEXT_TYPE + COMMA_SEP +
                    CREATED_AT + TEXT_TYPE + COMMA_SEP +
                    UPDATED_AT + TEXT_TYPE + COMMA_SEP +
                    VERSION + TEXT_TYPE + COMMA_SEP +
                    IS_FAVORITE + INTEGER_TYPE + COMMA_SEP +
                    IS_COMPLETE + INTEGER_TYPE + " )";



    //Drop table if already exist
    public static final String SQL_DELETE_POST_ENTRIES =
            "DROP TABLE IF EXISTS " + POST_TABLE_NAME;




}
