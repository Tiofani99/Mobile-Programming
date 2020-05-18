package com.tiooooo.mymovie.entity.tvseries;

import com.google.gson.annotations.SerializedName;
import com.tiooooo.mymovie.entity.movie.Movie;

import java.util.ArrayList;

public class ItemsTvSeries {

    @SerializedName("results")
    private ArrayList<TvSeries> list;

    public ArrayList<TvSeries> getList(){
        return list;
    }

    public void setList(ArrayList<TvSeries> list){
        this.list = list;
    }

}
