package com.abdoapp.myapplication.classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.abdoapp.myapplication.R;
import com.abdoapp.myapplication.db.DatabaseManager;
import com.abdoapp.myapplication.db.NoteModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.list_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //here we will set our list

        MyAdapter adapter = new MyAdapter(getList());
        recyclerView.setAdapter(adapter);

        //database operation
        NoteModel noteModel = new NoteModel();
        noteModel.setText("2 kg tmatm");
        noteModel.setTitle("tlbat");
        DatabaseManager.getInstance(this).noteDao().insert(noteModel);
    }

    private List<NoteModel> getList() {

        return null;
    }
}