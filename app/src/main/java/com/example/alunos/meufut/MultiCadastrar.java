package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by elyas on 02/12/17.
 */

public class MultiCadastrar extends AppCompatActivity {

    ArrayList<Pessoa> lista = new ArrayList<>();
    ArrayList<Pessoa> teste = new ArrayList<>();
    ArrayList<Time> listaT = new ArrayList<>();
    Intent it;
    Pessoa pessoa;
    int iTimes, iJogadores, totalJogadores, r, time;
    boolean sorteio;
    Button continuar, cadastrar, remover;
    TextView text;
    String nome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        final Random rand = new Random();

        it = new Intent(this, MultiLista.class);
        iTimes = getIntent().getIntExtra("iTimes", 0);
        iJogadores = getIntent().getIntExtra("iJogadores", 0);
        sorteio = getIntent().getBooleanExtra("sorteio", true);

        totalJogadores = iTimes * iJogadores;

        text = (TextView) findViewById(R.id.txtLista);

        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        continuar = (Button) findViewById(R.id.btnContinuar);
        remover = (Button) findViewById(R.id.btnRemover);

        if (sorteio == true)
            continuar.setText("Sortear Equipes");

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lista.size() != totalJogadores) {
                    Toast.makeText(getApplicationContext(), "Número inválido de jogadores!", Toast.LENGTH_SHORT).show();
                } else {
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
            text.setText(String.valueOf(list));
        } else {
            text.setText("");
        }
    }

    public void cadastrar(View v) {
        EditText nome1 = (EditText) findViewById(R.id.txtNome);
        String nome = nome1.getText().toString();

        if (TextUtils.isEmpty(nome) || lista.size() < totalJogadores)
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

}
