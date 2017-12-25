package br.com.meufut;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.meufut.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by alunos on 01/11/17.
 */

public class NavegadorInternet extends Activity implements View.OnClickListener {

    private AdView mAdView;
    TextView digLoc;
    Button proc;
    EditText digit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        digLoc = (TextView) findViewById(R.id.salvarPar);
        proc = (Button) findViewById(R.id.btNavegar);
        digit = (EditText) findViewById(R.id.edtCidade);
        digLoc.setTypeface(typeface);
        proc.setTypeface(typeface);
        digit.setTypeface(typeface);

            mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);



        final Button button = (Button) findViewById(R.id.btNavegar);
        button.setOnClickListener(this);

    }



    public void onClick(View view) {


        EditText campoEndereco = (EditText) findViewById(R.id.edtCidade);
        String endereco = campoEndereco.getText().toString();

        String url = "https://www.google.com.br/maps/search/quadras+para+alugar+em+" + endereco + "/";

        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }
}
