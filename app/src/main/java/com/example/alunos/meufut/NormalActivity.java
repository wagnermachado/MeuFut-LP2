package com.example.alunos.meufut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

    }
    public void cronometro(View v) {
        Intent it = new Intent(this, Cronometro.class);
        startActivity(it);
    }

    public void divisaoTimes(View v) {
        Intent it = new Intent(this, DivisaoTimes.class);
        startActivity(it);
    }

    public void divisaoDinheiro(View v) {
        Intent it = new Intent(this, DivisaoDinheiro.class);
        startActivity(it);
    }

    public void localizarQuadra(View v) {
        Intent it = new Intent(this, NavegadorInternet.class);
        startActivity(it);
    }

    public void historico(View v) {
        Intent it = new Intent(this, ConsultaActivity.class);
        startActivity(it);
    }

}

