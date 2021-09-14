package com.abdoapp.myapplication.ui;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.abdoapp.myapplication.db.DatabaseManager;
import com.abdoapp.myapplication.db.NoteDao;
import com.abdoapp.myapplication.db.NoteModel;

import java.util.ArrayList;
import java.util.List;

// operation class
public class MainActivityViewModel extends AndroidViewModel {

    NoteDao dao;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        dao = DatabaseManager.getInstance(application).noteDao();
    }

    /*public LiveData<ArrayList<NoteModel>> getAll() {
        return dao.getAllNotes();
    }*/

}
