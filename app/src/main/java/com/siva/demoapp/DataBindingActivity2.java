package com.siva.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataBindingActivity2 extends AppCompatActivity {
    @BindView(R.id.bindFirstName) TextView bindFirstName;
    @BindView(R.id.bindLastName) TextView bindLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding2);

        ButterKnife.bind(this);
        bindFirstName.setText("Firstname");
        bindLastName.setText("Lastname");
    }

    @OnClick(R.id.bindButtonClick) void LogMe() {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        Log.i("Binding2", "button clicked");
    }
}
