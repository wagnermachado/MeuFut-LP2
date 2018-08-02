package br.com.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.meufut.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alunos on 05/10/17.
 */

public class OrganizarTimes extends AppCompatActivity {


    EditText numero1;
    ArrayList<Pessoa> time1 = new ArrayList<>();
    ArrayList<Pessoa> time2 = new ArrayList<>();
    ArrayList<Pessoa> fora = new ArrayList<>();
    ArrayList<Pessoa> teste = new ArrayList<>();

    int forca1, forca2;

    Button organizar;
    Button continuar;
    Button bLista;
    Intent it, itC;
    Random rand = new Random();
    TextView home, away, out, jpt;

    AdView mAdView;
    Pessoa p;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizar);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        jpt = (TextView) findViewById(R.id.txt1);
        jpt.setTypeface(typeface);


        forca1 = 0;
        forca2 = 0;

        int orient=this.getResources().getConfiguration().orientation;

        if (orient == 1) {
        } else {
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        final ArrayList<Pessoa>  lista = getIntent().getParcelableArrayListExtra("lista");

        numero1 = (EditText) findViewById(R.id.numJogadores);
        numero1.setTypeface(typeface);

        organizar = (Button) findViewById(R.id.btnOrganizar);
        continuar = (Button) findViewById(R.id.btnComecar);
        bLista = (Button) findViewById(R.id.btnLista);
        bLista.setTypeface(typeface);
        continuar.setTypeface(typeface);
        organizar.setTypeface(typeface);

        it = new Intent(this, DivisaoTimes.class);
        itC = new Intent(this, Cronometro.class);

        numero1.setInputType(InputType.TYPE_CLASS_NUMBER);


        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itC.putParcelableArrayListExtra("time1", time1);
                itC.putParcelableArrayListExtra("time2", time2);
                itC.putParcelableArrayListExtra("fora", fora);
                startActivity(itC);
            }
        });


        organizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do {
                    time1.clear();
                    time2.clear();
                    fora.clear();
                    forca1 = 0;
                    forca2 = 0;


                    for (int i = 0; i < lista.size(); i++) {
                        teste.add(lista.get(i));
                    }

                    String numero = numero1.getText().toString();
                    boolean numjog = true;
                    int jogadores = 0;

                    if (TextUtils.isEmpty(numero)) {
                        Toast.makeText(getApplicationContext(), "Nenhum valor foi inserido!", Toast.LENGTH_SHORT).show();
                        numjog = false;
                        teste.clear();
                    } else {
                        jogadores = Integer.parseInt(numero);
                        if ((jogadores < 4) || (jogadores > 11)) {
                            numjog = false;
                            Toast.makeText(getApplicationContext(), "Valor inválido!", Toast.LENGTH_SHORT).show();
                            teste.clear();
                        }
                    }


                    if (numjog) {
                        if (lista.size() / jogadores < 2) {
                            while (teste.size() != 0) {
                                int n = rand.nextInt(teste.size());
                                time1.add(teste.get(n));
                                forca1 += teste.get(n).getRating();
                                teste.remove(n);
                                if (teste.size() != 0) {
                                    n = rand.nextInt(teste.size());
                                    time2.add(teste.get(n));
                                    forca2 += teste.get(n).getRating();
                                    teste.remove(n);
                                }
                            }
                        }
                        if (lista.size() / jogadores == 2) {
                            if (lista.size() % jogadores == 0) {
                                while (teste.size() != 0) {
                                    int n = rand.nextInt(teste.size());
                                    time1.add(teste.get(n));
                                    forca1 += teste.get(n).getRating();
                                    teste.remove(n);

                                    n = rand.nextInt(teste.size());
                                    time2.add(teste.get(n));
                                    forca2 += teste.get(n).getRating();
                                    teste.remove(n);
                                }
                            }
                            if (lista.size() % jogadores != 0) {
                                for (int i = 0; i < jogadores; i++) {
                                    int n = rand.nextInt(teste.size());
                                    time1.add(teste.get(n));
                                    forca1 += teste.get(n).getRating();
                                    teste.remove(n);

                                    n = rand.nextInt(teste.size());
                                    time2.add(teste.get(n));
                                    forca2 += teste.get(n).getRating();
                                    teste.remove(n);
                                }

                                while (teste.size() != 0) {
                                    int n = rand.nextInt(teste.size());
                                    fora.add(teste.get(n));
                                    teste.remove(n);
                                }
                            }
                        }

                    }

                    Toast.makeText(getApplicationContext(), String.valueOf(forca1) + ", " + String.valueOf(forca2),
                            Toast.LENGTH_SHORT).show();
                    bLista.setVisibility(View.VISIBLE);

                } while (Math.abs(forca1-forca2) > 3);
            }

        });



        bLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pessoa pessoa = null;
                String time1List = null;
                String time2List = null;
                String foraList = "";
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

                for (int i = 0; i < fora.size(); i++) {
                    if (i == 0) {
                        pessoa = fora.get(i);
                        foraList = pessoa.getNome();
                    } else {
                        pessoa = fora.get(i);
                        foraList = foraList + ", " + pessoa.getNome();
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(OrganizarTimes.this);
                builder.setMessage("Time 1 (" + String.valueOf(forca1) + "*) : " + time1List + "\n\n" + "Time 2 (" + String.valueOf(forca2) + "*) : " + time2List + "\n\n" + "Fora: " + foraList);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    protected  void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("time1", time1);
        outState.putParcelableArrayList("time2", time2);
        outState.putParcelableArrayList("fora", fora);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        time1 = savedInstanceState.getParcelableArrayList("time1");
        time2 = savedInstanceState.getParcelableArrayList("time2");
        fora = savedInstanceState.getParcelableArrayList("fora");

    }

}

