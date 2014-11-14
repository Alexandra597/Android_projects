package com.carparking;

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
        public static final String TABLE_NAME = "cars_list";
        public static final String COLUMN_NAME_NUMBERPLATE = "numberplate";
        public static final String COLUMN_NAME_MODEL = "model";
        public static final String COLUMN_NAME_COLOR = "color";
        public static final String COLUMN_NAME_COLOR_CODE = "color_code";
    }
}
