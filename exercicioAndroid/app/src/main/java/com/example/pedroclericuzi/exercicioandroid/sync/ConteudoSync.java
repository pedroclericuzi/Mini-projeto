package com.example.pedroclericuzi.exercicioandroid.sync;
import com.example.pedroclericuzi.exercicioandroid.adapter.adapter_parse;
import com.example.pedroclericuzi.exercicioandroid.data.DBFilmes;
import com.example.pedroclericuzi.exercicioandroid.data.DBHelper;
import com.example.pedroclericuzi.exercicioandroid.helpers.*;
import com.example.pedroclericuzi.exercicioandroid.model.modelJSON;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 26/05/2017.
 */

public class ConteudoSync extends AsyncTask<String, Void, String> {
    String conteudo = "";
    BaixarFilme baixarLivro = new BaixarFilme();
    ClassParser classParser = new ClassParser();

    //private ListView listView;
    private final Context context;
    public ConteudoSync(Context context){
        super();
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            conteudo = baixarLivro.ListaFilmes(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            //final DBHelper dbHelper = new DBHelper(context);
            DBFilmes dbFilmes = new DBFilmes(context);
            ArrayList<modelJSON> arrayList = classParser.Parser(s);
            modelJSON model = new modelJSON();
            for (int i=0;i<arrayList.size();i++){
                model.setTitulo(arrayList.get(i).getTitulo());
                model.setData(arrayList.get(i).getData());
                model.setLink(arrayList.get(i).getLink());
                model.setAtualizado("false");
                dbFilmes.insert(model);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
