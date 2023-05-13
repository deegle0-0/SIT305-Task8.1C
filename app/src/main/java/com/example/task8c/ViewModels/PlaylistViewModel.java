package com.example.task8c.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.task8c.database.Playlist;
import com.example.task8c.database.PlaylistRepository;
import com.example.task8c.database.UserRepository;
import com.example.task8c.database.Users;

import java.util.List;

public class PlaylistViewModel extends AndroidViewModel {
    PlaylistRepository playlistRepository;

    LiveData<List<Playlist>> playlist;

    public PlaylistViewModel(@NonNull Application application) {
        super(application);
        playlistRepository = new PlaylistRepository(application);
        playlist = playlistRepository.getPlaylist();
    }

    public LiveData<List<Playlist>> getUsers() {
        return playlist;
    }


    /*
        Created a wrapper insert() method that calls the Repository's
        insert() method. In this way, the implementation of insert()
        is encapsulated from the UI.
         */
        public void insert(Playlist playlist1){
            playlistRepository.insert(playlist1);
        }

        public void update(Playlist playlist1){
            playlistRepository.update(playlist1);
        }

        public void delete(Playlist playlist1){
            playlistRepository.delete(playlist1);
        }
    }
