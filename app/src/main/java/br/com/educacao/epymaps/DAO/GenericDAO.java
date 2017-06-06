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
    private String sqlCreateEstado = "CREATE TABLE IF NOT EXISTS estado  (" +
            "idEstado INTEGER PRIMARY KEY AUTOINCREMENT," +
            "codigoUf INT NOT NULL," +
            "nome VARCHAR (50) NOT NULL," +
            "uf CHAR (2) NOT NULL," +
            "regiao INT NOT NULL," +
            ");";

    private String sqlCreateMunicipio = "CREATE TABLE IF NOT EXISTS municipio (" +
            "idMunicipio INTEGER PRIMARY KEY  AUTOINCREMENT," +
            "codigo INT NOT NULL," +
            "nome VARCHAR(255) NOT NULL," +
            "uf CHAR(2)  NOT NULL" +
            ");";

    private String sqlCreateUsuario = "CREATE TABLE IF NOT EXISTS usuario(" +
            "idCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome VARCHAR(45) NOT NULL," +
            "email VARCHAR(60) NOT NULL UNIQUE," +
            "sexo CHAR(1) NOT NULL," +
            "cidade VARCHAR(45) NOT NULL," +
            "estado VARCAHR(45) NOT NULL," +
            "status BOOLEAN," +
            "administrador BOOLEAN DEFAULT FALSE" +
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



}

