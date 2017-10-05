package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alunos on 05/10/17.
 */

public class DivisaoTimes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
    }

    public ArrayList<Pessoa> lista = new ArrayList<>();

    public void mostraLista() {
        int tam = lista.size();

        TextView text = (TextView) findViewById(R.id.txtLista);
        text.setText(String.valueOf(tam));
    }

    public void cadastrar(View v) {
        EditText nome1 = (EditText) findViewById(R.id.txtNome);
        String nome = nome1.getText().toString();

        lista.add(new Pessoa(nome));

        this.mostraLista();

    }

    public void confirmar(View v) {
        Intent it = new Intent(this, OrganizarTimes.class);
        startActivity(it);
    }



}
