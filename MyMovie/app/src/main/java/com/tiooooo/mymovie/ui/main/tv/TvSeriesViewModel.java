package com.tiooooo.mymovie.ui.main.tv;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.data.local.entitiy.TvSeries;
import com.tiooooo.mymovie.data.rest.response.TvSeriesResponse;
import com.tiooooo.mymovie.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TvSeriesViewModel extends ViewModel {
    private DataRepository dataRepository;

    public TvSeriesViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public LiveData<Resource<List<TvSeries>>> getTvSeries(){
        return dataRepository.getTvSeries();
    }
}
