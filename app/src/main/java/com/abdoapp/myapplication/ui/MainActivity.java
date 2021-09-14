package com.abdoapp.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.abdoapp.myapplication.R;
import com.abdoapp.myapplication.classes.MyAdapter;
import com.abdoapp.myapplication.db.DatabaseManager;
import com.abdoapp.myapplication.db.NoteDao;
import com.abdoapp.myapplication.db.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyAdapter adapter;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.list_rv);
        fab = findViewById(R.id.fab_b);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //here we will set our list

        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        getList();
        adapter.setOnRVItemClickListener(new MyAdapter.OnRVItemClickListener() {
            @Override
            public void onClickOnItem(NoteModel noteModel) {
                new Intent(MainActivity.this, AddNoteActivity.class)
                        .putExtra("noteTitle", noteModel.getTitle())
                .putExtra("noteText", noteModel.getText());
                Log.d("TAG", "onClickOnItem: "+ noteModel.getTitle());
            }
        });
        /*//database operation
        NoteModel noteModel = new NoteModel();
        noteModel.setText("2 kg tmatm");
        noteModel.setTitle("tlbat");
        DatabaseManager.getInstance(this).noteDao()*/
    }

    private void getList() {
        NoteDao dao = DatabaseManager.getInstance(this).noteDao();
        dao.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    adapter.setNoteModelList(list);
                    adapter.notifyDataSetChanged();
                });
    }

    @Override
    public void onClick(View view) {

    }
}