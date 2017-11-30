package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by alunos on 16/11/17.
 */

public class ConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        final BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados();
        final String [] nomeCampos = new String [] {CriaBanco.PLACAR, CriaBanco.DATA};
        int [] idViews = new int[] {R.id.placar, R.id.data};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.row_layout, cursor, nomeCampos, idViews, 0);
        ListView lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Cursor cursorII;
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco._IDPARTIDA));

                cursorII = crud.carregaDadoById(Integer.parseInt(codigo));
                String time1 = cursorII.getString(cursorII.getColumnIndexOrThrow(CriaBanco.TIME1));
                String time2 = cursorII.getString(cursorII.getColumnIndexOrThrow(CriaBanco.TIME2));
                String nome1 = cursorII.getString(cursorII.getColumnIndexOrThrow(CriaBanco.NOMETIME1));
                String nome2 = cursorII.getString(cursorII.getColumnIndexOrThrow(CriaBanco.NOMETIME2));
                String placar = cursorII.getString(cursorII.getColumnIndexOrThrow(CriaBanco.PLACAR));

                AlertDialog alertDialog = new AlertDialog.Builder(ConsultaActivity.this).create();
                alertDialog.setTitle(nome1 + " " + placar + " " + nome2);
                alertDialog.setMessage(time1 + " vs. " + time2);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }

        });
    }
}
