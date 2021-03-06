package br.com.meufut;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.meufut.R;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    Intent itD, itM;
    Button a, b, c, d, e;
    Spinner infoSpinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itD = new Intent(this, DivisaoTimes.class);
        itM = new Intent(this, MultiOrganizar.class);

        //MobileAds.initialize(this, "ca-app-pub-1594606495855009~2242608510");

        a = (Button) findViewById(R.id.imageButton4);
        b = (Button) findViewById(R.id.imageButton5);
        c = (Button) findViewById(R.id.imageButton6);
        d = (Button) findViewById(R.id.imageButton7);
        e = (Button) findViewById(R.id.button3);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        a.setTypeface(typeface);
        b.setTypeface(typeface);
        c.setTypeface(typeface);
        d.setTypeface(typeface);
        e.setTypeface(typeface);

        infoSpinner = (Spinner) findViewById(R.id.infoSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        infoSpinner.setAdapter(adapter);

        infoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                ((TextView)view).setText(null);

                if (pos == 1) {

                    String url = "https://play.google.com/store/apps/details?id=br.com.meufut";
                    Uri uri = Uri.parse(url);

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }

                if (pos == 2) {

                    String url = "https://www.facebook.com/WAVE-Development-519975971719289/";
                    Uri uri = Uri.parse(url);

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }

                if (pos == 3) {

                    String url = "https://play.google.com/store/apps/developer?id=WAVE+Development";
                    Uri uri = Uri.parse(url);

                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void cronometro(View v) {
        Intent it = new Intent(this, Cronometro.class);
        startActivity(it);
    }

    public void divisaoTimes(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Qual é o tipo de divisão que você deseja?");

        builder.setPositiveButton("Multi-times", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(itM);
            }
        });
        builder.setNegativeButton("Dois times", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(itD);
            }
        });

        builder.show();
    }

    public void divisaoDinheiro(View v) {
        Intent it = new Intent(this, DivisaoDinheiro.class);
        startActivity(it);
    }

    public void localizarQuadra(View v) {
        Intent it = new Intent(this, NavegadorInternet.class);
        startActivity(it);
    }

    public void historico(View v) {
        Intent it = new Intent(this, ConsultaActivity.class);
        startActivity(it);
    }

}

