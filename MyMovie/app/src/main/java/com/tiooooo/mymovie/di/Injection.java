package com.tiooooo.mymovie.di;

import android.content.Context;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.data.rest.ApiCall;

public class Injection {
    public static DataRepository provideRepository(Context context) {
        ApiCall networkCall = ApiCall.getInstance(context);
        return DataRepository.getInstance(networkCall);
    }
}

