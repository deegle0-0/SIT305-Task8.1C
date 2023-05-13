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
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Users users);

    @Update
    void update(Users users);

    @Delete
    void delete(Users users);

    @Query("DELETE FROM users_table")
    void deleteAll();

    @Query("SELECT * FROM users_table ORDER BY name ASC")
    LiveData<List<Users>> getUsers();

    @Query("SELECT name,pass FROM users_table WHERE name=:user and pass=:pass")
    boolean loginUser(String user, String pass);

}
