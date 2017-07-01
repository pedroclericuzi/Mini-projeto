package com.example.pedroclericuzi.exercicioandroid.helpers;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pedroclericuzi on 26/05/2017.
 */

public class BaixarFilme {

    public String ListaFilmes(String url) throws IOException {
        String file = "";
        InputStream in = null;
        try {
            //Seta o valor da URL
            URL urlFile = new URL(url);
            //Estabelece a conexão com a url
            HttpURLConnection conn = (HttpURLConnection) urlFile.openConnection();
            conn.setRequestMethod("GET"); //Método de conexão
            conn.setDoInput(true); //Vai ter entrada? Neste caso sim
            conn.setDoOutput(false); // Vai ter saida? Neste caso não
            conn.connect(); // Faz a conexão
            //Pega o input da conexão
            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            file = new String(response, "UTF-8");
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return file;
    }
}
