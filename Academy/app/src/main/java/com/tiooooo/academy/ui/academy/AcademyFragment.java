package com.tiooooo.academy.ui.academy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tiooooo.academy.R;
import com.tiooooo.academy.viewmodel.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class AcademyFragment extends Fragment {
    private RecyclerView rvCourse;
    private ProgressBar progressBar;

    public AcademyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_academy, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCourse = view.findViewById(R.id.rv_academy);
        progressBar = view.findViewById(R.id.progress_bar);
        Log.d("Woe", "Academy Jalan");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            AcademyViewModel viewModel = new ViewModelProvider(this, factory).get(AcademyViewModel.class);

            AcademyAdapter academyAdapter = new AcademyAdapter();
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getCourses().observe(getViewLifecycleOwner(), courses -> {
                        progressBar.setVisibility(View.GONE);
                        academyAdapter.setCourses(courses);
                        academyAdapter.notifyDataSetChanged();
                    }
            );
            rvCourse.setLayoutManager(new LinearLayoutManager(getContext()));
            rvCourse.setHasFixedSize(true);
            rvCourse.setAdapter(academyAdapter);
        }
    }
}
