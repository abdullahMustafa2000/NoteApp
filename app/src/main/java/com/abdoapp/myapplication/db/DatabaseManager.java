package com.abdoapp.myapplication.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = NoteModel.class, version = 1, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static DatabaseManager REFERENCE;

    public static DatabaseManager getInstance(Context context) {
        if (REFERENCE == null) {
            // init REFERENCE
            REFERENCE = Room
                    .databaseBuilder(context, DatabaseManager.class, "NotesDB")
                    .build();
        }
        return REFERENCE;
    }

}
