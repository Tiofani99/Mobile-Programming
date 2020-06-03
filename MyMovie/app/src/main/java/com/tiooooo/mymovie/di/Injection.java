package com.tiooooo.mymovie.di;

import android.app.Application;
import android.content.Context;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.data.DataSource;
import com.tiooooo.mymovie.rest.ApiCall;

public class Injection {
    public static DataRepository provideRepository(Application application) {
        ApiCall networkCall = ApiCall.getInstance(application);
        return DataRepository.getInstance(networkCall);
    }
}

