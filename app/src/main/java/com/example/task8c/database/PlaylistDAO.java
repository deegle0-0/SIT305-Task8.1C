package com.example.task8c.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlaylistDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Playlist playlist);

    @Update
    void update(Playlist playlist);

    @Delete
    void delete(Playlist playlist);

    @Query("DELETE FROM playlist_table")
    void deleteAll();

    @Query("SELECT * FROM PLAYLIST_TABLE ORDER BY name ASC")
    LiveData<List<Playlist>> getPlaylist();

}
