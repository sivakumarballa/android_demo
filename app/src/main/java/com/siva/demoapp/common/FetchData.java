package com.siva.demoapp.common;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by siva on 27/12/16.
 */

public class FetchData extends AsyncTask<String, Integer, JSONObject> {
    HttpURLConnection urlConnection;
    String url;
    String method;
    String payload = null;
    AsyncResponse delegate = null;

    ApiCallList apiCallList = ApiCallList.getInstance();

    /**
     * This method gets the complete url and method from ApiCallsList
     * @param url unique identifier of api
     */
    public void updateApiUrlAndMethod(String url) {
        this.url = apiCallList.getApiURL(url);
        this.method = apiCallList.getApiMethod(url);
    }

    /**
     * Appending object params to url for GET call
     */
    // TODO find built in method to do it
    public void addParametersToURL() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(this.payload);
            Iterator<String> keys = jsonObject.keys();
            boolean firstKey = true;
            String key = null, val = null;
            while(keys.hasNext()){
                key = keys.next();
                val = jsonObject.getString(key);
                if(firstKey) {
                    firstKey = false;
                    this.url += "?" + key + "=" + val;
                } else {
                    this.url += "&" + key + "=" + val;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(this.url);
    }

    public FetchData(AsyncResponse delegate, String url, boolean isInternalApi) {
        this.delegate = delegate;
        this.url = url;
        this.method = "GET";

        if(isInternalApi) {
            updateApiUrlAndMethod(url);
        }
    }

    public FetchData(AsyncResponse delegate, String url, boolean isInternalApi, JSONObject payload) {
        this.delegate = delegate;
        this.url = url;
        this.method = "POST";
        this.payload = payload.toString();

        if(isInternalApi) {
            updateApiUrlAndMethod(url);
        }
    }

    @Override
    protected JSONObject doInBackground(String... args) {
        BufferedReader reader = null;

        try {
            if(method.equals("GET") && this.payload != null) {
                addParametersToURL();
            }
            URL url = new URL(this.url);

            // Open HTTP connection
            urlConnection = (HttpURLConnection) url.openConnection();

            // HTTP method GET/POST/PUT/DELETE
            urlConnection.setRequestMethod(this.method);

            // Disables caching, Set connection time out
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(10000);

            // Only for POST & PUT calls
            if(method.equals("POST") || method.equals("PUT")) {
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setDoInput(true);

                // Opening output stream to send data
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(this.payload);
                wr.close();
            }

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", "FAILURE");
                jsonObj.put("message", "Connection to the Server Failed: " + statusCode);
                return jsonObj;
            }

            // Get the response
            InputStream inputStream = urlConnection.getInputStream();
            if(inputStream == null) {
                // Nothing to do
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String inputLine = null;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine + "\n");
            }

            return new JSONObject(response.toString());
        } catch(Exception e) {
            try {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("status", "FAILURE");
                jsonObj.put("message", e.toString());
                return jsonObj;
            } catch(Exception e1) {
                System.out.println(e1);
                return null;
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        this.delegate.processFinish(result);
    }
}