package com.tiooooo.mynoteapps.ui.main;

import android.app.Application;

import com.tiooooo.mynoteapps.database.Note;
import com.tiooooo.mynoteapps.repository.NoteRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private NoteRepository mNoteRepository;

    public MainViewModel(Application application){
        mNoteRepository = new NoteRepository(application);
    }

    LiveData<List<Note>> getAllNotes(){
        return mNoteRepository.getAllNotes();
    }
}
