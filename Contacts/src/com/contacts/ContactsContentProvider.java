package com.contacts;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static com.contacts.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 24.10.14
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
public class ContactsContentProvider extends ContentProvider {
    private static final int CODE_CONTACT = 1;
    static UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, PATH_CONTACT, CODE_CONTACT);
    }

    DBHelper helper;
    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        helper = new DBHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Override
    public String getType(Uri arg0) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int code = matcher.match(uri);
        if (code == CODE_CONTACT) {
            database.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        }
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String orderBy) {
        Cursor cursor = null;
        int code = matcher.match(uri);
        if (code == CODE_CONTACT) {
            cursor = database.query(FeedReaderContract.FeedEntry.TABLE_NAME, projection,
                                    selection, selectionArgs, null, null, orderBy);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String whereClause, String[] whereArgs) {
        int code = matcher.match(uri);
        if (code == CODE_CONTACT) {
            database.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, whereClause, whereArgs);
        }
        return 0;
    }

    @Override
    public int delete(Uri uri, String whereClause, String[] whereArgs) {
        int code = matcher.match(uri);
        if (code == CODE_CONTACT) {
            database.delete(FeedReaderContract.FeedEntry.TABLE_NAME, whereClause, whereArgs);
        }
        return 0;
    }

}
