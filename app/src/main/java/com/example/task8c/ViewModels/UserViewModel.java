package com.example.task8c.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.task8c.database.UserRepository;
import com.example.task8c.database.Users;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    UserRepository userRepository;

    LiveData<List<Users>> users;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        users = userRepository.getUserList();
    }

    public LiveData<List<Users>> getUsers() {
        return users;
    }

    public void insert(Users user){
        userRepository.insert(user);
    }

    public void update(Users user){
        userRepository.update(user);
    }

    public void delete(Users user){
        userRepository.delete(user);
    }

    public boolean loginUser(String user,String pass){
        if(userRepository.loginUser(user,pass))
        {
            return true;
        }
        return true;
    }
}
