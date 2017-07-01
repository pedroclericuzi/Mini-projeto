package com.example.pedroclericuzi.exercicioandroid.service.others;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;

import com.example.pedroclericuzi.exercicioandroid.interfaces.FilmesListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedroclericuzi on 01/06/2017.
 */

public class BindServiceLoading extends Service implements FilmesListner{
    private Binder mBind = new Binder();
    private ListView listView;
    private List<FilmesListner> filmesListners = new ArrayList<FilmesListner>();

    private ServiceBinder serviceBinder = new ServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBind;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Script log", "Entrou");
        //ThreadLivros threadLivros = new ThreadLivros();
        //threadLivros.getThread();
    }

    @Override
    public ListView listaFilmes(ListView lista) {
        return lista;
    }

    public class ServiceBinder extends Binder {
        public FilmesListner getFilmes(){
            return(BindServiceLoading.this);
        }
    }

}
