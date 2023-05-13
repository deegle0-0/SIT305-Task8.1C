package com.example.task8c;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.task8c.ViewModels.PlaylistAdapter;
import com.example.task8c.ViewModels.PlaylistViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyPlaylists extends AppCompatActivity {

    private PlaylistViewModel playlistViewModel;

    PlaylistAdapter listAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_playlists);

        recyclerView = findViewById(R.id.recyclerview);

        playlistViewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);

        listAdapter = new PlaylistAdapter(new PlaylistAdapter.playlistDiff(), this, playlistViewModel);

        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        playlistViewModel.getUsers().observe(this, links -> {
            //update the cached copy of the words in the adapter
            listAdapter.submitList(links);
        });
    }

}