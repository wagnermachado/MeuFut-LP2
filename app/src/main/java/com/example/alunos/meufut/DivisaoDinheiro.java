package com.example.alunos.meufut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Typeface;
/**
 * Created by alunos on 28/09/17.
 */

public class DivisaoDinheiro extends AppCompatActivity {

    Double result;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //editFont
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisaotime);
        txt = (TextView) findViewById(R.id.textTotal);
        txt.setTypeface(typeface);
    }
    public void divd(View v){
        //editFont
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        EditText numjog = (EditText) findViewById(R.id.editNumjog);
        String nome1 = numjog.getText().toString();
        numjog.setTypeface(typeface);
        nome1.setTypeface(typeface);
        EditText valalug = (EditText) findViewById(R.id.editValalug);
        String nome2 = valalug.getText().toString();
        valalug.setTypeface(typeface);
        nome2.setTypeface(typeface);
        double v1 = Double.parseDouble(nome1);
        double v2 = Double.parseDouble(nome2);
        result = v2/v1;
        String resultt = String.valueOf(result);
        resultt..setTypeface(typeface);
        txt.setText(resultt);
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
        result.setTypeface(typeface);
        txt.setText(String.valueOf(result));
    }
}
