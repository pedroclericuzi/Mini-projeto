package com.example.pedroclericuzi.exercicioandroid.helpers;
import com.example.pedroclericuzi.exercicioandroid.model.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pedroclericuzi on 26/05/2017.
 */

public class ClassParser {

    public ArrayList<modelJSON> Parser(String s) throws JSONException {
        ArrayList<modelJSON> parser = new ArrayList<modelJSON>();
        JSONObject jo = new JSONObject(s);
        JSONArray filmes = jo.getJSONArray("filmes");
        //https://www.tutorialspoint.com/android/android_json_parser.htm
        for (int i = 0; i<filmes.length();i++){
            JSONObject objArr = filmes.getJSONObject(i);
            String titulo = objArr.getString("nome");
            String ano = objArr.getString("ano");
            String link = objArr.getString("link");
            parser.add(new modelJSON(titulo, ano, link));
        }
        return parser;
    }

}
