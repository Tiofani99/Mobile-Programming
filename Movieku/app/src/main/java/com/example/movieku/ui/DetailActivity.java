package com.example.movieku.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.movieku.R;
import com.example.movieku.model.Movie;

public class DetailActivity extends AppCompatActivity {

    private Button btnBack;
    public static final String EXTRA_MOVIE = "Movies";
    private TextView tvTitle,tvDesc,tvType,tvHour,tvDirector;
    private RatingBar rbRating;
    private ImageView imgMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnBack=findViewById(R.id.btn_back_detail);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tvTitle = findViewById(R.id.tv_title_detail);
        tvDesc = findViewById(R.id.tv_desc_detail);
        rbRating = findViewById(R.id.rb_rating_detail);
        imgMovie = findViewById(R.id.img_photo_detail);
        tvType = findViewById(R.id.tv_type_detail);
        tvHour = findViewById(R.id.tv_hour_detail);
        tvDirector = findViewById(R.id.tv_director_detail);


        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tvTitle.setText(movie.getTitle());
        rbRating.setRating(Float.parseFloat(movie.getRating()));
        imgMovie.setBackgroundResource(movie.getImg());
        tvDesc.setText(movie.getDesc());
        tvType.setText(movie.getType());
        tvHour.setText(movie.getTime());
        tvDirector.setText(movie.getDirector());


    }
}
