package com.example.alunos.meufut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
