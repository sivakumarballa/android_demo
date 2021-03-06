package com.siva.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.siva.demoapp.common.ApiCall;
import com.siva.demoapp.common.AsyncResponse;
import com.siva.demoapp.common.FetchData;
import com.siva.demoapp.common.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

public class AsyncTasks extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    TextView view = null;
    int a = 1;
    Utility utility = Utility.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_tasks);

        view = (TextView) findViewById(R.id.textView);

        findViewById(R.id.nextAction).setOnClickListener(this);
        findViewById(R.id.getData).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        switch (i) {
            case R.id.getData:
                this.getData();
                break;
            case R.id.nextAction:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.setText("Counter value " + a++);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    private void getData() {
        if(!utility.checkInternetConnection(this)) {
            utility.showNoConnectionDialog(this);
        }

        // new FetchData(this, "https://api.github.com/users/sivakumarballa", false).executeOnExecutor(THREAD_POOL_EXECUTOR);

        String json = "{'param1' : 'value1', 'param2' : 'value2'}";
        JSONObject queryParams = null;
        try {
            queryParams = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
        new FetchData(this, "login", true, jsonObject).executeOnExecutor(THREAD_POOL_EXECUTOR);
        */

        ApiCall.GET("https://api.github.com/users/sivakumarballa", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("response", "Failure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseStr = response.body().string();
                    Log.i("response", responseStr);
                } else {
                    Log.i("response", "request not successful");
                }
            }
        });
    }

    @Override
    public void processFinish(JSONObject data) {
        utility.showDialog(this, data.toString());
    }
}
