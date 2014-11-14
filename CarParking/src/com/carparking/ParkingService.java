package com.carparking;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 31.10.14
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public class ParkingService extends Service {
    private static final String LOG_TAG = "Service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
       super.onDestroy();
       Toast.makeText(this, R.string.destroying_service, Toast.LENGTH_SHORT).show();
       Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        Toast.makeText(this, R.string.starting_service, Toast.LENGTH_SHORT).show();
        parkCars();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    void parkCars() {
        Toast.makeText(this, "HI", Toast.LENGTH_SHORT).show();
        CarRunner runner = new CarRunner();
        runner.start();
    }

    class CarRunner extends Thread {
        public void run() {
            Car car;
            Intent broadcastIntent;
            while(true) {
                try {
                    car = new Car();
                    Log.d(LOG_TAG, "send broadcast");
                    broadcastIntent = new Intent(Constants.BROADCAST_ACTION);
                    broadcastIntent.putExtra(Constants.EXTRA_CAR, car);
                    sendBroadcast(broadcastIntent);
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
