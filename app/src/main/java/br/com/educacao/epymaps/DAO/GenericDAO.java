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
    private String sqlCreateCliente = "CREATE TABLE IF NOT EXISTS usuario(" +
            "idCliente INT AUTO_INCREMENT," +
            "nome VARCHAR(20) NOT NULL," +
            "sobrenome VARCHAR(45) NOT NULL" +
            "email VARCHAR(60) NOT NULL," +
            "endereco VARCHAR(45) NOT NULL," +
            "cidade VARCHAR(45) NOT NULL," +
            "estado VARCAHR(45) NOT NULL," +
            "dataNascimento DATE NOT NULL" +
            "PRIMARY KEY(idCliente)," +
            " UNIQUE KEY(email)" +
            ");";

    public GenericDAO(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateCliente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public abstract boolean salvar(T t);
}

