package com.example.task8c.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    UserDAO userDAO;

    LiveData<List<Users>> userList;

    boolean temp;

    public UserRepository(Application application)
    {
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        userDAO = db.userDAO();
        userList = userDAO.getUsers();

    }

    public LiveData<List<Users>> getUserList() {
        return userList;
    }

    public void insert(Users users){
        UserRoomDatabase.databaseWriteExecutor.execute(()->{
            userDAO.insert(users);
        });
    }

    public void update(Users users){
        UserRoomDatabase.databaseWriteExecutor.execute(()->{
            userDAO.update(users);
        });
    }


    public void delete(Users users){
        UserRoomDatabase.databaseWriteExecutor.execute(()->{
            userDAO.delete(users);
        });
    }

    public boolean loginUser(String user,String pass){
        UserRoomDatabase.databaseWriteExecutor.execute(()->{
            temp = userDAO.loginUser(user, pass);
        });

        return temp;
    }

}
