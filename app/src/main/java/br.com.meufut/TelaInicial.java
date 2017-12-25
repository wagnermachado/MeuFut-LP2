package br.com.meufut;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import br.com.meufut.R;

/**
 * Created by elyas on 04/12/17.
 */

public class TelaInicial extends AppCompatActivity {
    private static int tempo;
    Intent it;

    protected void onCreate(Bundle savedInstanceState) {
        View view = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view.startAnimation(mLoadAnimation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        tempo = 1000;




        it = new Intent(this, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(it);
                finish();
            }
        }, tempo);
    }



}
