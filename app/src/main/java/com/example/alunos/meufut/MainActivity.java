package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent itD, itM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itD = new Intent(this, DivisaoTimes.class);
        itM = new Intent(this, MultiOrganizar.class);
    }
    public void cronometro(View v) {
        Intent it = new Intent(this, Cronometro.class);
        startActivity(it);
    }

    public void divisaoTimes(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Qual é o tipo de divisão que você deseja?");

        builder.setPositiveButton("Multi-times", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(itM);
            }
        });
        builder.setNegativeButton("Dois times", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(itD);
            }
        });

        builder.show();
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

