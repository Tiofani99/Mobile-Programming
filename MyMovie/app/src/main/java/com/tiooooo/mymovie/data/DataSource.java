package com.tiooooo.mymovie.data;

import com.tiooooo.mymovie.entity.Movie;
import com.tiooooo.mymovie.entity.TvSeries;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface DataSource {
    LiveData<List<Movie>> getMovies();

    LiveData<List<TvSeries>> getTvSeries();

    LiveData<Movie> getMovieDetail(int id);

    LiveData<TvSeries> getTvSeriesDetail(int id);
}
