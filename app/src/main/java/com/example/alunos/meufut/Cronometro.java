package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

;import java.security.AccessController;
import java.util.ArrayList;
import java.util.Calendar;

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
    String textoM;

    int gols1;
    int gols2;
    long minutos;

    boolean crono, contagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        int orient=this.getResources().getConfiguration().orientation;

        if (orient == 1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        Typeface typefaceDigital7 = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        Typeface typefaceMS = Typeface.createFromAsset(getAssets(), "fonts/montserrat_alternates.ttf");


        final ArrayList<Pessoa> time1 = getIntent().getParcelableArrayListExtra("time1");
        final ArrayList<Pessoa> time2 = getIntent().getParcelableArrayListExtra("time2");
        final ArrayList<Pessoa> fora = getIntent().getParcelableArrayListExtra("fora");
        
        it = new Intent(this, FimDeJogo.class);

        crono = false;
        contagem = true;

        gols1 = 0;
        gols2 = 0;
        final Chronometer relogio = (Chronometer) findViewById(R.id.relogio);
        Button iniciar = (Button) findViewById(R.id.btnStart);
        Button zerar = (Button) findViewById (R.id.btnBlank);
        Button parar = (Button) findViewById(R.id.btnStop);

        iniciar.setTypeface(typefaceMS);
        zerar.setTypeface(typefaceMS);
        parar.setTypeface(typefaceMS);

        gol1 = (Button) findViewById(R.id.btnGol1);
        gol1.setTypeface(typeface);
        gol2 = (Button) findViewById(R.id.btnGol2);
        gol2.setTypeface(typeface);
        cancela1 = (Button) findViewById(R.id.btnCancela1);
        cancela1.setTypeface(typeface);
        cancela2 = (Button) findViewById(R.id.btnCancela2);
        cancela2.setTypeface(typeface);
        pla1 = (Button) findViewById(R.id.plaTime1);
        pla1.setTypeface(typeface);
        pla2 = (Button) findViewById(R.id.plaTime2);
        pla2.setTypeface(typeface);
        fim = (Button) findViewById(R.id.btnFim);
        fim.setTypeface(typeface);
        nomeTime1 = (EditText) findViewById(R.id.insTime1);
        nomeTime2 = (EditText) findViewById(R.id.insTime2);
        nomeTime1.setText("Time 1");
        nomeTime1.setTypeface(typeface);
        nomeTime2.setText("Time 2");
        nomeTime2.setTypeface(typeface);


        relogio.setTypeface(typefaceDigital7);
        relogio.setBase(SystemClock.elapsedRealtime()); relogio.stop(); lastPause = SystemClock.elapsedRealtime();

        AlertDialog.Builder builder = new AlertDialog.Builder(Cronometro.this);
        builder.setTitle("Ajuste o limite de tempo!");

        final EditText inputM = new EditText(Cronometro.this);
        inputM.setHint("Minutos...");

        inputM.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(inputM);

        builder.setPositiveButton("Configurar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textoM = inputM.getText().toString();
                if (TextUtils.isEmpty(textoM) || Integer.parseInt(textoM) == 0) {
                    Toast.makeText(getApplicationContext(), "Nenhum valor inserido.", Toast.LENGTH_LONG).show();
                } else {
                    minutos = Integer.parseInt(textoM);
                    contagem = true;
                }
            }
        });
        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contagem = false;
                dialog.cancel();
            }
        });

        builder.show();


        iniciar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (!crono) {
                    relogio.start();
                    relogio.setBase(relogio.getBase() + SystemClock.elapsedRealtime() - lastPause);
                    crono = true;

                    if (contagem) {
                        new CountDownTimer(minutos * 60000 + 1000, minutos * 60000 / 2) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                Toast.makeText(getApplicationContext(), "Fim do jogo!", Toast.LENGTH_LONG).show();
                                MediaPlayer ring = MediaPlayer.create(Cronometro.this, R.raw.apito);
                                ring.start();

                                lastPause = SystemClock.elapsedRealtime();
                                relogio.stop();
                                crono = false;
                                contagem = false;
                            }
                        }.start();

                    }

                }
            }
        });

        parar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (crono) {
                    if (contagem == false) {
                        lastPause = SystemClock.elapsedRealtime();
                        relogio.stop();
                        crono = false;
                    }
                }
            }
        });

        zerar.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (contagem == false) {
                relogio.setBase(SystemClock.elapsedRealtime());
                relogio.stop(); lastPause = SystemClock.elapsedRealtime();
                crono = false;
                }
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
                it.putExtra("fora", fora);
                it.putExtra("gols1", gols1);
                it.putExtra("gols2", gols2);
                startActivity(it);

            }
        });

    }

}
