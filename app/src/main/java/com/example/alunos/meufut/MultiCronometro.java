package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.AudioManager;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

;import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.security.AccessController;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by alunos on 21/09/17.
 */

public class MultiCronometro extends AppCompatActivity{


    private long lastPause;
    private AudioManager mAudioManager;
    int volume;

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
    Time t1, t2;

    CountDownTimer limite;

    int gols1;
    int gols2;
    int iTimes;
    long minutos;

    private  InterstitialAd mInterstitialAd;
    boolean crono, contagem;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        int orient=this.getResources().getConfiguration().orientation;

        if (orient == 1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


        Typeface typefaceDigital7 = Typeface.createFromAsset(getAssets(), "fonts/digital-7.ttf");
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        Typeface typefaceMS = Typeface.createFromAsset(getAssets(), "fonts/montserrat_alternates.ttf");

        final ArrayList<Time> listaT = getIntent().getParcelableArrayListExtra("listaT");
        t1 = getIntent().getParcelableExtra("time1");
        t2 = getIntent().getParcelableExtra("time2");


        it = new Intent(this, MultiFimDeJogo.class);

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
        gol2 = (Button) findViewById(R.id.btnGol2);
        cancela1 = (Button) findViewById(R.id.btnCancela1);
        cancela2 = (Button) findViewById(R.id.btnCancela2);
        pla1 = (Button) findViewById(R.id.plaTime1);
        pla2 = (Button) findViewById(R.id.plaTime2);
        fim = (Button) findViewById(R.id.btnFim);
        nomeTime1 = (EditText) findViewById(R.id.insTime1);
        nomeTime2 = (EditText) findViewById(R.id.insTime2);
        nomeTime1.setText(t1.getNome());
        nomeTime2.setText(t2.getNome());
        nomeTime1.setEnabled(false);
        nomeTime2.setEnabled(false);

        gol1.setTypeface(typeface);
        gol2.setTypeface(typeface);
        cancela2.setTypeface(typeface);
        cancela1.setTypeface(typeface);
        pla1.setTypeface(typeface);
        pla2.setTypeface(typeface);
        fim.setTypeface(typeface);
        nomeTime1.setTypeface(typeface);
        nomeTime2.setTypeface(typeface);

        iTimes = getIntent().getIntExtra("iTimes", 0);

        relogio.setTypeface(typefaceDigital7);
        relogio.setBase(SystemClock.elapsedRealtime()); relogio.stop(); lastPause = SystemClock.elapsedRealtime();

        AlertDialog.Builder builder = new AlertDialog.Builder(MultiCronometro.this);
        builder.setTitle("Ajuste o limite de tempo!");

        final EditText inputM = new EditText(MultiCronometro.this);
        inputM.setHint("Minutos...");

        inputM.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(inputM);

        builder.setPositiveButton("Configurar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textoM = inputM.getText().toString();
                if (TextUtils.isEmpty(textoM) || Integer.parseInt(textoM) == 0) {
                    Toast.makeText(getApplicationContext(), "Nenhum valor inserido.", Toast.LENGTH_LONG).show();
                    contagem = false;
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
                        limite = new CountDownTimer(minutos * 60000, minutos * 60000 / 2) {

                            public void onTick(long millisUntilFinished) {
                            }

                            public void onFinish() {
                                Toast.makeText(getApplicationContext(), "Fim do jogo!", Toast.LENGTH_LONG).show();

                                volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);

                                MediaPlayer ring = MediaPlayer.create(MultiCronometro.this, R.raw.apito);
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
                it.putExtra("iTimes", iTimes);

                if (contagem) {

                    limite.cancel();
                    contagem = false;
                    crono = false;

                    relogio.setBase(SystemClock.elapsedRealtime());
                    relogio.stop();
                    lastPause = SystemClock.elapsedRealtime();

                }

                it.putExtra("t1", t1);
                it.putExtra("t2", t2);
                it.putParcelableArrayListExtra("listaT", listaT);
                it.putExtra("gols1", gols1);
                it.putExtra("gols2", gols2);
                startActivity(it);

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "Não carregou");
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (contagem) {
            limite.cancel();
        }
        super.onBackPressed();
    }

}
