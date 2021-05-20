package com.example.tarea3_martin_diaz_jimenez_manuel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class ReproductorVideo extends AppCompatActivity {
    // Objetos
    VideoView vvVideo;
    MediaController mcControles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_video);
        
        Context contexto = this;
        vvVideo = findViewById(R.id.vvVideo);
        mcControles = new MediaController(contexto);
        vvVideo.setMediaController(mcControles);
        Uri path = Uri.parse("https://manuelmartin.name/the_valley-graham_uheslki.mp4");

        vvVideo.setVideoURI(path);
        vvVideo.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.abrirMusica){
            finish();
            Intent intent = new Intent(this, ReproductorMusica.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}