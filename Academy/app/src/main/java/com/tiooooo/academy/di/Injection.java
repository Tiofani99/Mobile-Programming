package com.tiooooo.academy.di;

import android.content.Context;

import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.data.source.local.LocalDataSource;
import com.tiooooo.academy.data.source.local.room.AcademyDatabase;
import com.tiooooo.academy.data.source.remote.RemoteDataSource;
import com.tiooooo.academy.utils.AppExecutors;
import com.tiooooo.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Context context){

        AcademyDatabase database = AcademyDatabase.getInstance(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        LocalDataSource localDataSource = LocalDataSource.getInstance(database.academyDao());
        AppExecutors appExecutors = new AppExecutors();

        return AcademyRepository.getInstance(remoteDataSource,localDataSource,appExecutors);
    }
}
