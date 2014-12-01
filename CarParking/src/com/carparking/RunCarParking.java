package com.carparking;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class RunCarParking extends Activity {
    private static final String LOG_TAG = "MyActivity";
    private DBHelper dbHelper;
    private ListOfCars listOfCars;
    private Intent serviceIntent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        setContentView(R.layout.displaying_cars_list);
        readDataFromContactsDB();
    }

    public void onDestroy() {
        super.onDestroy();
        if(dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }
        listOfCars = null;
        serviceIntent = null;
    }

    private synchronized void readDataFromContactsDB() {
        Log.d(LOG_TAG, "read");
        Activity ctx = RunCarParking.this;
        dbHelper = new DBHelper(ctx);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
            FeedReaderContract.FeedEntry._ID,
            FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBERPLATE,
            FeedReaderContract.FeedEntry.COLUMN_NAME_MODEL,
            FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR,
            FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR_CODE
        };
        Cursor cursor = db.query(FeedReaderContract.FeedEntry.TABLE_NAME,
                            projection,
                            null, null, null, null, null);
        if (cursor.moveToFirst()) {
            listOfCars = new ListOfCars(ctx, cursor);
        } else {
            listOfCars = new ListOfCars(ctx);
            Toast toast = Toast.makeText(ctx, R.string.empty_list_message, Toast.LENGTH_SHORT);
            toast.show();
        }
        cursor.close();
        listOfCars.showList();
        dbHelper.close();
    }

    public void updateParking(View view) {
        Log.d(LOG_TAG,"update");
        Toast.makeText(this, "Updating info", Toast.LENGTH_SHORT).show();
        readDataFromContactsDB();
    }

    public void startParking(View view) {
        Log.d(LOG_TAG, "startService");
        if(serviceIntent == null) {
            serviceIntent = new Intent(this, ParkingService.class);
            startService(serviceIntent);
            Log.d(LOG_TAG, "started");
        } else {
            Toast.makeText(this, R.string.service_already_started, Toast.LENGTH_SHORT).show();
        }
    }

    public void stopParking(View view) {
        Log.d(LOG_TAG, "stopService");
        if(serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
            Log.d(LOG_TAG, "stopped");
        } else {
            Toast.makeText(this, R.string.service_already_stopped, Toast.LENGTH_SHORT).show();
        }
    }
}