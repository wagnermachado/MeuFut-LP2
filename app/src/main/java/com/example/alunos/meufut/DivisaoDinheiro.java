package com.example.alunos.meufut;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by alunos on 28/09/17.
 */

public class DivisaoDinheiro extends AppCompatActivity {

    Double result;
    TextView txt, nJ, vA;
    Typeface typeface;
    Button a;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //editFont
        typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisaotime);
        txt = (TextView) findViewById(R.id.textTotal);
        txt.setTypeface(typeface);
        a = (Button) findViewById(R.id.button4);
        nJ = (TextView) findViewById(R.id.numJog);
        nJ.setTypeface(typeface);
        vA = (TextView) findViewById(R.id.valAlug);
        vA.setTypeface(typeface);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        a.setTypeface(typeface);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    public void divd(View v){
        //editFont
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        EditText numjog = (EditText) findViewById(R.id.editNumjog);
        EditText valalug = (EditText) findViewById(R.id.editValalug);
        numjog.setTypeface(typeface);
        valalug.setTypeface(typeface);
        String nome1 = numjog.getText().toString();
        String nome2 = valalug.getText().toString();

        if (TextUtils.isEmpty(nome1) || TextUtils.isEmpty(nome2)) {
            Toast.makeText(getApplicationContext(), "Valor inválido!", Toast.LENGTH_SHORT).show();
        } else {
            double v1 = Double.parseDouble(nome1);
            double v2 = Double.parseDouble(nome2);
            result = v2 / v1;
            String resultt = String.valueOf(result);
            txt.setText(resultt);
        }
        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("result", result);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //editFonte
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        result = savedInstanceState.getDouble("result");
        txt.setText(String.valueOf(result));
    }
}
