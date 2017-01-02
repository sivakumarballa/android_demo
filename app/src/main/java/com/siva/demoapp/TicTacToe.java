package com.siva.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TicTacToe extends AppCompatActivity {

    int counter = 1;
    int[] gameState = {3,3,3,3,3,3,3,3,3};

    public void dropIn (View view) {
        ImageView cell = (ImageView) view;

        int tappedCell = Integer.parseInt(cell.getTag().toString());

        if(gameState[tappedCell] == 3) {
            gameState[tappedCell] = counter;

            cell.setTranslationY(-1000f);
            if(counter == 1) {
                cell.setImageResource(R.drawable.yellow);
                counter = 2;
            } else {
                cell.setImageResource(R.drawable.red);
                counter = 1;
            }
            cell.animate().translationYBy(1000f).rotation(360f).setDuration(200);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_toe);
    }
}
