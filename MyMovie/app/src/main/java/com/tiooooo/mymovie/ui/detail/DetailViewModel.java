package com.tiooooo.mymovie.ui.detail;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.entity.Movie;
import com.tiooooo.mymovie.entity.TvSeries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {
    private int id;
    private DataRepository repository;

    public DetailViewModel(DataRepository repository) {
        this.repository = repository;
    }

    public LiveData<Movie> getMovieDetails() {
        return repository.getMovieDetail(id);
    }

    public LiveData<TvSeries> getTvSeriesDetails() {
        return repository.getTvSeriesDetail(id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
