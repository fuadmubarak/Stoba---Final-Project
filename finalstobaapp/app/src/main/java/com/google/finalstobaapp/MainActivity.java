package com.google.finalstobaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;

    Animation topanim, botanim, midanim;
    ImageView top, logo, bot1, bot2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top = findViewById(R.id.top);
        logo = findViewById(R.id.logo);
        bot1 = findViewById(R.id.bot1);
        bot2 = findViewById(R.id.bot2);

        topanim = AnimationUtils.loadAnimation(this,R.anim.topanimation);
        midanim = AnimationUtils.loadAnimation(this,R.anim.midanimation);
        botanim = AnimationUtils.loadAnimation(this,R.anim.botanimation);

        top.setAnimation(topanim);
        logo.setAnimation(midanim);
        bot1.setAnimation(botanim);
        bot2.setAnimation(botanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}