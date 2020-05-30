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

import com.tiooooo.academy.R;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.ui.reader.CourseReaderViewModel;
import com.tiooooo.academy.viewmodel.ViewModelFactory;

public class ModuleContentFragment extends Fragment {

    public static final String TAG = ModuleContentFragment.class.getSimpleName();

    private WebView webView;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(requireActivity());
            CourseReaderViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(CourseReaderViewModel.class);

            viewModel.getSelectedModule().observe(getViewLifecycleOwner(), module ->{
                if(module != null){
                    populateWebView(module);
                }
            });
        }
    }

    private void populateWebView(ModuleEntity module) {
        webView.loadData(module.contentEntity.getContent(), "text/html", "UTF-8");
    }
}
