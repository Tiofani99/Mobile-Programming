package com.tiooooo.mymovie.ui.main.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.tiooooo.mymovie.BuildConfig;
import com.tiooooo.mymovie.R;
import com.tiooooo.mymovie.entity.movie.ItemsMovie;
import com.tiooooo.mymovie.entity.movie.Movie;
import com.tiooooo.mymovie.rest.ApiClient;
import com.tiooooo.mymovie.rest.ApiInterface;
import com.tiooooo.mymovie.ui.main.adapter.MovieAdapter;

import java.util.ArrayList;


public class FragmentMovies extends Fragment {


    private static final String MOVIE_KEY = "MOVIE_KEY";
    private final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;



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
        ButterKnife.bind(this,view);
        initAdapter();
        getMovies();
    }

    private void initAdapter(){
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));;
        adapter = new MovieAdapter(getActivity());
        adapter.notifyDataSetChanged();
    }

    private void showLoading(Boolean state){
        if(state){
            shimmerFrameLayout.setVisibility(View.VISIBLE);
        }else{
            shimmerFrameLayout.setVisibility(View.GONE);
        }
    }


    private void getMovies() {
        showLoading(true);
        Call<ItemsMovie> call = apiService.getMovies(BuildConfig.API_KEY);
        movieList = new ArrayList<>();

        call.enqueue(new Callback<ItemsMovie>() {
            @Override
            public void onResponse(Call<ItemsMovie> call, Response<ItemsMovie> response) {
                if(response.body() != null){
                    movieList = response.body().getList();
                    if(movieList.isEmpty()){
                        String errorMessage = getResources().getString(R.string.data_not_found);
                        tvInformationData.setText(errorMessage);
                        tvInformationData.setVisibility(View.VISIBLE);
                    }
                }

                adapter.setMovies(movieList);
                rvMovies.setAdapter(adapter);
                showLoading(false);
            }

            @Override
            public void onFailure(Call<ItemsMovie> call, Throwable t) {
                showLoading(false);
                String errorMessage = getResources().getString(R.string.no_internet);
                tvInformationData.setText(errorMessage);
                tvInformationData.setVisibility(View.VISIBLE);
            }
        });




    }
}
