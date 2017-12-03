package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by elyas on 02/12/17.
 */

public class MultiOrganizar extends AppCompatActivity {

    EditText numTimes, numJogadores;
    Button continuar;
    String sTimes, sJogadores;
    int iTimes, iJogadores, totalJogadores;
    boolean sorteio;
    Intent it;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiorganizar);

        it = new Intent(this, MultiCadastrar.class);

        numTimes = (EditText) findViewById(R.id.txtTimes);
        numTimes.setInputType(InputType.TYPE_CLASS_NUMBER);

        numJogadores = (EditText) findViewById(R.id.txtNumero);
        numJogadores.setInputType(InputType.TYPE_CLASS_NUMBER);

        continuar = (Button) findViewById(R.id.btnContinuar);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sTimes = numTimes.getText().toString();
                sJogadores = numJogadores.getText().toString();

                if (TextUtils.isEmpty(sTimes) || TextUtils.isEmpty(sJogadores)) {
                    Toast.makeText(getApplicationContext(), "Nenhum valor inserido!", Toast.LENGTH_SHORT).show();
                } else {
                    iTimes = Integer.parseInt(sTimes);
                    iJogadores = Integer.parseInt(sJogadores);

                    if (iTimes < 3 || iTimes > 6 || iJogadores < 4 || iJogadores > 11) {
                        Toast.makeText(getApplicationContext(), "Valor inválido!", Toast.LENGTH_SHORT).show();
                    } else {
                        totalJogadores = iJogadores * iTimes;

                        AlertDialog.Builder builder = new AlertDialog.Builder(MultiOrganizar.this);
                        builder.setTitle("Como você deseja organizar as equipes?");

                        builder.setPositiveButton("Sorteio", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sorteio = true;
                                it.putExtra("iTimes", iTimes);
                                it.putExtra("iJogadores", iJogadores);
                                it.putExtra("sorteio", sorteio);
                                startActivity(it);
                            }
                        });

                        builder.setNegativeButton("Pré-determinado", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sorteio = false;
                                it.putExtra("iTimes", iTimes);
                                it.putExtra("iJogadores", iJogadores);
                                it.putExtra("sorteio", sorteio);
                                Toast.makeText(getApplicationContext(), "Favor inserir os jogadores por ordem de time.", Toast.LENGTH_LONG).show();
                                startActivity(it);
                            }
                        });

                        builder.show();

                    }
                }
            }
        });
    }


}
