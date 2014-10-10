package com.contacts;

import android.provider.BaseColumns;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 03.10.14
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public final class FeedReaderContract {

    public FeedReaderContract() {}

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SURNAME = "surname";
        public static final String COLUMN_NAME_PHONE = "phone";
    }
}
