package com.example.pedroclericuzi.exercicioandroid.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.activities.MainActivity;

import java.util.List;

/**
 * Created by pedroclericuzi on 07/06/2017.
 */

public class Notifications {

    public void Notificar(Context context){//Context context, Class<?> activity, CharSequence titulo, List<CharSequence> mensagem, CharSequence textoInicial){
        //NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Notification notification = new Notification(R.mipmap.ic_launcher, textoInicial, System.currentTimeMillis());

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context.getApplicationContext())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My Notification Title")
                        .setContentText("Something interesting happened");
        int NOTIFICATION_ID = 12345;

        Intent targetIntent = new Intent(context.getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.cancel(NOTIFICATION_ID);
        nManager.notify(NOTIFICATION_ID, builder.build());

    }

}
