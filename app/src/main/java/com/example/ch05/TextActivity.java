package com.example.ch05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class TextActivity extends AppCompatActivity {

    private Button ckbt;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        try {
            InputStream is = getResources().openRawResource(R.raw.notice);
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String text = new String(buffer, "UTF-8");
            tv = (TextView) findViewById(R.id.text);
            tv.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ckbt=findViewById(R.id.ckbt);
        ckbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
