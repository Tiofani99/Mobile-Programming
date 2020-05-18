package com.tiooooo.mymovie.rest;

import com.tiooooo.mymovie.entity.movie.ItemsMovie;
import com.tiooooo.mymovie.entity.tvseries.ItemsTvSeries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie")
    Call<ItemsMovie> getMovies(
            @Query("api_key")
            String apiKey
    );

    @GET("discover/tv")
    Call<ItemsTvSeries> getTvSeries(
            @Query("api_key")
            String apiKey
    );
}
