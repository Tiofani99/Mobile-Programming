package com.tiooooo.mynoteapps.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.tiooooo.mynoteapps.R;
import com.tiooooo.mynoteapps.database.Note;
import com.tiooooo.mynoteapps.helper.SortUtils;
import com.tiooooo.mynoteapps.ui.ViewModelFactory;
import com.tiooooo.mynoteapps.ui.insert.NoteAddUpdateActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private MainViewModel mainViewModel;
    private NotePagedListAdapter notePagedListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notePagedListAdapter = new NotePagedListAdapter(this);

        recyclerView = findViewById(R.id.rv_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(notePagedListAdapter);

        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.fab_add){
                    Intent intent = new Intent(MainActivity.this, NoteAddUpdateActivity.class);
                    startActivityForResult(intent,NoteAddUpdateActivity.REQUEST_ADD);
                }
            }
        });

        mainViewModel = obtainViewModel(MainActivity.this);
        mainViewModel.getAllNotes(SortUtils.NEWEST).observe(this,noteObserver);

    }

    private final Observer<PagedList<Note>> noteObserver = new Observer<PagedList<Note>>() {
        @Override
        public void onChanged(@Nullable PagedList<Note> noteList) {
            if (noteList != null) {
                notePagedListAdapter.submitList(noteList);
            }
        }
    };
    @NonNull
    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if(requestCode == NoteAddUpdateActivity.REQUEST_ADD){
                if(resultCode == NoteAddUpdateActivity.RESULT_ADD){
                    showSnackBarMessage(getString(R.string.added));
                }
            }else if(requestCode == NoteAddUpdateActivity.REQUEST_UPDATE){
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE){
                    showSnackBarMessage(getString(R.string.changed));
                }else if(resultCode == NoteAddUpdateActivity.RESULT_DELETE){
                    showSnackBarMessage(getString(R.string.deleted));
                }
            }
        }
    }

    private void showSnackBarMessage(String string) {
        Snackbar.make(recyclerView,string,Snackbar.LENGTH_SHORT).show();
    }
}