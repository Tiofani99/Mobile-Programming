package com.tiooooo.mynoteapps.repository;

import android.app.Application;

import com.tiooooo.mynoteapps.database.Note;
import com.tiooooo.mynoteapps.database.NoteDao;
import com.tiooooo.mynoteapps.database.NoteRoomDatabase;
import com.tiooooo.mynoteapps.helper.SortUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.sqlite.db.SimpleSQLiteQuery;

public class NoteRepository {
    private NoteDao mNotesDao;
    private ExecutorService executorService;

    public NoteRepository(Application application){
        executorService = Executors.newSingleThreadExecutor();
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        mNotesDao = db.noteDao();
    }

    public DataSource.Factory<Integer,Note>getAllNotes(String sort){
        SimpleSQLiteQuery query = SortUtils.getSortedQuery(sort);
        return mNotesDao.getAllNotes(query);
    }

    public void insert(final Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.insert(note);
            }
        });
    }

    public void delete(final Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.delete(note);
            }
        });
    }

    public void update(final Note note){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mNotesDao.update(note);
            }
        });
    }
}
