package com.example.task8c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.task8c.database.Users;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddUserActivity extends AppCompatActivity {

    EditText fullname,username,password,confirmpassword;

    Button signup;

    private final int request_code =1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        fullname = findViewById(R.id.Full_Name);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmPassword);

        signup = findViewById(R.id.SignupBtn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(TextUtils.isEmpty(fullname.getText())|| TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(confirmpassword.getText())){
                    setResult(RESULT_CANCELED, intent);
                }
                else{
                    String nameValue = username.getText().toString();
                    String passVal = password.getText().toString();

                    intent.putExtra("add_value1", nameValue);
                    intent.putExtra("add_value2",passVal);
                    Log.v("added","user added");
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
    }
}