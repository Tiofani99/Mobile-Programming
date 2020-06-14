package com.tiooooo.academy.ui.reader.content;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tiooooo.academy.R;
import com.tiooooo.academy.data.source.local.entity.ModuleEntity;
import com.tiooooo.academy.ui.reader.CourseReaderViewModel;
import com.tiooooo.academy.viewmodel.ViewModelFactory;

public class ModuleContentFragment extends Fragment {

    public static final String TAG = ModuleContentFragment.class.getSimpleName();
    private ProgressBar progressBar;
    private WebView webView;
    private Button btnNext;
    private Button btnPrev;
    private CourseReaderViewModel viewModel;

    public ModuleContentFragment() {
        // Required empty public constructor
    }

    public static ModuleContentFragment newInstance(){
        return new ModuleContentFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_module_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.web_view);
        progressBar = view.findViewById(R.id.progress_bar);
        btnNext = view.findViewById(R.id.btn_next);
        btnPrev = view.findViewById(R.id.btn_prev);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
            viewModel = new ViewModelProvider(requireActivity(), factory).get(CourseReaderViewModel.class);

            viewModel.selectedModule.observe(getViewLifecycleOwner(),moduleEntity -> {
                if(moduleEntity != null){
                    switch (moduleEntity.status){
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;

                        case SUCCESS:
                            if (moduleEntity != null){
                                progressBar.setVisibility(View.GONE);
                                if(moduleEntity.data.contentEntity != null){
                                    populateWebView(moduleEntity.data);
                                }
                                setButtonNextPrevState(moduleEntity.data);
                                if(!moduleEntity.data.ismRead()){
                                    viewModel.readContent(moduleEntity.data);
                                }
                            }
                            break;

                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }

                    btnNext.setOnClickListener(v -> viewModel.setNextPage());
                    btnPrev.setOnClickListener(v -> viewModel.setPrevPage());
                }
            });
        }
    }

    private void setButtonNextPrevState(ModuleEntity module) {
        if(getActivity() != null){
            if(module.getmPosition() == 0){
                btnPrev.setEnabled(false);
                btnNext.setEnabled(true);
            }else if(module.getmPosition() == viewModel.getModuleSize() -1 ){
                btnPrev.setEnabled(true);
                btnNext.setEnabled(false);
            }else{
                btnPrev.setEnabled(true);
                btnNext.setEnabled(true);
            }
        }
    }

    private void populateWebView(ModuleEntity module) {
        webView.loadData(module.contentEntity.getContent(), "text/html", "UTF-8");
    }
}
