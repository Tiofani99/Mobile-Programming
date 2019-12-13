package com.example.movieku.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.movieku.R;
import com.example.movieku.adapter.MovieAdapter;
import com.example.movieku.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] title;
    private TypedArray img;
    private String[] rating;
    private String[] desc;
    private String[] type;
    private String[] directors;
    private String[] hour;

    private ArrayList<Movie> movies;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView listView = findViewById(R.id.lv_list);
        adapter = new MovieAdapter(this);
        listView.setAdapter(adapter);
        getData();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = movies.get(i);

                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_MOVIE,movie);
                startActivity(intent);
            }
        });
    }

    private void addItem(){
        movies = new ArrayList<>();
        for (int i = 0; i < title.length ; i++) {
            Movie movie = new Movie();
            movie.setImg(img.getResourceId(i,-1));
            movie.setTitle(title[i]);
            movie.setRating((rating[i]));
            movie.setDesc(desc[i]);
            movie.setType(type[i]);
            movie.setTime(hour[i]);
            movie.setDirector(directors[i]);
            movies.add(movie);
            
        }

        adapter.setMovies(movies);
    }

    private void getData(){
        title = getResources().getStringArray(R.array.data_name);
        img = getResources().obtainTypedArray(R.array.data_photo);
        rating = getResources().getStringArray(R.array.data_rating);
        desc = getResources().getStringArray(R.array.data_desc);
        type = getResources().getStringArray(R.array.data_type);
        hour = getResources().getStringArray(R.array.data_hour);
        directors = getResources().getStringArray(R.array.data_director);



    }
}
