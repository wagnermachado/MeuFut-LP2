package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alunos.meufut.Pessoa;

import java.util.ArrayList;

/**
 * Created by alunos on 05/10/17.
 */

public class DivisaoTimes extends AppCompatActivity {

    ArrayList<Pessoa> lista = new ArrayList<>();
    Button confirmar, remover, cadastrar, listaB;
    Intent it;
    Pessoa pessoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");


        cadastrar = (Button) findViewById(R.id.btnCadastrar);
        remover = (Button) findViewById(R.id.btnRemover);
        confirmar = (Button) findViewById(R.id.btnContinuar);
        listaB = (Button) findViewById(R.id.btnLista);
        it = new Intent(this, OrganizarTimes.class);


        cadastrar.setTypeface(typeface);
        remover.setTypeface(typeface);
        confirmar.setTypeface(typeface);
        listaB.setTypeface(typeface);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it.putParcelableArrayListExtra("lista", lista);
                startActivity(it);
            }

        });

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

                AlertDialog.Builder builder = new AlertDialog.Builder(DivisaoTimes.this);
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
    }

    public void mostraLista() {
        if (lista.size() != 0) {
            String list = null;

            for (int i = 0; i < lista.size(); i++) {
                if (i == 0) {
                    pessoa = lista.get(i);
                    list = pessoa.getNome();
                } else if (i < 15) {
                    pessoa = lista.get(i);
                    list = list + ", " + pessoa.getNome();
                } else if (i == 15) {
                    list = list + ", ...";
                }
            }
            listaB.setText(String.valueOf(list));
        } else {
            listaB.setText("");
        }
    }

    public void cadastrar(View v) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        EditText nome1 = (EditText) findViewById(R.id.txtNome);
        nome1.setTypeface(typeface);
        String nome = nome1.getText().toString();

        if (nome != "")
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

