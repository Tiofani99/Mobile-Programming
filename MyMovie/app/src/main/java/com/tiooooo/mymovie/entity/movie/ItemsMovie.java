package com.tiooooo.mymovie.entity.movie;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemsMovie {

    @SerializedName("results")
    private ArrayList<Movie> list;

    public ArrayList<Movie> getList(){
        return list;
    }

    public void setList(ArrayList<Movie> list){
        this.list = list;
    }
}
