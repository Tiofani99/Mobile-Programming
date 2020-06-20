package com.tiooooo.mymovie.ui.main.fragment.movie;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.entity.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private DataRepository dataRepository;

    public MovieViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public LiveData<List<Movie>> getMovies() {
        return dataRepository.getMovies();
    }
}
