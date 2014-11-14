package com.carparking;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CarReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = "Receiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG, "onReceive");
        Car car = (Car) intent.getParcelableExtra(Constants.EXTRA_CAR);
        DBHelper dbHelper = new DBHelper(context);
        ContentValues values = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBERPLATE, car.getNumberplate());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_MODEL, car.getModel());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR, car.getColorName());
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR_CODE, car.getColorCode());
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        dbHelper.close();
    }
}
