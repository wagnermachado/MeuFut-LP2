package com.example.alunos.meufut;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alunos on 05/10/17.
 */

public class OrganizarTimes extends AppCompatActivity {


    EditText numero1, ejpt;
    ArrayList<Pessoa> time1 = new ArrayList<>();
    ArrayList<Pessoa> time2 = new ArrayList<>();
    ArrayList<Pessoa> fora = new ArrayList<>();

    Button organizar;
    Button continuar;
    Intent it, itC;
    Random rand = new Random();
    TextView home, away, out, jpt;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        ejpt = (EditText) findViewById(R.id.numJogadores);
        jpt = (TextView) findViewById(R.id.txt1);
        ejpt.setTypeface(typeface);
        jpt.setTypeface(typeface);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizar);

        final ArrayList<Pessoa>  lista = getIntent().getParcelableArrayListExtra("lista");

        numero1 = (EditText) findViewById(R.id.numJogadores);

        organizar = (Button) findViewById(R.id.btnOrganizar);
        continuar = (Button) findViewById(R.id.btnComecar);
        continuar.setTypeface(typeface);
        organizar.setTypeface(typeface);

        it = new Intent(this, DivisaoTimes.class);
        itC = new Intent(this, Cronometro.class);

        home = (TextView) findViewById(R.id.txtTime1);
        away = (TextView) findViewById(R.id.txtTime2);
        out = (TextView) findViewById(R.id.txtFora);

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
                time1.clear();
                time2.clear();
                fora.clear();

                ArrayList<Pessoa> teste = getIntent().getParcelableArrayListExtra("lista");
                String numero = numero1.getText().toString();
                boolean numjog = true;
                Pessoa pessoa;
                int jogadores = Integer.parseInt(numero);
                if (TextUtils.isEmpty(numero) || (jogadores < 4) || (jogadores > 11)) {
                    numjog = false;
                }

                if (numjog) {
                    if (lista.size() / jogadores < 2) {
                        while (teste.size() != 0) {
                            int n = rand.nextInt(teste.size());
                            time1.add(teste.get(n));
                            teste.remove(n);
                            if (teste.size() != 0) {
                                n = rand.nextInt(teste.size());
                                time2.add(teste.get(n));
                                teste.remove(n);
                            }
                        }
                    }
                    if (lista.size() / jogadores == 2) {
                        if (lista.size() % jogadores == 0) {
                           while (teste.size() != 0) {
                               int n = rand.nextInt(teste.size());
                               time1.add(teste.get(n));
                               teste.remove(n);

                               n = rand.nextInt(teste.size());
                               time2.add(teste.get(n));
                               teste.remove(n);
                           }
                        }
                        if (lista.size() % jogadores != 0) {
                            for (int i = 0; i < jogadores; i++) {
                                int n = rand.nextInt(teste.size());
                                time1.add(teste.get(n));
                                teste.remove(n);

                                n = rand.nextInt(teste.size());
                                time2.add(teste.get(n));
                                teste.remove(n);
                            }

                            while (lista.size() != 0) {
                                int n = rand.nextInt(teste.size());
                                fora.add(teste.get(n));
                                teste.remove(n);
                            }
                        }
                    }

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
                    home.setText("Time 1: " + time1List);
                    away.setText("Time 2: " + time2List);
                    out.setText("Fora: " + foraList);
                } else {
                    Toast.makeText(getApplicationContext(), "O número inserido é inválido!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}

