package com.tiooooo.academy.di;

import android.content.Context;

import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.data.source.remote.RemoteDataSource;
import com.tiooooo.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Context context){

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));

        return AcademyRepository.getInstance(remoteDataSource);
    }
}
