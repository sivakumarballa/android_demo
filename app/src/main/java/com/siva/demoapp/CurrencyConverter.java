package com.siva.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CurrencyConverter extends AppCompatActivity {

    public void calculate(View view) {
        try {
            EditText currencyTextField = (EditText) findViewById(R.id.currencyTextField);
            Double usd = Double.parseDouble(currencyTextField.getText().toString());

            Double inr = usd * 67.77;

            Toast.makeText(CurrencyConverter.this, usd.shortValue() + " USD = " + inr + " INR", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currency_converter);
    }
}
