package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alunos on 08/11/17.
 */

public class FimDeJogo extends AppCompatActivity {

    int gol1, gol2;
    Intent it;
    Button gols1B, gols2B, sim, nao;
    TextView time1T, time2T;
    String time1List;
    String time2List;
    String nome1;
    String nome2;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        time1T = (TextView) findViewById(R.id.txtTime1);
        time2T = (TextView) findViewById(R.id.txtTime2);

        it = new Intent(this, MainActivity.class);

        gols1B = (Button) findViewById(R.id.btnTime1);
        gols2B = (Button) findViewById(R.id.btnTime2);
        sim = (Button) findViewById(R.id.btnSim);
        nao = (Button) findViewById(R.id.btnNao);

        gol1 = getIntent().getIntExtra("gols1", 0);
        gol2 = getIntent().getIntExtra("gols2", 0);

        gols1B.setText(String.valueOf(gol1));
        gols2B.setText(String.valueOf(gol2));

        nome1 = getIntent().getStringExtra("nome1");
        nome2 = getIntent().getStringExtra("nome2");

        final ArrayList<Pessoa> time1 = getIntent().getParcelableArrayListExtra("time1");
        final ArrayList<Pessoa> time2 = getIntent().getParcelableArrayListExtra("time2");

        if (time1 != null) {

            for (int i = 0; i < time1.size(); i++) {
                if (i == 0) {
                    pessoa = time1.get(i);
                    time1List = pessoa.getNome();
                } else {
                    pessoa = time1.get(i);
                    time1List = time1List + ", " + pessoa.getNome();
                }
            }

            for (int i = 0; i < time2.size(); i++) {
                if (i == 0) {
                    pessoa = time2.get(i);
                    time2List = pessoa.getNome();
                } else {
                    pessoa = time2.get(i);
                    time2List = time2List + ", " + pessoa.getNome();
                }
            }

            time1T.setText(nome1 + ": " + time1List);
            time2T.setText(nome2 + ": " + time2List);

        } else {
            time1T.setText(nome1);
            time2T.setText(nome2);
        }

        sim.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                String resultado;

                resultado = crud.insereDado(gol1, gol2, time1List, time2List);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                startActivity(it);
            }
        });

        nao.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                startActivity(it);
            }
        });
    }


}
