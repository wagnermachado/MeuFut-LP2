package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alunos on 05/10/17.
 */

public class OrganizarTimes extends AppCompatActivity {

    EditText numero1 = (EditText) findViewById(R.id.numJogadores);
    TextView erro = (TextView) findViewById(R.id.txtErro);
    ArrayList<Pessoa> time1 = new ArrayList<>();
    ArrayList<Pessoa> time2 = new ArrayList<>();
    ArrayList<Pessoa> fora = new ArrayList<>();
    Button voltar = (Button) findViewById(R.id.btnVoltar);
    Button organizar = (Button) findViewById(R.id.btnOrganizar);
    Intent it = new Intent(this, DivisaoTimes.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<Pessoa>  lista = getIntent().getParcelableArrayListExtra("lista");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  it.putParcelableArrayListExtra("lista", lista);
               // startActivity(it);
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
                    if (lista.size() / jogadores < 2) {

                    }
                    if (lista.size() / jogadores == 2) {
                        if (lista.size() % jogadores == 0) {

                        }
                        if (lista.size() % jogadores != 0) {

                        }
                    }
                } else {
                    erro.setText("O número inserido está inválido!");
                }
            }

        });
    }

}
