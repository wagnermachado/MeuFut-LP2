package com.example.alunos.meufut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alunos on 09/11/17.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public String insereDado(int gols1, int gols2, String time1List, String time2List) {
        ContentValues valores;
        long resultado;

        Calendar cal = Calendar.getInstance();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);
        mes++;
        int ano = cal.get(Calendar.YEAR);

        String data = dia + "/" + mes + "/" + ano;

        String placar = String.valueOf(gols1) + "-" + String.valueOf(gols2);

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.PLACAR, placar);
        valores.put(CriaBanco.TIME1, time1List);
        valores.put(CriaBanco.TIME2, time2List);
        valores.put(CriaBanco.DATA, data);
        resultado = db.insert(CriaBanco.TABELA_PARTIDA, null, valores);
        db.close();

        if (resultado == -1) {
            return "Erro no registro!";
        } else {
            return "Registrado com sucesso!";
        }

    }

    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {banco._IDPARTIDA, banco.PLACAR, banco.TIME1, banco.TIME2, banco.DATA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_PARTIDA, campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
