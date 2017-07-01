package com.example.pedroclericuzi.exercicioandroid.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.pedroclericuzi.exercicioandroid.data.DBFilmes;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 01/06/2017.
 */

public class ThreadLivros {
    private final String urlJson = "http://androidjsonteste.orgfree.com/filmes.json";//"https://dl.dropboxusercontent.com/s/vv50krexlh2hc39/filmes.json?dl=0";
    //https://raw.githubusercontent.com/PedroClericuzi/ExercicioAndroid/master/filmes.json
    public void getThread(final boolean running, final Context context){
        Thread t = new Thread(){
            int count = 0;
            @Override
            public void run() {
                super.run();
                try {
                    while (running && count == 0){
                        if(count==0){
                            BaixarFilme baixarLivro = new BaixarFilme();
                            ClassParser classParser = new ClassParser();
                            String conteudo = baixarLivro.ListaFilmes(urlJson);
                            DBFilmes dbFilmes = new DBFilmes(context);
                            ArrayList<modelJSON> arrayList = classParser.Parser(conteudo);
                            modelJSON model = new modelJSON();
                            dbFilmes.clearAll();
                            for (int i=0;i<arrayList.size();i++){
                                Log.d("Script log", "COUTN "+arrayList.get(i).getTitulo());
                                model.setTitulo(arrayList.get(i).getTitulo());
                                model.setData(arrayList.get(i).getData());
                                model.setLink(arrayList.get(i).getLink());
                                model.setAtualizado("false");
                                dbFilmes.insert(model);
                            }
                        }
                        count++;
                        Log.d("Script log", "COUTN "+count);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        t.start();
    }
}
