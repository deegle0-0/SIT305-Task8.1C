package com.example.task8c;

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

import com.example.task8c.ViewModels.PlaylistViewModel;
import com.example.task8c.ViewModels.UserViewModel;
import com.example.task8c.database.Playlist;
import com.example.task8c.database.Users;

public class AppScreen extends AppCompatActivity {

    EditText youtubeLink;
    Button play,add,view;

    PlaylistViewModel playlistViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_screen);

        youtubeLink = findViewById(R.id.givenLink);
        play = findViewById(R.id.playBtn);
        add = findViewById(R.id.AddBtn);
        view = findViewById(R.id.playlistBtn);

        playlistViewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppScreen.this, MyPlaylists.class);
                startActivity(intent);
                finish();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(youtubeLink.getText()))
                {
                    Toast.makeText(AppScreen.this, "Enter Link to play ", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(AppScreen.this,PlayActivity.class);
                    intent.putExtra("youtube_link",youtubeLink.getText().toString());
                    startActivity(intent);
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(youtubeLink.getText()))
                {
                    Toast.makeText(AppScreen.this, "Enter Link to add to playlist", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(AppScreen.this, "Added to playlist", Toast.LENGTH_LONG).show();

                    Playlist playlist = new Playlist(0, youtubeLink.getText().toString());
                    playlistViewModel.insert(playlist);
                    Log.v("link added","true");
                }

            }
        });
    }
}