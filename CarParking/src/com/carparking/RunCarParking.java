package com.carparking;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 31.10.14
 * Time: 11:29
 * To change this template use File | Settings | File Templates.
 */
public class RunCarParking extends Activity {
    private DBHelper dbHelper;
    private ListOfCars listOfCars;
    Intent serviceIntent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaying_cars_list);
        readDataFromContactsDB();
    }

    private void readDataFromContactsDB() {
        dbHelper = new DBHelper(this);
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
            listOfCars = new ListOfCars(this, cursor);
        } else {
            listOfCars = new ListOfCars(this);
            Toast toast = Toast.makeText(this, R.string.empty_list_message, Toast.LENGTH_SHORT);
            toast.show();
        }
        listOfCars.showList();
        cursor.close();
        dbHelper.close();
    }

    public void updateParking(View view) {
        Toast.makeText(this, "UPDATE", Toast.LENGTH_SHORT).show();
        readDataFromContactsDB();
    }

    public void startParking(View view) {
        if(serviceIntent == null) {
            serviceIntent = new Intent(this, ParkingService.class);
            startService(serviceIntent);
        } else {
            Toast.makeText(this, R.string.service_already_started, Toast.LENGTH_SHORT).show();
        }
    }

    public void stopParking(View view) {
        if(serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
        } else {
            Toast.makeText(this, R.string.service_already_stopped, Toast.LENGTH_SHORT).show();
        }
    }
}