package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

;import java.util.ArrayList;

/**
 * Created by alunos on 21/09/17.
 */

public class Cronometro extends AppCompatActivity{


    private long lastPause;

    Button gol1;
    Button gol2;
    Button cancela1;
    Button cancela2;
    Button pla1;
    Button pla2;
    Button fim;
    Intent it;
    EditText nomeTime1, nomeTime2;

    int gols1;
    int gols2;

    boolean crono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        final ArrayList<Pessoa> time1 = getIntent().getParcelableArrayListExtra("time1");
        final ArrayList<Pessoa> time2 = getIntent().getParcelableArrayListExtra("time2");

        it = new Intent(this, FimDeJogo.class);

        crono = false;

        gols1 = 0;
        gols2 = 0;

        final Chronometer relogio = (Chronometer) findViewById(R.id.relogio);
        Button iniciar = (Button) findViewById(R.id.btnStart);
        Button zerar = (Button) findViewById (R.id.btnBlank);
        Button parar = (Button) findViewById(R.id.btnStop);

        gol1 = (Button) findViewById(R.id.btnGol1);
        gol2 = (Button) findViewById(R.id.btnGol2);
        cancela1 = (Button) findViewById(R.id.btnCancela1);
        cancela2 = (Button) findViewById(R.id.btnCancela2);
        pla1 = (Button) findViewById(R.id.plaTime1);
        pla2 = (Button) findViewById(R.id.plaTime2);
        fim = (Button) findViewById(R.id.btnFim);
        nomeTime1 = (EditText) findViewById(R.id.insTime1);
        nomeTime2 = (EditText) findViewById(R.id.insTime2);
        nomeTime1.setText("Time 1");
        nomeTime2.setText("Time 2");

        relogio.setBase(SystemClock.elapsedRealtime()); relogio.stop(); lastPause = SystemClock.elapsedRealtime();


        iniciar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (!crono) {
                    relogio.start();
                    relogio.setBase(relogio.getBase() + SystemClock.elapsedRealtime() - lastPause);
                    crono = true;
                }
            }
        });

        parar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (crono) {
                    lastPause = SystemClock.elapsedRealtime();
                    relogio.stop();
                    crono = false;
                }
            }
        });

        zerar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) { relogio.setBase(SystemClock.elapsedRealtime()); relogio.stop(); lastPause = SystemClock.elapsedRealtime();
                crono = false;
            }
        });

        gol1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                gols1 += 1;
                pla1.setText(String.valueOf(gols1));
            }
        });

        gol2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                gols2 += 1;
                pla2.setText(String.valueOf(gols2));
            }
        });

        cancela1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (gols1 > 0) {
                    gols1 -= 1;
                    pla1.setText(String.valueOf(gols1));
                }
            }
        });

        cancela2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (gols2 > 0) {
                    gols2 -= 1;
                    pla2.setText(String.valueOf(gols2));
                }
            }
        });

        fim.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                it.putExtra("nome1", nomeTime1.getText().toString());
                it.putExtra("nome2", nomeTime2.getText().toString());
                it.putExtra("time1", time1);
                it.putExtra("time2", time2);
                it.putExtra("gols1", gols1);
                it.putExtra("gols2", gols2);
                startActivity(it);

            }
        });




    }


}
