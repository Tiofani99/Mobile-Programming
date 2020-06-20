package com.tiooooo.mymovie.ui.detail;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.entity.Movie;
import com.tiooooo.mymovie.entity.TvSeries;
import com.tiooooo.mymovie.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel viewModel;
    private Movie dummyMovie = FakeDataDummy.generateDummyMovies().get(0);
    private TvSeries dummyTvSeries =FakeDataDummy.generateDummyTvSeries().get(0);
    private int movieID = dummyMovie.getId();
    private int tvSeriesID = dummyTvSeries.getId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private DataRepository dataRepository;

    @Mock
    private Observer<Movie> movieObserver;

    @Mock
    private Observer<TvSeries> tvSeriesObserver;

    @Before
    public void setUp(){
        viewModel = new DetailViewModel(dataRepository);
    }

    @Test
    public void getMovieDetails(){
        viewModel.setId(movieID);
        MutableLiveData<Movie> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);
        when(dataRepository.getMovieDetail(movieID)).thenReturn(movies);
        Movie movie = viewModel.getMovieDetails().getValue();
        verify(dataRepository).getMovieDetail(movieID);
        assertNotNull(movie);
        assertEquals(dummyMovie.getId(),movie.getId());
        assertEquals(dummyMovie.getTitle(),movie.getTitle());
        assertEquals(dummyMovie.getVote_count(),movie.getVote_count());
        assertEquals(dummyMovie.getDesc(),movie.getDesc());
        assertEquals(dummyMovie.getPopularity(),movie.getPopularity());

        viewModel.getMovieDetails().observeForever(movieObserver);
        movieObserver.onChanged(dummyMovie);
    }

    @Test
    public void getTvSeriesDetail(){
        viewModel.setId(tvSeriesID);
        MutableLiveData<TvSeries> tvSeriesResponse = new MutableLiveData<>();
        tvSeriesResponse.setValue(dummyTvSeries);
        when(dataRepository.getTvSeriesDetail(tvSeriesID)).thenReturn(tvSeriesResponse);
        TvSeries tvSeries = viewModel.getTvSeriesDetails().getValue();
        assertNotNull(tvSeries);
        assertEquals(dummyTvSeries.getId(),tvSeries.getId());
        assertEquals(dummyTvSeries.getName(),tvSeries.getName());
        assertEquals(dummyTvSeries.getVote_count(),tvSeries.getVote_count());
        assertEquals(dummyTvSeries.getDesc(),tvSeries.getDesc());
        assertEquals(dummyTvSeries.getPopularity(),tvSeries.getPopularity());

        viewModel.getTvSeriesDetails().observeForever(tvSeriesObserver);
        tvSeriesObserver.onChanged(dummyTvSeries);

    }


}