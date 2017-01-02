package com.siva.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class DemoAnimations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_animations);
    }

    public void onClick(View view) {
        ImageView img1 = (ImageView) findViewById(R.id.img1);
        ImageView img2 = (ImageView) findViewById(R.id.img2);

        if(img1.getAlpha() == 0f) {
            img1.animate().alpha(1f).setDuration(1000);
            img2.animate().alpha(0f).setDuration(1000);
        } else {
            img1.animate().alpha(0f).setDuration(1000);
            img2.animate().alpha(1f).setDuration(1000);
        }
    }

    public void onClick2(View view) {
        final ImageView img3 = (ImageView) findViewById(R.id.img3);

        img3.animate().translationXBy(-1000f)
                .translationYBy(-1000f)
                .rotationBy(4800f)
                .setDuration(2000)
                .withStartAction(new Runnable(){
                    public void run(){
                        Log.i("Info", "Animation Start");
                    }
                });
    }
}
