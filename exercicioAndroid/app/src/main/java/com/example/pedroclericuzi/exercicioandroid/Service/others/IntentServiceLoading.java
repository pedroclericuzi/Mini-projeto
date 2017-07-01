package com.example.pedroclericuzi.exercicioandroid.service.others;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.helpers.ThreadLivros;

/**
 * Created by pedroclericuzi on 01/06/2017.
 */

public class IntentServiceLoading extends IntentService {
    private final String urlJson = "http://androidjsonteste.esy.es/filmes.json";
    public static final String BROADCAST_ACTION = "com.example.pedroclericuzi.exercicioandroid.helpers.displayevent";
    Intent intent;
    public Boolean running = true;
    ListView listView;
    private final Handler handler = new Handler();
    boolean ativo;
    int cont;
    int count;
    private boolean stopAll;

    public IntentServiceLoading() {
        super("ServiceIntentThread");
        count = 0;
        ativo = true;
        stopAll = true;
        intent = new Intent(BROADCAST_ACTION);
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            handler.postDelayed(this, 3000); // 10 seconds
        }
    };

    private void DisplayLoggingInfo() {
        ThreadLivros threadLivros = new ThreadLivros();
        threadLivros.getThread(running, getApplicationContext());
        sendBroadcast(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        Bundle b = intent.getExtras();
        if(b != null){
            int desligar = b.getInt("desligar");
            if(desligar == 1){
                stopAll = false;
            }
        }

        return(super.onStartCommand(intent, flags, startId));
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Log.d("log Script", "StoopAll: "+stopAll+ ", Ativo " + ativo);
        while(stopAll && ativo){
            try {
                handler.removeCallbacks(sendUpdatesToUI);
                handler.postDelayed(sendUpdatesToUI, 3000);
                Thread.sleep(3000);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            count++;
            Log.i("Script", "COUNT: "+count);
        }
        ativo = true;
        count = 0;
    }

}
