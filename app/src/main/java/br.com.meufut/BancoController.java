package br.com.meufut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public String insereDado(int gols1, int gols2, String time1List, String time2List, String nome1, String nome2) {
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
        valores.put(CriaBanco.NOMETIME1, nome1);
        valores.put(CriaBanco.NOMETIME2, nome2);

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

    public Cursor carregaDadoById(int id) {

        Cursor cursor;
        String [] campos = {banco._IDPARTIDA, banco.NOMETIME1, banco.NOMETIME2, banco.TIME1, banco.TIME2, banco.PLACAR};
        String where = CriaBanco._IDPARTIDA + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA_PARTIDA, campos, where, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;

    }

    public void apagaRegistro(int id) {
        String where = CriaBanco._IDPARTIDA + " = " + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA_PARTIDA, where, null);
        db.close();
    }
}

