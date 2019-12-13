package com.example.mytestingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSetValue;
    private TextView tvText;
    ImageView imgPreview;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetValue = findViewById(R.id.btn_set_value);
        tvText = findViewById(R.id.tv_text);
        imgPreview = findViewById(R.id.img_preview);
//        imgPreview.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.fronalpstock_big));

        Glide.with(this)
                .load(R.drawable.fronalpstock_big)
                .into(imgPreview);


        btnSetValue.setOnClickListener(this);


        names = new ArrayList<>();
        names.add("Tio");
        names.add("Fani");
        names.add("Tio Fani");
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_set_value){
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                name.append(names.get(i)).append("\n");
            }
            tvText.setText(name.toString());
        }
    }
}
