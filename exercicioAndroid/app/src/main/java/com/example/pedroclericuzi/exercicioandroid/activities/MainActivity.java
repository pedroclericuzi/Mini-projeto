package com.example.pedroclericuzi.exercicioandroid.activities;
import com.example.pedroclericuzi.exercicioandroid.adapter.*;
import com.example.pedroclericuzi.exercicioandroid.helpers.*;
import com.example.pedroclericuzi.exercicioandroid.data.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.R;
import com.example.pedroclericuzi.exercicioandroid.service.ServiceLoading;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

public class MainActivity extends AppCompatActivity {
    
    modelJSON json_model = new modelJSON();
    BaixarFilme baixarLivro = new BaixarFilme();
    Intent it;
    ArrayAdapter adaper;
    private ListView listView;
    DBFilmes dbFilmes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista_filmes);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent it) {
            updateUI(it);
        }
    };

    @Override
    protected void onStart() {
        it = new Intent(this, ServiceLoading.class);
        startService(it);
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //new ConteudoSync(MainActivity.this).execute(urlJson);
        try {
            registerReceiver(broadcastReceiver, new IntentFilter(ServiceLoading.BROADCAST_ACTION));
        } catch (Exception e){ }
    }

    @Override
    protected void onStop() {
        unregisterReceiver(broadcastReceiver);
        //Intent it = new Intent(this, IntentServiceLoading.class);
        //it.putExtra("desligar", 1);
        //startService(it);
        stopService(it);
        super.onStop();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void updateUI(Intent intent) {
        dbFilmes = new DBFilmes(MainActivity.this);
        adaper = new adapter_parse(MainActivity.this, dbFilmes.search(json_model));
        listView.setAdapter(adaper);
        adaper.notifyDataSetChanged();

        Notifications notifications = new Notifications();
        notifications.Notificar(MainActivity.this);

    }

}
