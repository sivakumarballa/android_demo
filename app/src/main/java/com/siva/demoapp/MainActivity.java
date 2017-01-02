package com.siva.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view) {
        EditText userNameTextField = (EditText) findViewById(R.id.userNameTextField);
        EditText passwordTextField = (EditText) findViewById(R.id.passwordTextField);

        Log.i("Info", "Button Pressed");
        Toast.makeText(MainActivity.this, "Username: " + userNameTextField.getText().toString() + ", Password: " +
                passwordTextField.getText().toString() + "\n Logging In...", Toast.LENGTH_LONG).show();

        Log.i("Info", userNameTextField.getText().toString());
        Log.i("Info", passwordTextField.getText().toString());

        Intent i = new Intent(this, ListViews.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
