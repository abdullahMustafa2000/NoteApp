package com.abdoapp.myapplication.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteModel {

    private String title;
    private String text;

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Override
    public String toString() {
        return "NoteModel{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
