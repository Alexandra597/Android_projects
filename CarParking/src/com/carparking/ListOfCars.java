package com.carparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 10.10.14
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class ListOfCars {
    private ArrayList<Car> cars;
    private Activity activity;
    private CarsAdapter adapter;

    ListOfCars(Activity act) {
        cars = new ArrayList<Car>();
        activity = act;
        adapter = new CarsAdapter(activity, cars);
    }

    ListOfCars(Activity act, ArrayList<Car> carsList) {
        cars = carsList;
        activity = act;
        adapter = new CarsAdapter(activity, cars);
    }

    ListOfCars(Activity act, Cursor cursor) {
        activity = act;
        cars = new ArrayList<Car>();
        int idColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry._ID);
        int numberplateColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_NUMBERPLATE);
        int modelColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_MODEL);
        int colorColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR);
        int colorCodeColIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_NAME_COLOR_CODE);
        do {
            Car car = new Car(cursor.getString(numberplateColIndex),
                                         cursor.getString(modelColIndex),
                                         cursor.getString(colorColIndex),
                                         cursor.getInt(colorCodeColIndex));
            car.setDbID(cursor.getLong(idColIndex));
            cars.add(car);
        } while (cursor.moveToNext());
        adapter = new CarsAdapter(activity, cars);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void showList() {
        ListView listView = (ListView) activity.findViewById(R.id.carsListView);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                Context ctx = adapter.getContext();
                Car car = (Car)adapter.getItemAtPosition(position);
                DBHelper dbHelper = new DBHelper(ctx);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete(FeedReaderContract.FeedEntry.TABLE_NAME,
                        FeedReaderContract.FeedEntry._ID + "=" + car.getDbID(), null);
                return true;
            }
        });
    }
}
