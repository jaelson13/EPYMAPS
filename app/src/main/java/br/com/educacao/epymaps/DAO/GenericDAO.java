package br.com.educacao.epymaps.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jaelson on 25/04/2017.
 */

public abstract class GenericDAO<T> extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "epymaps";
    private static final int VERSAO_BANCO = 1;
    private String sqlCreateUsuario = "CREATE TABLE IF NOT EXISTS usuario(" +
            "idCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(45) NOT NULL," +
            "sobrenome VARCHAR(45) NOT NULL," +
            "dataNascimento DATE NOT NULL," +
            "telefone VARCHAR(45) NOT NULL,"+
            "email VARCHAR(60) NOT NULL UNIQUE," +
            "status BOOLEAN," +
            "administrador BOOLEAN DEFAULT FALSE," +
            "cidade VARCHAR(45) NOT NULL," +
            "estado VARCAHR(45) NOT NULL" +

            ");";

    public GenericDAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateUsuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public abstract boolean salvar(T t);
}

