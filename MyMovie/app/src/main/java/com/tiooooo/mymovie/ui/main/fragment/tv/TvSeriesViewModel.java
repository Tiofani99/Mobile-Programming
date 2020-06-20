package com.tiooooo.mymovie.ui.main.fragment.tv;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.entity.TvSeries;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TvSeriesViewModel extends ViewModel {
    private DataRepository dataRepository;

    public TvSeriesViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public LiveData<List<TvSeries>> getTvSeries(){
        return dataRepository.getTvSeries();
    }
}
