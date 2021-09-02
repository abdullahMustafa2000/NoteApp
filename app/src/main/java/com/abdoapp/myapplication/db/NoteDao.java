package com.abdoapp.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {


    @Insert
    void insert(NoteModel noteModel);

    @Delete
    void delete(NoteModel noteModel);

    @Query("SELECT * FROM note_table")
    List<NoteModel> getAllNotes();

}
