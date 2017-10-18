package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alunos on 05/10/17.
 */

public class DivisaoTimes extends AppCompatActivity {

    ArrayList<Pessoa> lista = new ArrayList<>();
    Button confirmar;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);

        confirmar = (Button) findViewById(R.id.btnContinuar);
        it = new Intent(this, OrganizarTimes.class);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                it.putParcelableArrayListExtra("lista", lista);
                startActivity(it);
            }

        });
    }

    public void mostraLista() {
        int tam = lista.size();

        TextView text = (TextView) findViewById(R.id.txtLista);
        text.setText(String.valueOf(tam));
    }

    public void cadastrar(View v) {
        EditText nome1 = (EditText) findViewById(R.id.txtNome);
        String nome = nome1.getText().toString();

        lista.add(new Pessoa(nome));

        nome1.setText("");

        this.mostraLista();

    }
}

