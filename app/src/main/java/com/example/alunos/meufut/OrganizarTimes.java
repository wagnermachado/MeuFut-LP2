package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alunos on 05/10/17.
 */

public class OrganizarTimes extends AppCompatActivity {

    EditText numero1;
    TextView erro;
    ArrayList<Pessoa> time1 = new ArrayList<>();
    ArrayList<Pessoa> time2 = new ArrayList<>();
    ArrayList<Pessoa> fora = new ArrayList<>();
    Button voltar;
    Button organizar;
    Intent it;
    Random rand = new Random();
    TextView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizar);
        final ArrayList<Pessoa>  lista = getIntent().getParcelableArrayListExtra("lista");

        numero1 = (EditText) findViewById(R.id.numJogadores);
        erro = (TextView) findViewById(R.id.txtErro);
        voltar = (Button) findViewById(R.id.btnVoltar);
        organizar = (Button) findViewById(R.id.btnOrganizar);
        it = new Intent(this, DivisaoTimes.class);
        scroll = (TextView) findViewById(R.id.txtTime);



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it.putParcelableArrayListExtra("lista", lista);
                startActivity(it);
            }

        });

        organizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = numero1.getText().toString();
                boolean numjog = true;
                int jogadores = Integer.parseInt(numero);
                if ((jogadores < 4) || (jogadores > 11)) {
                    numjog = false;
                }

                if (numjog) {
                    erro.setText("");
                    if (lista.size() / jogadores < 2) {
                        while (lista.size() != 0) {
                            int n = rand.nextInt(lista.size());
                            time1.add(lista.get(n));
                            lista.remove(n);
                            if (lista.size() != 0) {
                                n = rand.nextInt(lista.size());
                                time2.add(lista.get(n));
                                lista.remove(n);
                            }
                        }
                    }
                    if (lista.size() / jogadores == 2) {
                        if (lista.size() % jogadores == 0) {
                           while (lista.size() != 0) {
                               int n = rand.nextInt(lista.size());
                               time1.add(lista.get(n));
                               lista.remove(n);

                               n = rand.nextInt(lista.size());
                               time2.add(lista.get(n));
                               lista.remove(n);
                           }
                        }
                        if (lista.size() % jogadores != 0) {
                            for (int i = 0; i < jogadores; i++) {
                                int n = rand.nextInt(lista.size());
                                time1.add(lista.get(n));
                                lista.remove(n);

                                n = rand.nextInt(lista.size());
                                time2.add(lista.get(n));
                                lista.remove(n);
                            }

                            while (lista.size() != 0) {
                                int n = rand.nextInt(lista.size());
                                fora.add(lista.get(n));
                                lista.remove(n);
                            }
                        }
                    }

                    scroll.setText("Time 1: " + time1.size() + " Time 2: " + time2.size() + " Fora: " + fora.size());
                } else {
                    erro.setText("O número inserido está inválido!");
                }
            }

        });

    }

}

