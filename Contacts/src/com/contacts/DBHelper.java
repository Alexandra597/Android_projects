package com.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 03.10.14
 * Time: 20:11
 * To change this template use File | Settings | File Templates.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String DB_NAME = "contactsDB";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FeedReaderContract.FeedEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                FeedReaderContract.FeedEntry.COLUMN_NAME_SURNAME + TEXT_TYPE + COMMA_SEP +
                FeedReaderContract.FeedEntry.COLUMN_NAME_PHONE + TEXT_TYPE + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
