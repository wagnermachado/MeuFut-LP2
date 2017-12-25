package br.com.meufut;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alunos on 01/11/17.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "fut.db";
    public static final String TABELA_PARTIDA = "PARTIDA";
    public static final String _IDPARTIDA = "_id";
    public static final String PLACAR = "placar";
    public static final String NOMETIME1 = "nometime1";
    public static final String NOMETIME2 = "nometime2";

    public static final String TIME1 = "time1";
    public static final String TIME2 = "time2";

    public static final String DATA = "data";
    private static final int VERSAO = 1;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABELA_PARTIDA + " ( "
                        + _IDPARTIDA + " integer primary key autoincrement, "
                        + PLACAR + " text, "
                        + NOMETIME1 + " text, "
                        + NOMETIME2 + " text, "
                        + TIME1 + " text, "
                        + TIME2 + " text, "
                        + DATA + " text ) ";
        sqLiteDatabase.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABELA_PARTIDA);
        onCreate(sqLiteDatabase);
    }

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
}

