package com.siva.demoapp.common;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by siva on 27/12/16.
 */

public class FetchData extends AsyncTask<String, Integer, JSONObject> {
    HttpURLConnection urlConnection;
    String url;
    String method;
    String payload = null;
    AsyncResponse delegate = null;

    public FetchData(AsyncResponse delegate, String url, String method) {
        this.delegate = delegate;
        this.url = url;
        this.method = method;
    }

    public FetchData(AsyncResponse delegate, String url, String method, JSONObject payload) {
        this(delegate, url, method);
        this.payload = payload.toString();
    }

    @Override
    protected JSONObject doInBackground(String... args) {
        BufferedReader reader = null;

        try {
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
            reader = new BufferedReader(new InputStreamReader(inputStream));
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