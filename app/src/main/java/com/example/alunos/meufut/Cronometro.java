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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        final Chronometer relogio = (Chronometer) findViewById(R.id.relogio);
        Button iniciar = (Button) findViewById(R.id.btnStart);
        Button zerar = (Button) findViewById (R.id.btnBlank);
        Button parar = (Button) findViewById(R.id.btnStop);
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

    }


}
