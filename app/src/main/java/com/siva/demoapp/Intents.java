package com.siva.demoapp;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.siva.demoapp.models.Employee;

public class Intents extends AppCompatActivity implements View.OnClickListener {

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);

        new TextView(this);

        Button startActivityButton = (Button) findViewById(R.id.start_activity);
        startActivityButton.setOnClickListener(this);
        /*startActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFunction(view);
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        onClickFunction(view);
    }

    public void onClickFunction(View view) {
        /* One way */
        // intent.setData(null);
        //intent.setClass(Intents.this, ListViews.class);

        /* Using intent-filter */
        Intent intent = new Intent("com.siva.demoapp.listviews");
        intent.putExtra("Message", "Hello Activity");
        intent.putExtra("Message2", "Hello Activity2");
        intent.putExtra("emp1", new Employee("Vish", "Manager"));

        Bundle b = new Bundle();
        b.putSerializable("emp2", new Employee("Sumathi", "Architect"));
        b.putString("name", "sivakumar");
        b.putString("designation", "UX Engineer");
        intent.putExtras(b);

        startActivity(intent);
    }

    public void startOtherAppActivity(View view) {
        Intent i = new Intent("com.siva.moneyone.main");
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void startOtherAppActivityExported(View view) {
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.siva.moneyone", "com.siva.moneyone.SecondActivity"));
        startActivity(i);
    }

    public void dialNumber(View view) {
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("tel:7702595800"));
        startActivity(intent);
    }

    public void pageLoad(View view) {
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.divami.com"));
        startActivity(intent);
    }

    public void takePhoto(View view) {
        intent.setData(null);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap)result.getExtras().get("data");
            ImageView img = (ImageView) findViewById(R.id.camera_img);
            img.setImageBitmap(image);
        }
    }
}
