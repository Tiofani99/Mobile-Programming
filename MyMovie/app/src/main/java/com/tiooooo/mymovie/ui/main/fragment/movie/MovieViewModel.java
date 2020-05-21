package com.tiooooo.mymovie.ui.main.fragment.movie;

import com.tiooooo.mymovie.entity.movie.Movie;
import com.tiooooo.mymovie.utils.DataDummy;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

class MovieViewModel extends ViewModel {
    ArrayList<Movie> getMovies() {
        return DataDummy.getMovies();
    }
}
