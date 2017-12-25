package br.com.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;

import br.com.meufut.R;

import java.util.ArrayList;

/**
 * Created by alunos on 08/11/17.
 */

public class FimDeJogo extends AppCompatActivity {

    EditText nomes1, nomes2;
    int gol1, gol2;
    Intent it, itM;
    Button gols1B, gols2B, sim, nao, bSim, bNao;
    TextView time1T, time2T,salvarP,tT1,tT2;
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
        salvarP = (TextView) findViewById(R.id.salvarPar);
        tT1 = (TextView)  findViewById(R.id.txtTime1);
        tT2 = (TextView)  findViewById(R.id.txtTime2);
        bSim = (Button) findViewById(R.id.btnSim);
        bNao = (Button) findViewById(R.id.btnNao);
        time1T = (TextView) findViewById(R.id.txtTime1);
        time2T = (TextView) findViewById(R.id.txtTime2);
        nomes1 = new EditText(FimDeJogo.this);
        nomes2 = new EditText(FimDeJogo.this);
        it = new Intent(this, Cronometro.class);
        itM = new Intent(this, MainActivity.class);

        gols1B = (Button) findViewById(R.id.btnTime1);
        gols2B = (Button) findViewById(R.id.btnTime2);
        sim = (Button) findViewById(R.id.btnSim);
        nao = (Button) findViewById(R.id.btnNao);

        gol1 = getIntent().getIntExtra("gols1", 0);
        gol2 = getIntent().getIntExtra("gols2", 0);

        gols1B.setText(String.valueOf(gol1));
        gols2B.setText(String.valueOf(gol2));

        nome1 = getIntent().getStringExtra("nome1");
        nome2 = getIntent().getStringExtra("nome2");

        salvarP.setTypeface(typeface);
        tT1.setTypeface(typeface);
        tT2.setTypeface(typeface);
        bSim.setTypeface(typeface);
        bNao.setTypeface(typeface);
        time1T.setTypeface(typeface);
        time2T.setTypeface(typeface);
        nomes1.setTypeface(typeface);
        nomes2.setTypeface(typeface);
        gols1B.setTypeface(typeface);
        gols2B.setTypeface(typeface);
        sim.setTypeface(typeface);
        nao.setTypeface(typeface);
        gols1B.setTypeface(typeface);
        gols2B.setTypeface(typeface);

        final ArrayList<Pessoa> time1 = getIntent().getParcelableArrayListExtra("time1");
        final ArrayList<Pessoa> time2 = getIntent().getParcelableArrayListExtra("time2");
        final ArrayList<Pessoa> fora = getIntent().getParcelableArrayListExtra("fora");

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
            public void onClick(final View v) {

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
                            time1List = "";
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
                            time2List = "";
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
                    builder.setTitle("Deseja voltar para o cronômetro?");

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
                    builder.setTitle("Deseja voltar para o cronômetro?");

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
        });    }


}

