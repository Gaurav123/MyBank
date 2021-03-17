package com.firsttask.mybank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash_screen extends Activity {

    TextView designed, name, app_name;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        app_name = findViewById(R.id.txtview1);
        logo = findViewById(R.id.logo);
        designed = findViewById(R.id.txtview2);
        name = findViewById(R.id.txtview3);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startEnterAnimation();
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(new Intent(getApplicationContext(), PromotionActivity.class));
                finish();

            }
        }, 5500);
    }

    private void startEnterAnimation() {
        app_name.startAnimation(AnimationUtils.loadAnimation(splash_screen.this, R.anim.anim_from_top));
        logo.startAnimation(AnimationUtils.loadAnimation(splash_screen.this, R.anim.anim_from_bottom));

        logo.setVisibility(View.VISIBLE);
        designed.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
        app_name.setVisibility(View.VISIBLE);
    }
}

