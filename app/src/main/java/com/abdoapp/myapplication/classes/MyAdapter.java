package com.abdoapp.myapplication.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdoapp.myapplication.R;
import com.abdoapp.myapplication.db.NoteModel;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<NoteModel> noteModelList;

    public MyAdapter(List<NoteModel> noteModelList) {
        this.noteModelList = noteModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.noteTv.setText(noteModelList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView noteTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTv = itemView.findViewById(R.id.note_tv);
        }
    }

}
