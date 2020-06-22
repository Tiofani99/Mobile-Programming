package com.tiooooo.mymovie.ui.favorite.movie;

import com.tiooooo.mymovie.data.DataRepository;
import com.tiooooo.mymovie.data.local.entitiy.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MovieFavoriteViewModel extends ViewModel {
    private DataRepository dataRepository;

    public MovieFavoriteViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public LiveData<PagedList<Movie>> getMovieFavorite(){
        return dataRepository.getMovieFavorite();
    }

    void setBookmark(Movie movie){
        final boolean newState = !movie.isBookmarked();
        dataRepository.setMovieFavorite(movie,newState);
    }


}
