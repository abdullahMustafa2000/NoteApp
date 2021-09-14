package com.abdoapp.myapplication.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NoteModel noteModel);

    /*@Update
    void update(NoteModel noteModel);*/

    @Delete
    void delete(NoteModel noteModel);

    @Query("SELECT * FROM note_table")
    Observable<List<NoteModel>> getAllNotes();

}
