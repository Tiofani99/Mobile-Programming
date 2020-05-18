package com.tiooooo.mymovie.ui.main.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

import com.tiooooo.mymovie.BuildConfig;
import com.tiooooo.mymovie.R;
import com.tiooooo.mymovie.entity.tvseries.ItemsTvSeries;
import com.tiooooo.mymovie.entity.tvseries.TvSeries;
import com.tiooooo.mymovie.rest.ApiClient;
import com.tiooooo.mymovie.rest.ApiInterface;
import com.tiooooo.mymovie.ui.main.adapter.TvSeriesAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTvSeries extends Fragment {

    private static final String EXTRA_TV_SERIES = "TvSeries";
    private final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private TvSeriesAdapter adapter;
    private ArrayList<TvSeries> tvSeriesList;

    @BindView(R.id.progress_bar)
     ProgressBar progressBar;
    @BindView(R.id.rv_tv_series)
     RecyclerView rvTvSeries;
    @BindView(R.id.tv_information)
     TextView tvInformationData;


    public FragmentTvSeries() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_series, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initAdapter();
        getTvSeries();
    }

    private void initAdapter() {
        rvTvSeries.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new TvSeriesAdapter(this.getContext());
        adapter.notifyDataSetChanged();
    }

    private void showLoading(Boolean state){
        if(state){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setErrorInformation(String error){
        tvInformationData.setText(error);
        tvInformationData.setVisibility(View.VISIBLE);
    }

    private void getTvSeries() {
        showLoading(true);
        Call<ItemsTvSeries> call = apiInterface.getTvSeries(BuildConfig.API_KEY);
        tvSeriesList = new ArrayList<>();
        call.enqueue(new Callback<ItemsTvSeries>() {
            @Override
            public void onResponse(Call<ItemsTvSeries> call, Response<ItemsTvSeries> response) {
                if(response.body()!= null){
                    tvSeriesList = response.body().getList();
                    if(tvSeriesList.isEmpty()){
                        setErrorInformation(getResources().getString(R.string.data_not_found));
                    }
                }
                adapter.setTvSeries(tvSeriesList);
                rvTvSeries.setAdapter(adapter);
                showLoading(false);
            }

            @Override
            public void onFailure(Call<ItemsTvSeries> call, Throwable t) {
                showLoading(false);
                setErrorInformation(getResources().getString(R.string.no_internet));
            }
        });
    }
}
