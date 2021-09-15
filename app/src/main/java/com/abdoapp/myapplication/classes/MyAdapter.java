package com.abdoapp.myapplication.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdoapp.myapplication.R;
import com.abdoapp.myapplication.db.NoteModel;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<NoteModel> noteModelList;
    Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    private OnRVItemClickListener onRVItemClickListener;

    public void setOnRVItemClickListener(OnRVItemClickListener onRVItemClickListener) {
        this.onRVItemClickListener = onRVItemClickListener;
    }

    private OnDeleteItemClickListener onDeleteItemClickListener;

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener onDeleteItemClickListener) {
        this.onDeleteItemClickListener = onDeleteItemClickListener;
    }

    public void setNoteModelList(List<NoteModel> noteModelList) {
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
        NoteModel noteModel = noteModelList.get(position);
        holder.noteTv.setText(noteModelList.get(position).getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: abdo 2" + noteModel.getText());
                onRVItemClickListener.onClickOnItem(noteModel);
            }
        });

        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteItemClickListener.onClickOnItem(noteModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (noteModelList != null)
        return noteModelList.size();
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView noteTv;
        ImageView deleteIv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTv = itemView.findViewById(R.id.note_tv);
            deleteIv = itemView.findViewById(R.id.delete_note_iv);
        }
    }

    public interface OnRVItemClickListener {
        void onClickOnItem(NoteModel noteModel);
    }

    public interface OnDeleteItemClickListener {
        void onClickOnItem(NoteModel noteModel);
    }
}
