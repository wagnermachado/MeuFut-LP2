package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by elyas on 02/12/17.
 */

public class MultiCadastrar extends AppCompatActivity {

    ArrayList<Pessoa> lista = new ArrayList<>();
    ArrayList<Pessoa> teste = new ArrayList<>();
    ArrayList<Time> listaT = new ArrayList<>();
    AdView mAdView;
    Intent it;
    Pessoa pessoa;
    int iTimes, iJogadores, totalJogadores, r, time;
    boolean sorteio;
    Button continuar, cadastrar, remover, listaB;
    String nome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        final Random rand = new Random();

        it = new Intent(this, MultiLista.class);
        iTimes = getIntent().getIntExtra("iTimes", 0);
        iJogadores = getIntent().getIntExtra("iJogadores", 0);
        sorteio = getIntent().getBooleanExtra("sorteio", true);

        totalJogadores = iTimes * iJogadores;

        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        cadastrar.setTypeface(typeface);
        continuar = (Button) findViewById(R.id.btnContinuar);
        continuar.setTypeface(typeface);
        remover = (Button) findViewById(R.id.btnRemover);
        remover.setTypeface(typeface);

        listaB = (Button) findViewById(R.id.btnLista);
        listaB.setTypeface(typeface);

        int orient=this.getResources().getConfiguration().orientation;

        if (orient == 1) {
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        if (sorteio == true)
            continuar.setText("Sortear Equipes");

        listaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String list = null;

                for (int i = 0; i < lista.size(); i++) {
                    if (i == 0) {
                        pessoa = lista.get(i);
                        list = pessoa.getNome();
                    } else {
                        pessoa = lista.get(i);
                        list = list + ", " + pessoa.getNome();
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MultiCadastrar.this);
                builder.setTitle(lista.size() + " jogadores cadastrados");
                builder.setMessage(list);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();


            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lista.size() < totalJogadores) {
                    Toast.makeText(getApplicationContext(), "Número inválido de jogadores!", Toast.LENGTH_SHORT).show();
                } else {
                    listaT.clear();

                    teste = lista;
                    if (sorteio == true) {
                        String elenco = null;
                        for (int i = 0; i < iTimes; i++) {
                            for (int j = 0; j < iJogadores; j++) {
                                r = rand.nextInt(teste.size());
                                pessoa = teste.get(r);
                                if (j == 0) {
                                    elenco = pessoa.getNome();
                                }else{
                                    elenco = elenco + ", " + pessoa.getNome();
                                }

                                teste.remove(r);
                            }
                            time = 1 + i;
                            nome = "Time " + String.valueOf(time);
                            elenco = String.valueOf(elenco);
                            listaT.add(new Time(nome, elenco));
                            elenco = "";
                        }
                        if (teste.size() != 0) {
                            int cont = 0;
                            while (teste.size() != 0) {
                                pessoa = teste.get(0);
                                if (cont == 0) {
                                    elenco = pessoa.getNome();
                                }else{
                                    elenco = elenco + ", " + pessoa.getNome();
                                }

                                teste.remove(0);
                                cont++;
                            }
                            nome = "Fora";
                            elenco = String.valueOf(elenco);
                            listaT.add(new Time(nome, elenco));
                        }
                    } else {
                        r = 0;
                        String elenco = null;
                        for (int i = 0; i < iTimes; i++) {
                            for (int j = 0; j < iJogadores; j++) {
                                pessoa = teste.get(r);
                                if (j == 0) {
                                    elenco = pessoa.getNome();
                                }else{
                                    elenco = elenco + ", " + pessoa.getNome();
                                }
                                teste.remove(r);
                            }
                            time = 1 + i;
                            nome = "Time " + String.valueOf(time);
                            listaT.add(new Time(nome, String.valueOf(elenco)));
                            elenco = "";
                        }
                    }

                    it.putExtra("iTimes", iTimes);
                    it.putExtra("iJogadores", iJogadores);
                    it.putExtra("totalJogadores", totalJogadores);
                    it.putParcelableArrayListExtra("listaT", listaT);
                    it.putParcelableArrayListExtra("lista", lista);
                    startActivity(it);
                }
            }
        });


    }


    public void mostraLista() {
        if (lista.size() != 0) {
            String list = null;

            for (int i = 0; i < lista.size(); i++) {
                if (i == 0) {
                    pessoa = lista.get(i);
                    list = pessoa.getNome();
                } else {
                    pessoa = lista.get(i);
                    list = list + ", " + pessoa.getNome();
                }
            }
            listaB.setText(String.valueOf(list));
        } else {
            listaB.setText("");
        }
    }

    public void cadastrar(View v) {
        EditText nome1 = (EditText) findViewById(R.id.txtNome);
        String nome = nome1.getText().toString();

        if (!TextUtils.isEmpty(nome))
            lista.add(new Pessoa(nome));

        nome1.setText("");

        this.mostraLista();

    }

    public void remover(View v) {
        if (lista.size() != 0) {
            lista.remove(lista.size() - 1);
        } else {
            Toast.makeText(getApplicationContext(), "Não há jogadores cadastrados!", Toast.LENGTH_SHORT).show();
        }

        this.mostraLista();

    }

    @Override
    protected  void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("lista", lista);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        lista = savedInstanceState.getParcelableArrayList("lista");
        this.mostraLista();
    }


}
