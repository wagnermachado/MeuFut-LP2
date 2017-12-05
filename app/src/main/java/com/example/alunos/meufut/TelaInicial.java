package com.example.alunos.meufut;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/**
 * Created by elyas on 04/12/17.
 */

public class TelaInicial extends AppCompatActivity {
    private static int tempo;
    Intent it;

    protected void onCreate(Bundle savedInstanceState) {
        View view = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(2000);
        view.startAnimation(mLoadAnimation);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        tempo = 2000;
        TextView t = (TextView) findViewById(R.id.txtFut);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/rockwell.otf");
        t.setTypeface(typeface);



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
