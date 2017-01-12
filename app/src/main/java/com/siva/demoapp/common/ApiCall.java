package com.siva.demoapp.common;

import android.util.Log;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Suresh on 1/10/2017.
 */

public class ApiCall {
    private static OkHttpClient client;

    public static String addParametersToURL(String url, JSONObject payload) {
        Iterator<String> keys = payload.keys();
        boolean firstKey = true;
        String key = null, val = null;
        while(keys.hasNext()){
            key = keys.next();
            try {
                val = payload.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(firstKey) {
                firstKey = false;
                url += "?" + key + "=" + val;
            } else {
                url += "&" + key + "=" + val;
            }
        }
        return url;
    }

    public static Call get(String url, boolean isInternalApi, Callback callback) {
        client = new OkHttpClient();

        if(isInternalApi) {
            ApiCallList apiCallList = ApiCallList.getInstance();
            url = apiCallList.getApiURL(url);
        }
        Log.i("get url: ", url.toString());
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * TODO better include both get calls in signle call (with and withour params need to finalize)
     */
    public static Call get(String url, boolean isInternalApi, JSONObject payload, Callback callback) {
        if(isInternalApi) {
            ApiCallList apiCallList = ApiCallList.getInstance();
            url = apiCallList.getApiURL(url);
        }
        if(payload != null) {
            url = addParametersToURL(url, payload);
        }
        client = new OkHttpClient();
        Log.i("with params: ", url.toString());
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * TODO headers add
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public Call post(String url, String json, Callback callback) {
        client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
