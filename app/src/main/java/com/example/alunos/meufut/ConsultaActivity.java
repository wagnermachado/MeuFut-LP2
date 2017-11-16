package com.example.alunos.meufut;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by alunos on 16/11/17.
 */

public class ConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        BancoController crud = new BancoController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String placar = CriaBanco.PLACARTIME1 + "-" + CriaBanco.PLACARTIME2;
        String [] nomeCampos = new String [] {CriaBanco.IDPARTIDA, placar, CriaBanco.DATA};
        int [] idViews = new int[] {R.id.placar, R.id.data};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.row_layout
                                                                , cursor, nomeCampos, idViews, 0);
        ListView lista = (ListView) findViewById(R.id.ListView);
        lista.setAdapter(adaptador);
    }
}
