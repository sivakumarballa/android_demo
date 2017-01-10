package com.siva.demoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ewise.android.pdv.api.PdvApi;
import com.ewise.android.pdv.api.PdvApiImpl;
import com.siva.demoapp.common.EwiseHelper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class FinTech extends AppCompatActivity implements View.OnClickListener{

    public static final String DEFAULT_MM_HOST = "https://fintechin-wmm.ewise.com/api/";
    public static final String DEFAULT_SWAN_HOST = "https://fintechin-pdv.ewise.com/";
    public static final String EWISEDEMO = "com.ewise.android.pdv.EwiseSharedPref";
    public PdvApi pdvApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_tech);

        Button startPdv = (Button) findViewById(R.id.start_pdv);
        startPdv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(EWISEDEMO, Context.MODE_PRIVATE);
        final String mmHost = preferences.getString(EwiseHelper.MM_HOST, DEFAULT_MM_HOST);
        final String swanHost = preferences.getString(EwiseHelper.SWAN_HOST, DEFAULT_SWAN_HOST);
        pdvApi = new PdvApiImpl(swanHost, mmHost);
        setupAppConfig();
        pdvApi.apiInit(this, EwiseHelper.acaWebView(this));
        Log.i("Info", "Pdv Initialized");
    }


    private void setupAppConfig() {
        final List<String> issuerDns = asList("CN=RapidSSL SHA256 CA - G3, O=GeoTrust Inc., C=US");
        Map<PdvApi.Config, Object> config = new LinkedHashMap<>();
        config.put(
                PdvApi.Config.LIST_OF_ACCEPTED_ISSUERS,
                issuerDns);
        pdvApi.config(config);
    }
}
