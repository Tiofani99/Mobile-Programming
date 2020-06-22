package com.tiooooo.mymovie.ui.main.movie;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.data.local.entitiy.Movie;
import com.tiooooo.mymovie.data.rest.response.MovieResponse;
import com.tiooooo.mymovie.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private DataRepository dataRepository;

    public MovieViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public LiveData<Resource<List<Movie>>> getMovies() {
        return dataRepository.getMovies();
    }
}
