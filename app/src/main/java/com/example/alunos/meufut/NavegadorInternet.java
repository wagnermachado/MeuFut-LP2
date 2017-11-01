package com.example.alunos.meufut;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by alunos on 01/11/17.
 */

public class NavegadorInternet extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        final Button button = (Button) findViewById(R.id.btNavegar);
        button.setOnClickListener(this);
    }

    public void onClick(View view) {

        EditText campoEndereco = (EditText) findViewById(R.id.edtCidade);

        String endereco = campoEndereco.getText().toString();

        String url = "https://www.google.com.br/maps/search/quadras+para+alugar+" + endereco + "/";

        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }
}
