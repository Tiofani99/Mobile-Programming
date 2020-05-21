package com.tiooooo.mymovie.ui.main.fragment.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.tiooooo.mymovie.R;
import com.tiooooo.mymovie.entity.movie.Movie;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentMovies extends Fragment {


    private static final String MOVIE_KEY = "MOVIE_KEY";
    private MovieAdapter adapter;

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    @BindView(R.id.tv_information)
    TextView tvInformationData;
    @BindView(R.id.shimmerFrameLayout)
    ShimmerFrameLayout shimmerFrameLayout;

    public FragmentMovies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (getActivity() != null) {
            initAdapter();
            getMovies();
        }
    }

    private void initAdapter() {
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new MovieAdapter(getActivity());
        adapter.notifyDataSetChanged();
    }

    private void showLoading(Boolean state) {
        if (state) {
            shimmerFrameLayout.setVisibility(View.VISIBLE);
        } else {
            shimmerFrameLayout.setVisibility(View.GONE);
        }
    }


    private void getMovies() {
        showLoading(true);
        MovieViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);
        ArrayList<Movie> movies = viewModel.getMovies();
        adapter.setMovies(movies);
        rvMovies.setAdapter(adapter);
        showLoading(false);
    }

}

