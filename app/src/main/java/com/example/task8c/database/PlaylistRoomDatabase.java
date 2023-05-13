package com.example.task8c.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Playlist.class}, version = 1,exportSchema = false)
public abstract class PlaylistRoomDatabase extends RoomDatabase {

    public abstract PlaylistDAO playlistDAO();

    private static volatile PlaylistRoomDatabase INSTANCE;

    static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PlaylistRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (PlaylistRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PlaylistRoomDatabase.class, "playList_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                PlaylistDAO dao = INSTANCE.playlistDAO();
                dao.deleteAll();
            });
        }
    };
}
