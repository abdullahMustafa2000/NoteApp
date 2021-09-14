package com.abdoapp.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.abdoapp.myapplication.R;
import com.abdoapp.myapplication.db.DatabaseManager;
import com.abdoapp.myapplication.db.NoteDao;
import com.abdoapp.myapplication.db.NoteModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddNoteActivity extends AppCompatActivity {

    TextView titleEt, txtEt;
    Button saveBtn, cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        /*Intent intent = getIntent();
        String noteTitle = intent.getStringExtra("noteTitle");
        String noteText = intent.getStringExtra("noteText");*/
        titleEt = findViewById(R.id.title_et);
        txtEt = findViewById(R.id.txt_et);
        saveBtn = findViewById(R.id.save_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        saveBtn.setOnClickListener(view -> addToDb());
        /*if (noteTitle != null && noteText!= null) {
            titleEt.setText(noteTitle);
            txtEt.setText(noteText);
        }*/
    }

    // observable {single, observbale, completable...
    // subscribeOn control observable thread

    // observer has the result from observable
    // observeOn control observer thread

    private void addToDb() {
        if (inputIsOk()) {
            NoteDao dao = DatabaseManager.getInstance(this).noteDao();
            NoteModel noteModel = new NoteModel();
            noteModel.setTitle(titleEt.getText().toString());
            noteModel.setText(txtEt.getText().toString());

            Single.create(new SingleOnSubscribe<NoteModel>() {
                @Override
                public void subscribe(@NonNull SingleEmitter<NoteModel> emitter) throws Throwable {
                    emitter.onSuccess(noteModel);
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(o -> dao.insert(o));
            finish();
        }
    }



    private boolean inputIsOk() {
        return titleEt.getText().toString() != null && txtEt.getText().toString() != null;
    }
}