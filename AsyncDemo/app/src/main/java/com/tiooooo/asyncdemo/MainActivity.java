package com.tiooooo.asyncdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvText;
    private static final String LOG_ASYNC = "AsyncDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        tvText = findViewById(R.id.textView);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
               Worker worker = new Worker();
               worker.execute(tvText);
                break;

            case R.id.button2:
                Log.i(LOG_ASYNC, "Clicked");
                break;
        }

    }
}
