package br.com.meufut;

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

import br.com.meufut.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

/**
 * Created by elyas on 02/12/17.
 */

public class MultiLista extends AppCompatActivity {

    TextView tt1, tt2, vs, visu;
    EditText vt1,vt2;
    int iTimes, iTime1, iTime2;
    ArrayList<Time> listaT = new ArrayList<>();
    Time t, t1, t2;
    String nome, elenco, ultima;
    Button iniciar, tela, ini, com, bLista;
    EditText time1, time2;
    Intent it, itV, itC;
    AdView mAdView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");

        setContentView(R.layout.activity_multilista);

        tt1 = (TextView) findViewById(R.id.txtvTime1);
        tt2 = (TextView) findViewById(R.id.txtvTime2);
        vs = (TextView) findViewById(R.id.txtvTime);
        vt1 = (EditText) findViewById(R.id.txtTime1);
        vt2 = (EditText) findViewById(R.id.txtTime2);
        ini = (Button) findViewById(R.id.btnIniciar);
        com = (Button) findViewById(R.id.btnVoltar);
        tt1.setTypeface(typeface);
        tt2.setTypeface(typeface);
        vs.setTypeface(typeface);
        vt1.setTypeface(typeface);
        vt2.setTypeface(typeface);
        ini.setTypeface(typeface);
        com.setTypeface(typeface);

        int orient=this.getResources().getConfiguration().orientation;

        if (orient == 1) {
        } else {
            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        final ArrayList<Pessoa> lista = getIntent().getParcelableArrayListExtra("lista");

        listaT = getIntent().getParcelableArrayListExtra("listaT");

        bLista = (Button) findViewById(R.id.btnLista);
        bLista.setTypeface(typeface);

        itC = new Intent(this, MultiCadastrar.class);
        itC.putParcelableArrayListExtra("lista", lista);

        iTimes = getIntent().getIntExtra("iTimes", 0);

        iniciar = (Button) findViewById(R.id.btnIniciar);
        tela = (Button) findViewById(R.id.btnVoltar);

        time1 = (EditText) findViewById(R.id.txtTime1);
        time2 = (EditText) findViewById(R.id.txtTime2);


        time1.setInputType(InputType.TYPE_CLASS_NUMBER);
        time2.setInputType(InputType.TYPE_CLASS_NUMBER);

        it = new Intent(this, MultiCronometro.class);
        itV = new Intent(this, MainActivity.class);

        for (int i = 0; i < listaT.size(); i++) {
            t = listaT.get(i);

            if (i == 0) {
                nome = t.getNome();
                elenco = t.getElenco();
                ultima = nome + ": " + elenco;
            } else {
                nome = t.getNome();
                elenco = t.getElenco();
                ultima = ultima + "\n" + nome + ": " + elenco;
            }

        }

        bLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MultiLista.this);
                builder.setMessage(ultima);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();

            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iTime1 = Integer.parseInt(time1.getText().toString());
                iTime2 = Integer.parseInt(time2.getText().toString());

                if (iTime1 < 1 || iTime1 > iTimes || iTime2 < 1 || iTime2 > iTimes || TextUtils.isEmpty(time1.getText().toString()) || TextUtils.isEmpty(time2.getText().toString()) || iTime1 == iTime2) {
                    Toast.makeText(getApplicationContext(), "Valor inválido", Toast.LENGTH_SHORT).show();
                } else {
                    t1 = listaT.get(--iTime1);
                    t2 = listaT.get(--iTime2);

                    it.putExtra("time1", t1);
                    it.putExtra("time2", t2);
                    it.putExtra("iTimes", iTimes);
                    it.putParcelableArrayListExtra("listaT", listaT);
                    startActivity(it);
                }
            }
        });

        tela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(itV);
            }
        });
    }

}
