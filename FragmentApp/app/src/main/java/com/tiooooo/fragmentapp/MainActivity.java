package com.tiooooo.fragmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivHome = findViewById(R.id.ivHome);
        ImageView ivFavorite = findViewById(R.id.ivFavorite);
        ImageView ivProfile = findViewById(R.id.ivProfile);

        ivHome.setOnClickListener(this);
        ivFavorite.setOnClickListener(this);
        ivProfile.setOnClickListener(this);

        initFragment(new HomeFragment());

    }

    private void initFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameFragment,fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.ivHome:
                initFragment(new HomeFragment());
                break;

            case R.id.ivFavorite:
                initFragment(new FavoriteFragment());
                break;

            case R.id.ivProfile:
                initFragment(new ProfileFragment());
                break;
        }

    }
}
