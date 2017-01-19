package com.siva.demoapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Suresh on 1/18/2017.
 */

public class BindClickHandler {
    Context context;
    public BindClickHandler(Context context) {
        this.context = context;
    }

    public void onButtonClick(View view){
        Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show();
        Log.i("Binding1", "button clicked");
    }
}