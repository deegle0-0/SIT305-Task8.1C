package com.example.task8c;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task8c.ViewModels.UserViewModel;
import com.example.task8c.database.Users;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login,signup;

    private UserViewModel usersViewModel;
    boolean loginBool= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.LoginButton);
        signup = findViewById(R.id.SignUpButton);

        usersViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,AddUserActivity.class);

                startActivityForResult(myIntent,1);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(username.getText())) {
                    loginBool = usersViewModel.loginUser(username.getText().toString(), password.getText().toString());

                    if (loginBool) {
                        Log.v("Login Status", "Success");
                        Intent myIntent = new Intent(MainActivity.this, AppScreen.class);
                        startActivity(myIntent);
                    } else {
                        Log.v("Login Status", "Failed");
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "bro enter details first", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Users student = new Users(0, data.getStringExtra("add_value1"),
                        data.getStringExtra("add_value2"));
                usersViewModel.insert(student);
            }
            else {
                Toast.makeText(MainActivity.this, "Not SAVED", Toast.LENGTH_SHORT).show();
            }
        }

    }

}