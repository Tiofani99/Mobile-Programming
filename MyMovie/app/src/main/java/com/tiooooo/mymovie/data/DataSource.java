package com.tiooooo.mymovie.data;

import com.tiooooo.mymovie.data.rest.response.MovieResponse;
import com.tiooooo.mymovie.data.rest.response.TvSeriesResponse;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface DataSource {
    LiveData<List<MovieResponse>> getMovies();

    LiveData<List<TvSeriesResponse>> getTvSeries();

    LiveData<MovieResponse> getMovieDetail(int id);

    LiveData<TvSeriesResponse> getTvSeriesDetail(int id);
}
