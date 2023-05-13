package com.example.task8c.ViewModels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task8c.R;

public class PlaylistViewHolder extends RecyclerView.ViewHolder {

    private TextView id,name;

    public PlaylistViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textViewName);
        id = itemView.findViewById(R.id.textViewID);
    }

    public void bind(int idValue, String nameValue, String descriptionValue){
        name.setText(nameValue);
        id.setText(String.valueOf(idValue));
    }

    public static PlaylistViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new PlaylistViewHolder(view);
    }
}
