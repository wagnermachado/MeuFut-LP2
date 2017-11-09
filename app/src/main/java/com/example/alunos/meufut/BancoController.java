package com.example.alunos.meufut;

import android.content.ContentValues;
import android.content.Context;
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

    public String insereDado(int gols1, int gols2) {
        ContentValues valores;
        long resultado;

        Calendar cal = Calendar.getInstance();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);
        int ano = cal.get(Calendar.YEAR);
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);
        int segundo = cal.get(Calendar.SECOND);

        //Date data = cal.set(ano + 1900, mes, dia, hora, minuto, segundo);

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.PLACARTIME1, gols1);
        valores.put(CriaBanco.PLACARTIME2, gols2);
        valores.put(CriaBanco.DATA, data);
        resultado = db.insert(CriaBanco.TABELA_PARTIDA, null, valores);
        db.close();

        if (resultado == -1) {
            return "Registrado com sucesso!";
        } else {
            return gols1 + "," + gols2 + "," + data;
        }

    }
}
