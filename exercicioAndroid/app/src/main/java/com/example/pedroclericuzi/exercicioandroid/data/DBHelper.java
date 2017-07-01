package com.example.pedroclericuzi.exercicioandroid.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pedroclericuzi on 31/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String BANCO = "BANCO";
    public static final int VERSAO = 1;

    public static final String TABELA = "LIVROS";
    public static final String _ID = "_ID";
    public static final String TITULO = "TITULO";
    public static final String ANO = "ANO";
    public static final String URL = "URL";
    public static final String ATUALIZADO = "ATUALIZADO";

    public DBHelper(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String oncreate = String.format("CREATE TABLE %s(" +
                                                    "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    "%s TEXT NOT NULL," +
                                                    "%s TEXT NOT NULL," +
                                                    "%s TEXT NOT NULL," +
                                                    "%s TEXT)",TABELA, _ID, TITULO, ANO, URL, ATUALIZADO);
        db.execSQL(oncreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String droptable = "DROP TABLE "+TABELA+";";
        db.execSQL(droptable);
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public SQLiteDatabase open() {
        return super.getWritableDatabase();
    }
}
