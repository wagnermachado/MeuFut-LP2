package com.example.alunos.meufut;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alunos on 01/11/17.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "fut.db";
    private static final String TABELA_PARTIDA = "PARTIDA";
    private static final String IDPARTIDA = "_idPARTIDA";
    private static final String PLACARTIME1 = "placarTime1";
    private static final String PLACARTIME2 = "placarTime2";
    private static final String DATA = "data";
    private static final String TABELA_JOGADORES = "JOGADORES";
    private static final String IDJOGADOR = "_idJOGADOR";
    private static final String JOGADOR_IDPARTIDA = "idPARTIDA";
    private static final String NOME = "NOME";
    private static final String TIMEJOGADOR = "TIMEJOGADOR";
    private static final int VERSAO = 1;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA_PARTIDA + "(" 
                        + IDPARTIDA + " integer primary key autoincrement, "
                        + PLACARTIME1 + " integer, "
                        + PLACARTIME2 + " integer, "
                        + DATA + " date);" +
                     "CREATE TABLE " + TABELA_JOGADORES + "("
                        + IDJOGADOR + " integer primary key autoincrement, "
                        + JOGADOR_IDPARTIDA + " integer, "
                        + NOME + " text, "
                        + TIMEJOGADOR + " integer, " +
                        "foreign key(" + JOGADOR_IDPARTIDA + ") references " + TABELA_PARTIDA + "(" + IDPARTIDA + "));";
        sqLiteDatabase.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABELA_PARTIDA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABELA_JOGADORES);
        onCreate(sqLiteDatabase);
    }

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
}
