package com.example.alunos.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alunos on 08/11/17.
 */

public class MultiFimDeJogo extends AppCompatActivity {

    int gol1, gol2, iTimes;
    Intent it;
    Button gols1B, gols2B, sim, nao;
    TextView time1T, time2T;
    String time1List;
    String time2List;
    String nome1;
    String nome2;
    ArrayList<Time> listaT;
    Time t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim);

        it = new Intent(this, MultiLista.class);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");

        TextView salvarP = (TextView) findViewById(R.id.salvarPar);
        TextView tT1 = (TextView)  findViewById(R.id.txtTime1);
        TextView tT2 = (TextView)  findViewById(R.id.txtTime2);

        EditText nomes1 = new EditText(MultiFimDeJogo.this);
        EditText nomes2 = new EditText(MultiFimDeJogo.this);

        listaT = getIntent().getParcelableArrayListExtra("listaT");
        t1 = getIntent().getParcelableExtra("t1");
        t2 = getIntent().getParcelableExtra("t2");

        nome1 = t1.getNome();
        time1List = t1.getElenco();

        nome2 = t2.getNome();
        time2List = t2.getElenco();

        iTimes = listaT.size();

        time1T = (TextView) findViewById(R.id.txtTime1);
        time2T = (TextView) findViewById(R.id.txtTime2);

        gols1B = (Button) findViewById(R.id.btnTime1);
        gols2B = (Button) findViewById(R.id.btnTime2);
        sim = (Button) findViewById(R.id.btnSim);
        nao = (Button) findViewById(R.id.btnNao);

        salvarP.setTypeface(typeface);
        tT1.setTypeface(typeface);
        tT2.setTypeface(typeface);
        sim.setTypeface(typeface);
        nao.setTypeface(typeface);
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


        time1T.setText(nome1 + ": " + time1List);
        time2T.setText(nome2 + ": " + time2List);

        time1T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        time2T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        gol1 = getIntent().getIntExtra("gols1", 0);
        gol2 = getIntent().getIntExtra("gols2", 0);

        gols1B.setText(String.valueOf(gol1));
        gols2B.setText(String.valueOf(gol2));

        sim.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                String resultado;

                resultado = crud.insereDado(gol1, gol2, time1List, time2List, nome1, nome2);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

                it.putParcelableArrayListExtra("listaT", listaT);
                it.putExtra("iTimes", listaT.size());
                startActivity(it);

            }
        });

        nao.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                it.putExtra("iTimes", listaT.size());
                it.putParcelableArrayListExtra("listaT", listaT);
                startActivity(it);

            }
        });
    }


}

