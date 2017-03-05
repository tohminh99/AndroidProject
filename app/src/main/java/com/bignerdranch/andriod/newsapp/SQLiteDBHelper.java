package com.bignerdranch.andriod.newsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by toh on 3/5/2017.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "articlereader.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID =  "id";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_PASSWORD =  "password";

    public static final String TABLE2_NAME = "article";
    public static final String COLUMN2_ID = "id";
    public static final String COLUMN2_TITLE = "title";
    public static final String COLUMN2_CONTENT = "content";
    public static final String COLUMN2_WRITER = "writer";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE "+TABLE_NAME+" ("+
                    COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_USER+" TEXT, "+
                    COLUMN_PASSWORD+" TEXT "+")";

    private static final String CREATE_TABLE2_QUERY =
            "CREATE TABLE "+TABLE2_NAME+" ("+
                    COLUMN2_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN2_TITLE+" TEXT, "+
                    COLUMN2_CONTENT+" TEXT, "+
                    COLUMN2_WRITER+" TEXT "+")";



    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
        db.execSQL(CREATE_TABLE2_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        onCreate(db);

    }
    public Cursor getListContent()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE2_NAME,null);
        return data;
    }
}
