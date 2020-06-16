package com.tiooooo.mynoteapps.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static volatile  NoteRoomDatabase INSTANCE;

    public static NoteRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NoteRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NoteRoomDatabase.class,"note_database")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            add();
                        }
                    })
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static void add() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final List<Note> list = new ArrayList<>();
                for (int i = 1 ; i <= 9 ; i++){
                    Note dummyNote = new Note();
                    dummyNote.setTitle("Tugas "+i);
                    dummyNote.setDescription("Belajar Modul "+i);
                    dummyNote.setDate("2020/06/06 09:09:0"+i);
                    list.add(dummyNote);
                }

                INSTANCE.noteDao().insertAll(list);
            }
        });
    }
}
