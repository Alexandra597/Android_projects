package com.carparking;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;

public class ParkingService extends Service {
    private static final String LOG_TAG = "MyService";
    CarRunner runner;
    boolean running;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
        runner.interrupt();
        runner = null;
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
        runner = new CarRunner();
        running = true;
        runner.start();
    }

    class CarRunner extends Thread {
        public void run() {
            Log.d(LOG_TAG, "inThreadRunnerRun");
            Car car;
            Intent broadcastIntent;
            while(running) {
                try {
                    car = new Car();
                    Log.d(LOG_TAG, "send broadcast");
                    broadcastIntent = new Intent(Constants.BROADCAST_ACTION);
                    broadcastIntent.putExtra(Constants.EXTRA_CAR, car);
                    sendBroadcast(broadcastIntent);
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
