package com.example.task8c.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlaylistRepository {

    PlaylistDAO playlistDAO;

    LiveData<List<Playlist>> playlist;

    public PlaylistRepository(Application application) {
        PlaylistRoomDatabase db = PlaylistRoomDatabase.getDatabase(application);
        playlistDAO = db.playlistDAO();
        playlist = playlistDAO.getPlaylist();
    }

    public LiveData<List<Playlist>> getPlaylist(){ return playlist;}

    public void insert(Playlist playlist1)
    {
        PlaylistRoomDatabase.databaseWriteExecutor.execute(()->{
            playlistDAO.insert(playlist1);
        });
    }
    public void update(Playlist playlist1)
    {
        PlaylistRoomDatabase.databaseWriteExecutor.execute(()->{
            playlistDAO.update(playlist1);
        });
    }
    public void delete(Playlist playlist1)
    {
        PlaylistRoomDatabase.databaseWriteExecutor.execute(()->{
            playlistDAO.delete(playlist1);
        });
    }
}
