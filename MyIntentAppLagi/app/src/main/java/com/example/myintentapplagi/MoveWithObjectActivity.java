package com.example.myintentapplagi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MoveWithObjectActivity extends AppCompatActivity {
    public static final String EXTRA_PERSON = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_object);

        TextView tvObject = findViewById(R.id.tv_object_received);

        Person person  = getIntent().getParcelableExtra(EXTRA_PERSON);
        String text = "Name : "+person.getName() + ",\nEmail : "+person.getEmail()+
                "\nAge : "+person.getAge()+"\nCity : "+person.getCity();
        tvObject.setText(text);
    }
}
