package com.siva.demoapp;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.siva.demoapp.databinding.ActivityDataBindingBinding;
import com.siva.demoapp.models.dataBindingUser;

public class DataBindingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        dataBindingUser user = new dataBindingUser("Firstname", "Lastname");
        binding.setUser(user);

        binding.changeButtonText.setText("Binding1");

        BindClickHandler clickHandler = new BindClickHandler(getApplicationContext());
        binding.setClickHandler(clickHandler);
    }
}