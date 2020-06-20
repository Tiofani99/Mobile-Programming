package com.tiooooo.mymovie.data.rest;

import android.content.Context;
import android.util.Log;

import com.tiooooo.mymovie.BuildConfig;
import com.tiooooo.mymovie.entity.Movie;
import com.tiooooo.mymovie.entity.TvSeries;
import com.tiooooo.mymovie.utils.EspressoIdlingResource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCall {
    private static ApiCall INSTANCE;
    private static ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
    private static final String TAG = ApiCall.class.getSimpleName();

    private Context context;

    public ApiCall(Context context) {
        this.context = context;
    }

    public static ApiCall getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ApiCall(context);
        }

        return INSTANCE;
    }

    public LiveData<List<Movie>> getMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<List<Movie>> listMovies = new MutableLiveData<>();
        Call<Movie> movieResponseCall = apiClient.getMovies(BuildConfig.API_KEY);
        movieResponseCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.body() != null) {
                    listMovies.postValue(response.body().getList());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG,"Empty Data");
            }
        });

        EspressoIdlingResource.decrement();
        return listMovies;
    }

    public LiveData<Movie> getMoviesDetail(int id) {
        EspressoIdlingResource.increment();
        MutableLiveData<Movie> movieDetail = new MutableLiveData<>();
        Call<Movie> movieDetailResponseCall = apiClient.getMovieById(id, BuildConfig.API_KEY);
        movieDetailResponseCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.body() != null) {
                    movieDetail.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d(TAG,"Empty Data");
            }
        });

        EspressoIdlingResource.decrement();
        return movieDetail;
    }


    public LiveData<List<TvSeries>> getTvSeries() {
        EspressoIdlingResource.increment();
        MutableLiveData<List<TvSeries>> listTvSeries = new MutableLiveData<>();
        Call<TvSeries> movieResponseCall = apiClient.getTvSeries(BuildConfig.API_KEY);
        movieResponseCall.enqueue(new Callback<TvSeries>() {
            @Override
            public void onResponse(Call<TvSeries> call, Response<TvSeries> response) {
                if (response.body() != null) {
                    listTvSeries.postValue(response.body().getTvSeriesList());
                }
            }

            @Override
            public void onFailure(Call<TvSeries> call, Throwable t) {
                Log.d(TAG,"Empty Data");
            }
        });

        EspressoIdlingResource.decrement();
        return listTvSeries;
    }

    public LiveData<TvSeries> getTvSeriesDetail(int id) {
        EspressoIdlingResource.increment();
        MutableLiveData<TvSeries> tvSeriesDetail = new MutableLiveData<>();
        Call<TvSeries> tvSeriesResponseCall = apiClient.getTvSeriesById(id, BuildConfig.API_KEY);
        tvSeriesResponseCall.enqueue(new Callback<TvSeries>() {
            @Override
            public void onResponse(Call<TvSeries> call, Response<TvSeries> response) {
                if(response.body() != null);
                tvSeriesDetail.postValue(response.body());
            }

            @Override
            public void onFailure(Call<TvSeries> call, Throwable t) {
                Log.d(TAG,"Empty Data");
            }
        });

        EspressoIdlingResource.decrement();
        return tvSeriesDetail;
    }
}
