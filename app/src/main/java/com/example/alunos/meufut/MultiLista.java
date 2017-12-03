package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by elyas on 02/12/17.
 */

public class MultiLista extends AppCompatActivity {

    TextView text;
    int iTimes, iTime1, iTime2;
    ArrayList<Time> listaT = new ArrayList<>();
    Time t, t1, t2;
    String nome, elenco, ultima;
    Button iniciar, tela;
    EditText time1, time2;
    Intent it, itV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilista);

        listaT = getIntent().getParcelableArrayListExtra("listaT");

        iTimes = getIntent().getIntExtra("iTimes", 0);

        text = (TextView) findViewById(R.id.txtLista);

        iniciar = (Button) findViewById(R.id.btnIniciar);
        tela = (Button) findViewById(R.id.btnVoltar);

        time1 = (EditText) findViewById(R.id.txtTime1);
        time2 = (EditText) findViewById(R.id.txtTime2);

        it = new Intent(this, MultiCronometro.class);
        itV = new Intent(this, MainActivity.class);

        for (int i = 0; i < listaT.size(); i++) {
            t = listaT.get(i);

            if (i == 0) {
                nome = t.getNome();
                elenco = t.getElenco();
                ultima = nome + ": " + elenco;
            } else {
                nome = t.getNome();
                elenco = t.getElenco();
                ultima = ultima + "\n" + nome + ": " + elenco;
            }

        }

        text.setText(ultima);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iTime1 = Integer.parseInt(time1.getText().toString());
                iTime2 = Integer.parseInt(time2.getText().toString());

                if (iTime1 < 1 || iTime1 > iTimes || iTime2 < 1 || iTime2 > iTimes || TextUtils.isEmpty(time1.getText().toString()) || TextUtils.isEmpty(time2.getText().toString()) || iTime1 == iTime2) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MultiLista.this);
                    builder.setTitle(String.valueOf(iTime1) + " - " + String.valueOf(iTime2) + " - " + String.valueOf(iTimes));

                    builder.setPositiveButton("Sorteio", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    builder.show();


                    Toast.makeText(getApplicationContext(), "Valor inválido", Toast.LENGTH_SHORT).show();
                } else {
                    t1 = listaT.get(--iTime1);
                    t2 = listaT.get(--iTime2);

                    it.putExtra("time1", t1);
                    it.putExtra("time2", t2);
                    it.putExtra("iTimes", iTimes);
                    it.putParcelableArrayListExtra("listaT", listaT);
                    startActivity(it);
                }
            }
        });

        tela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(itV);
            }
        });
    }

}
