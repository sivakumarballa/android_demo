package com.siva.demoapp.common;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Suresh on 1/10/2017.
 */

public class ApiCall {

    private static OkHttpClient client;
    private static ApiCallList apiCallList = ApiCallList.getInstance();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Function to make actual API calls using OkHttp.
     * @param request
     * @param callback
     * @return
     */
    static Call SendRequest(Request request, Callback callback) {
        client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * GET call with url and callback
     * @param url
     * @param callback
     * @return Instance of OkHttp Call object
     */
    public static Call GET(String url, Callback callback) {
        return GET(url, null, callback);
    }

    /**
     * GET call with url, query params, callback
     * @param url
     * @param params
     * @param callback
     * @return Instance of OkHttp Call object
     */
    public static Call GET(String url, JSONObject params, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(apiCallList.getApiURL(url)).newBuilder();

        if(params != null) {
            Iterator<String> keys = params.keys();
            String key = null;
            while(keys.hasNext()) {
                key = keys.next();
                try {
                    urlBuilder.addQueryParameter(key, params.getString(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();

        return SendRequest(request, callback);
    }

    /**
     * POST call with url, params, callback
     * @param url
     * @param params
     * @param callback
     * @return Instance of OkHttp Call object
     */
    public static Call POST(String url, JSONObject params, Callback callback) {
        RequestBody body = RequestBody.create(JSON, params.toString());

        Request request = new Request.Builder()
                .url(apiCallList.getApiURL(url))
                .post(body)
                .build();

        return SendRequest(null, callback);
    }
}
