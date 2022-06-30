package com.example.basic_beginning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button photoManager,musicPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        photoManager=findViewById ( R.id.photoManager );
        musicPlayer=findViewById ( R.id.music_player );

    }

    public void photoManagerClick(View view) {
        Intent i=new Intent (MainActivity.this,photoManager.class);
        startActivity ( i );
    }

    public void musicPlayer(View view) {
        Intent i=new Intent (MainActivity.this, musicPlayer.class);
        startActivity ( i );
    }


    public void newsApp(View view) {
        Intent i=new Intent (MainActivity.this, newsApp.class);
        startActivity ( i );
    }

    public void unInstall(View view) {
        Intent i=new Intent (MainActivity.this, unInstaller.class);
        startActivity ( i );
    }
}