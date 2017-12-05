package com.example.alunos.meufut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by alunos on 28/09/17.
 */

public class DivisaoDinheiro extends AppCompatActivity {

    Double result;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisaotime);
        txt = (TextView) findViewById(R.id.textTotal);
    }
    public void divd(View v){

        EditText numjog = (EditText) findViewById(R.id.editNumjog);
        String nome1 = numjog.getText().toString();


        EditText valalug = (EditText) findViewById(R.id.editValalug);
        String nome2 = valalug.getText().toString();

        double v1 = Double.parseDouble(nome1);
        double v2 = Double.parseDouble(nome2);
        result = v2/v1;
        String resultt = String.valueOf(result);
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

        result = savedInstanceState.getDouble("result");
        txt.setText(String.valueOf(result));
    }
}
