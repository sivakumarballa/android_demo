package com.siva.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class AnimationsWithXML extends AppCompatActivity {

    Animation animation = null;
    ImageView img = null;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back_activity_incoming, R.anim.back_acitivity_outgoing);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_with_xml);

        img = (ImageView) findViewById(R.id.img);

        Button btn_fade = (Button) findViewById(R.id.btn_fade);
        btn_fade.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                img.startAnimation(animation);
            }
        });

        Button btn_bounce = (Button) findViewById(R.id.btn_bounce);
        btn_bounce.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
                img.startAnimation(animation);
            }
        });

        Button btn_move = (Button) findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                img.startAnimation(animation);
            }
        });

    }
}
