package com.tiooooo.mymovie.ui.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiooooo.mymovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_TvSeries extends Fragment {

    public Fragment_TvSeries() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__tv_series, container, false);
    }
}
