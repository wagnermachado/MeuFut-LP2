package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alunos on 08/11/17.
 */

public class FimDeJogo extends AppCompatActivity {

    ArrayList<Pessoa> time1 = new ArrayList<>();
    ArrayList<Pessoa> time2 = new ArrayList<>();
    int gol1, gol2;
    Intent it;
    Button gols1B, gols2B;
    TextView time1T, time2T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        gols1B = (Button) findViewById(R.id.btnTime1);
        gols2B = (Button) findViewById(R.id.btnTime2);

        gol1 = getIntent().getIntExtra("gols1", 0);
        gol2 = getIntent().getIntExtra("gols2", 0);

        gols1B.setText(String.valueOf(gol1));
        gols2B.setText(String.valueOf(gol2));


    }


}
