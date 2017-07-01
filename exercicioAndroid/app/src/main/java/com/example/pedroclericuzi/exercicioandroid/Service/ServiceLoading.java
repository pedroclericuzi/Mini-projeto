package com.example.pedroclericuzi.exercicioandroid.service;
import com.example.pedroclericuzi.exercicioandroid.helpers.ThreadLivros;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by pedroclericuzi on 29/05/2017.
 */

public class ServiceLoading extends Service{
    public static final String BROADCAST_ACTION = "com.example.pedroclericuzi.exercicioandroid.helpers.displayevent";
    Intent intent;
    public Boolean running = true;
    private final Handler handler = new Handler();
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Script log", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Script log", "onCreate");
        intent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Script log", "onDestroy");
        running = false;
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 10000); // 10 seconds
        }
    };

    private void DisplayLoggingInfo() {
        ThreadLivros threadLivros = new ThreadLivros();
        threadLivros.getThread(running, getApplicationContext());
        sendBroadcast(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 10000);
        return super.onStartCommand(intent, flags, startId);
    }
}
