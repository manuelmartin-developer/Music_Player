package com.example.tarea3_martin_diaz_jimenez_manuel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class PantallaPrincipal extends AppCompatActivity {
    // Objetos
    ImageView ivMusica, ivVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

        Context contexto = this;

        ivMusica = findViewById(R.id.imgMusica);
        ivVideo = findViewById(R.id.imgVideo);

        /**********REPRODUCTOR MÚSICA********/

        ivMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, ReproductorMusica.class);
                startActivity(intent);
            }
        });

        /***********REPRODUCTO VÍDEO********/

        ivVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contexto, ReproductorVideo.class);
                startActivity(intent);
            }
        });
    }
}