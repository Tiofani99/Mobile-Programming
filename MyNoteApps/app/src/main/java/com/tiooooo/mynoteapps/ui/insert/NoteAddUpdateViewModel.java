package com.tiooooo.mynoteapps.ui.insert;

import android.app.Application;

import com.tiooooo.mynoteapps.database.Note;
import com.tiooooo.mynoteapps.repository.NoteRepository;

import androidx.lifecycle.ViewModel;

public class NoteAddUpdateViewModel extends ViewModel {
    private NoteRepository mNoteRepository;

    public NoteAddUpdateViewModel(Application application){
        mNoteRepository = new NoteRepository(application);
    }

    public void insert(Note note){
        mNoteRepository.insert(note);
    }

    public void update(Note note){
        mNoteRepository.update(note);
    }

    public void delete(Note note){
        mNoteRepository.delete(note);
    }

}
