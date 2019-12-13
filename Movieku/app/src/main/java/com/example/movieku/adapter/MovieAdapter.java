package com.example.movieku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieku.R;
import com.example.movieku.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies = new ArrayList<>();




    public void setMovies(ArrayList<Movie> movies){
        this.movies = movies;
    }

    public  MovieAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if(itemView == null){
            itemView= LayoutInflater.from(context).inflate(R.layout.item_list_movie,viewGroup,false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);

        return itemView;
    }

    public class ViewHolder{
        private TextView tvTitle;
        private ImageView imgPhoto;
        private RatingBar rbRating;

        ViewHolder(View view){
            tvTitle = view.findViewById(R.id.tv_title_list);
            imgPhoto = view.findViewById(R.id.img_photo_list);
            rbRating = view.findViewById(R.id.rb_rating_list);
        }

        void bind(Movie movie){
            //tvTitle.setText(movie.getTitle());
            rbRating.setRating(Float.parseFloat(movie.getRating()));
            Glide.with(context)
                    .load(movie.getImg())
                    .apply(new RequestOptions().fitCenter())
                    .into(imgPhoto);
        }

    }
}
