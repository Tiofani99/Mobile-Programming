package com.tiooooo.mymovie.data.rest;

import com.tiooooo.mymovie.entity.Movie;
import com.tiooooo.mymovie.entity.TvSeries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<Movie> getMovies(
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}")
    Call<Movie> getMovieById(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("discover/tv")
    Call<TvSeries> getTvSeries(
            @Query("api_key") String apiKey
    );


    @GET("tv/{tv_id}")
    Call<TvSeries> getTvSeriesById(
            @Path("tv_id") int id,
            @Query("api_key") String apiKey);
}
