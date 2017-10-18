package com.example.alunos.meufut;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

;
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

    int gols1;
    int gols2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

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


        relogio.setBase(SystemClock.elapsedRealtime());


        iniciar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {   relogio.start();relogio.setBase(relogio.getBase() + SystemClock.elapsedRealtime() - lastPause);

                }
        });

        parar.setOnClickListener(new Button.OnClickListener() {
           public void onClick(View v) {  lastPause = SystemClock.elapsedRealtime();
               relogio.stop();  }
        });

        zerar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) { relogio.setBase(SystemClock.elapsedRealtime()); relogio.stop(); lastPause = SystemClock.elapsedRealtime();
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




    }


}
