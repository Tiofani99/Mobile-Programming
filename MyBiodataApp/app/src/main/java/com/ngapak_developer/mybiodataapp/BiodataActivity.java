package com.ngapak_developer.mybiodataapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BiodataActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        FloatingActionButton btnCall = findViewById(R.id.fab);
        TextView tvGithub = findViewById(R.id.tv_github);
        TextView tvIg = findViewById(R.id.tv_ig);
        btnCall.setOnClickListener(this);
        tvGithub.setOnClickListener(this);
        tvIg.setOnClickListener(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("My Biodata");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.fab:
                String phoneNumber = "082137138344";
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialPhoneIntent);
                break;

            case R.id.tv_github:
                String linkGithub = "https://github.com/Tiofani99";
                Intent openGithub =new Intent(Intent.ACTION_VIEW);
                openGithub.setData(Uri.parse(linkGithub));
                startActivity(openGithub);
                break;

            case R.id.tv_ig:
                String linkIg = "https://www.instagram.com/tio.fani/";
                Intent openIg = new Intent(Intent.ACTION_VIEW);
                openIg.setData(Uri.parse(linkIg));
                startActivity(openIg);
                break;

        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
