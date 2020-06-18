package com.tiooooo.academy.ui.bookmark;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.tiooooo.academy.R;
import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.viewmodel.ViewModelFactory;



/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment implements BookmarkFragmentCallback{

    private RecyclerView rvBookmark;
    private ProgressBar progressBar;
    private BookmarkViewModel viewModel;
    private BookmarkAdapter bookmarkAdapter;


    public BookmarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvBookmark = view.findViewById(R.id.rv_bookmark);
        progressBar = view.findViewById(R.id.progress_bar);
        itemTouchHelper.attachToRecyclerView(rvBookmark);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null){
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            viewModel = new ViewModelProvider(this,factory).get(BookmarkViewModel.class);

            bookmarkAdapter = new BookmarkAdapter(this);
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getBookmarks().observe(getViewLifecycleOwner(), courses ->{
                progressBar.setVisibility(View.GONE);
                bookmarkAdapter.submitList(courses);
                bookmarkAdapter.notifyDataSetChanged();
            });

            rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
            rvBookmark.setHasFixedSize(true);
            rvBookmark.setAdapter(bookmarkAdapter);
        }
    }

    @Override
    public void onShareClick(CourseEntity course){
        if(getActivity() != null){
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle("Bagikan aplikasi ini sekarang")
                    .setText(getResources().getString(R.string.share_text,course.getTitle()))
                    .startChooser();

        }
    }

    private ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if(getView() != null){
                int swipedPosition = viewHolder.getAdapterPosition();
                CourseEntity courseEntity = bookmarkAdapter.getSwipedData(swipedPosition);
                viewModel.setBookmark(courseEntity);
                Snackbar snackbar = Snackbar.make(getView(),R.string.message_undo, BaseTransientBottomBar.LENGTH_LONG);
                snackbar.setAction(R.string.message_ok, v -> viewModel.setBookmark(courseEntity));
                snackbar.show();
            }
        }
    });


}
