package com.example.task8c.ViewModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task8c.R;
import com.example.task8c.database.Playlist;
import com.example.task8c.database.PlaylistDAO;

public class PlaylistAdapter extends ListAdapter<Playlist,PlaylistAdapter.MyViewHolder> {

    Context context;

    PlaylistViewModel playlistViewModel;

    public PlaylistAdapter(@NonNull DiffUtil.ItemCallback<Playlist> diffCallback,
                           Context context, PlaylistViewModel playlistViewModel) {
        super(diffCallback);
        this.context = context;
        this.playlistViewModel = playlistViewModel;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.MyViewHolder holder, int position) {
        Playlist current = getItem(position);
        holder.name.setText(current.getName());
        holder.id.setText(String.valueOf(current.getId()));

        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlistViewModel.delete(current);
            }
        });
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView id, name;

        ImageView deleteImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            id = itemView.findViewById(R.id.textViewID);
            deleteImage = itemView.findViewById(R.id.deleteImage);
        }
    }


    public static class playlistDiff extends DiffUtil.ItemCallback<Playlist>{

        @Override
        public boolean areItemsTheSame(@NonNull Playlist oldItem, @NonNull Playlist newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Playlist oldItem, @NonNull Playlist newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }

}
