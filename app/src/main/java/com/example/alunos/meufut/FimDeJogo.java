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
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;
import java.util.ArrayList;

/**
 * Created by alunos on 08/11/17.
 */

public class FimDeJogo extends AppCompatActivity {

    EditText nomes1, nomes2;
    int gol1, gol2;
    Intent it, itM;
    Button gols1B, gols2B, sim, nao;
    TextView time1T, time2T;
    String time1List;
    String time2List;
    String nome1;
    String nome2;
    Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        time1T = (TextView) findViewById(R.id.txtTime1);
        time1T.setTypeface(typeface);
        time2T = (TextView) findViewById(R.id.txtTime2);
        time2T.setTypeface(typeface);
        nomes1 = new EditText(FimDeJogo.this);
        nomes1.setTypeface(typeface);
        nomes2 = new EditText(FimDeJogo.this);
        nomes2.setTypeface(typeface);
        it = new Intent(this, OrganizarTimes.class);
        itM = new Intent(this, MainActivity.class);

        gols1B = (Button) findViewById(R.id.btnTime1);
        gols1B.setTypeface(typeface);
        gols2B = (Button) findViewById(R.id.btnTime2);
        gols2B.setTypeface(typeface);
        sim = (Button) findViewById(R.id.btnSim);
        sim.setTypeface(typeface);
        nao = (Button) findViewById(R.id.btnNao);
        nao.setTypeface(typeface);

        gol1 = getIntent().getIntExtra("gols1", 0);
        gol1.setTypeface(typeface);
        gol2 = getIntent().getIntExtra("gols2", 0);
        gol2.setTypeface(typeface);

        gols1B.setText(String.valueOf(gol1));
        gols1B.setTypeface(typeface);
        gols2B.setText(String.valueOf(gol2));
        gols2B.setTypeface(typeface);

        nome1 = getIntent().getStringExtra("nome1");
        nome1.setTypeface(typeface);
        nome2 = getIntent().getStringExtra("nome2");
        nome2.setTypeface(typeface);


        final ArrayList<Pessoa> time1 = getIntent().getParcelableArrayListExtra("time1");
        time1.setTypeface(typeface);
        final ArrayList<Pessoa> time2 = getIntent().getParcelableArrayListExtra("time2");
        time2.setTypeface(typeface);
        final ArrayList<Pessoa> fora = getIntent().getParcelableArrayListExtra("fora");
        fora.setTypeface(typeface);

        final ArrayList<Pessoa> lista = new ArrayList<>();

        if (time1 != null) {
            for (int i = 0; i < time1.size(); i++) {
                if (i == 0) {
                    pessoa = time1.get(i);
                    time1List = pessoa.getNome();
                } else {
                    pessoa = time1.get(i);
                    time1List = time1List + ", " + pessoa.getNome();
                }
            }

            for (int i = 0; i < time2.size(); i++) {
                if (i == 0) {
                    pessoa = time2.get(i);
                    time2List = pessoa.getNome();
                } else {
                    pessoa = time2.get(i);
                    time2List = time2List + ", " + pessoa.getNome();
                }
            }

            time1T.setText(nome1 + ": " + time1List);
            time2T.setText(nome2 + ": " + time2List);

        } else {
            time1T.setText(nome1);
            time2T.setText(nome2);
        }

        time1T.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                if (time1List == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FimDeJogo.this);
                    builder.setTitle("Cadastre a equipe " + nome1);
                    builder.setView(nomes1);

                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            time1List = nomes1.getText().toString();

                            if (TextUtils.isEmpty(time1List)) {

                            } else {
                                time1T.setText(nome1 + ": " + time1List);
                            }
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }
        });

        time2T.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (time2List == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FimDeJogo.this);
                    builder.setTitle("Cadastre a equipe " + nome2);
                    builder.setView(nomes2);

                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            time2List = nomes2.getText().toString();

                            if (TextUtils.isEmpty(time2List)) {
                            } else {
                                time2T.setText(nome2 + ": " + time2List);
                            }
                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }
        });

        sim.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                String resultado;

                resultado = crud.insereDado(gol1, gol2, time1List, time2List, nome1, nome2);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                if (time1 != null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(FimDeJogo.this);
                    builder.setTitle("Deseja manter os mesmos jogadores?");

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i = 0; i < time1.size(); i++) {
                                lista.add(time1.get(i));
                            }
                            for (int i = 0; i < time2.size(); i++) {
                                lista.add(time2.get(i));
                            }
                            for (int i = 0; i < fora.size(); i++) {
                                lista.add(fora.get(i));
                            }

                            it.putParcelableArrayListExtra("lista", lista);
                            startActivity(it);

                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(itM);
                        }
                    });

                    builder.show();

                } else {
                    startActivity(itM);
                }
            }
        });

        nao.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                if (time1 != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FimDeJogo.this);
                    builder.setTitle("Deseja manter os mesmos jogadores?");

                    builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for (int i = 0; i < time1.size(); i++) {
                                lista.add(time1.get(i));
                            }
                            for (int i = 0; i < time2.size(); i++) {
                                lista.add(time2.get(i));
                            }
                            for (int i = 0; i < fora.size(); i++) {
                                lista.add(fora.get(i));
                            }

                            it.putParcelableArrayListExtra("lista", lista);
                            startActivity(it);

                        }
                    });
                    builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(itM);
                        }
                    });

                    builder.show();
                } else {
                    startActivity(itM);
                }
            }
        });
    }


}

