package com.carparking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String DB_NAME = "carsParkingDB";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                FeedReaderContract.FeedEntry._ID + INT_TYPE + " PRIMARY KEY AUTOINCREMENT," +
                FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBERPLATE + TEXT_TYPE + COMMA_SEP +
                FeedReaderContract.FeedEntry.COLUMN_NAME_MODEL + TEXT_TYPE + COMMA_SEP +
                FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR + TEXT_TYPE + COMMA_SEP +
                FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR_CODE + INT_TYPE + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
