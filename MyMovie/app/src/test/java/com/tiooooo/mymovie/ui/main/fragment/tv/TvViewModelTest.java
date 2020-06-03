package com.tiooooo.mymovie.ui.main.fragment.tv;

import com.tiooooo.mymovie.data.source.TvSeriesResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TvViewModelTest {
    private TvSeriesViewModel viewModel;
    private final static int DATA_LENGTH = 10;

    @Before
    public void setUp(){
        viewModel  = new TvSeriesViewModel();
    }

    @Test
    public void getDataTv(){
        ArrayList<TvSeriesResponse> tvSeries = viewModel.getTvSeries();
        assertNotNull(tvSeries);
        assertEquals(DATA_LENGTH,tvSeries.size());
    }

}