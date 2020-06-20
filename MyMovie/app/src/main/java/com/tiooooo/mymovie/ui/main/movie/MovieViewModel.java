package com.tiooooo.mymovie.ui.main.movie;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.data.rest.response.MovieResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private DataRepository dataRepository;

    public MovieViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public LiveData<List<MovieResponse>> getMovies() {
        return dataRepository.getMovies();
    }
}
