package com.example.alunos.meufut;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by alunos on 05/10/17.
 */

public class OrganizarTimes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizar);
    }

    public void voltar(View v) {
        Intent it = new Intent(this, DivisaoTimes.class);
        startActivity(it);
    }

    public void organizar(View v) {


    }

}
