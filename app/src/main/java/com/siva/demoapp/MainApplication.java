package com.siva.demoapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.ewise.android.pdv.api.PdvApi;
import com.ewise.android.pdv.api.PdvApiImpl;
import com.siva.demoapp.common.EwiseHelper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by siva on 09/01/17.
 */

public class MainApplication extends Application {
    public static final String DEFAULT_MM_HOST = "https://fintechin-wmm.ewise.com/api/";
    public static final String DEFAULT_SWAN_HOST = "https://fintechin-pdv.ewise.com/";
    public static final String EWISEDEMO = "com.ewise.android.pdv.EwiseSharedPref";
    private PdvApi pdvApi;

    @Override public void onCreate() {
        super.onCreate();

        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(EWISEDEMO, Context.MODE_PRIVATE);
        final String mmHost = preferences.getString(EwiseHelper.MM_HOST, DEFAULT_MM_HOST);
        final String swanHost = preferences.getString(EwiseHelper.SWAN_HOST, DEFAULT_SWAN_HOST);
        pdvApi = new PdvApiImpl(swanHost, mmHost);
    }

    public PdvApi getPdvApi() {
        return pdvApi;
    }

    public void setPdvApi(PdvApi pdvApi) {
        this.pdvApi = pdvApi;
    }

    public String getMmHost() {
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(EWISEDEMO,
                Context.MODE_PRIVATE);
        final String mmHost = preferences.getString(EwiseHelper.MM_HOST, DEFAULT_MM_HOST);
        return mmHost;
    }

    public void setMmHost(String mmHost) {
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(EWISEDEMO,
                Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = preferences.edit();
        edit.putString(EwiseHelper.MM_HOST, mmHost);
        edit.commit();
    }

    public String getSwanHost() {
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(EWISEDEMO, Context.MODE_PRIVATE);
        final String swanHost = preferences.getString(EwiseHelper.SWAN_HOST, DEFAULT_SWAN_HOST);
        return swanHost;
    }

    public void setSwanHost(String swanHost) {
        final SharedPreferences preferences = getApplicationContext().getSharedPreferences(EWISEDEMO, Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = preferences.edit();
        edit.putString(EwiseHelper.SWAN_HOST, swanHost);
        edit.commit();
    }
}
