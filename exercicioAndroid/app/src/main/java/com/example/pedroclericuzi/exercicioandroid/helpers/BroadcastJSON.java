package com.example.pedroclericuzi.exercicioandroid.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pedroclericuzi on 29/05/2017.
 */

public class BroadcastJSON extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Script log", "Testando meu broadcast");
    }
}
