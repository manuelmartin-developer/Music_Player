package com.example.tarea3_martin_diaz_jimenez_manuel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class ReproductorMusica extends AppCompatActivity {
    // Objetos
    ListView listadoCanciones;
    ImageView ivCancion;
    MediaPlayer reproductor;
    String cancion="";
    int cancionSeleccionada, posicion=0, duracion=0;
    Button btPlay, btPausa, btParar, btAvanzar, btRetroceder;
    FloatingActionButton btCambiarCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_musica);

        // Asignación de Objetos
        Context contexto = this;
        listadoCanciones = findViewById(R.id.lvCanciones);
        ivCancion = findViewById(R.id.ivCancion);
        btPlay = findViewById(R.id.btPlay);
        btPausa = findViewById(R.id.btPausar);
        btParar = findViewById(R.id.btParar);
        btAvanzar = findViewById(R.id.btAvanzar);
        btRetroceder = findViewById(R.id.btRetroceder);
        btCambiarCancion = findViewById(R.id.btCambiarCancion);


        // Deshabilitamos el botón de Play, ya que los archivos de audio se inician desde el listView
        // se volverá a habilitar cuando se pulse el botón pause, ya que su única funcionalidad será
        // reiniciar el audio una vez haya sido pausado.
        // Del mismo modo, deshabilitaremos el botón de pausa y de Play y los de avance.
        // Se irán habilitando y deshabilitando que adquieran o pierdan funcionalidad, respectivamente.

        deshabilitarBotones();

        /**********************LISTVIEW***********************/
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(contexto,
                R.array.listadoCanciones, android.R.layout.simple_list_item_1);
        listadoCanciones.setAdapter(adaptador);

        listadoCanciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Controlamos la canción que se selecciona
                if (position == 0){
                    ivCancion.setImageResource(R.drawable.mozart);
                    cancionSeleccionada = R.raw.mozart;
                    cancion = "Mozart";


                }else if (position == 1){
                    ivCancion.setImageResource(R.drawable.bach);
                    cancionSeleccionada = R.raw.bach;
                    cancion = "Bach";

                }else if (position == 2){
                    ivCancion.setImageResource(R.drawable.beethoven);
                    cancionSeleccionada = R.raw.beethoven;
                    cancion = "Beethoven";

                }
                //Creamos el reproductor y le asignamos la canción seleccionada

                reproductor = MediaPlayer.create(contexto, cancionSeleccionada);
                reproductor.start();
                duracion = reproductor.getDuration();
                Toast.makeText(contexto, "Reproduciendo " + cancion, Toast.LENGTH_SHORT).show();
                    // Deshabilitamos el listado de canciones durante la reproducción
                listadoCanciones.setEnabled(false);
                listadoCanciones.setAlpha((float) 0.2);
                    // Habilitamos los botones

                habilitarBotones();
            }
        });

        /**********************BOTONES********************/

        /**PLAY**/

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(reproductor != null){
                    reproductor.start();

                    // Habilitamos de nuevo el botón de pausa y deshabilitamos el Play
                    btPausa.setEnabled(true);
                    btPausa.setAlpha(1);
                    btPlay.setEnabled(false);
                    btPlay.setAlpha((float) 0.2);
                    listadoCanciones.setEnabled(false);
                    listadoCanciones.setAlpha((float) 0.2);
                }
            }
        });

        /**PAUSA**/

        btPausa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(reproductor != null){
                    reproductor.pause();
                    listadoCanciones.setEnabled(true);
                    listadoCanciones.setAlpha(1);

                    // Volvemos a habilitar o deshabilitar los botones correspondientes
                    listadoCanciones.setEnabled(false);
                    listadoCanciones.setAlpha((float) 0.2);
                    btPausa.setEnabled(false);
                    btPausa.setAlpha((float) 0.2);
                    btPlay.setEnabled(true);
                    btPlay.setAlpha(1);

                    Toast.makeText(contexto, "Reproductor Pausado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**PARAR**/

        btParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(reproductor != null){
                    reproductor.stop();
                    try {
                        reproductor.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(contexto, "Reproductor Detenido", Toast.LENGTH_SHORT).show();

                    // Habilitamos el botón de Play y deshabilitamos el de pause, ya que desde el
                    // estado Stopped no podremos acceder al estado Paused
                    btPlay.setEnabled(true);
                    btPlay.setAlpha(1);
                    btPausa.setEnabled(false);
                    btPausa.setAlpha((float) 0.2);

                }
            }
        });

        /**AVANZAR 10''**/

        btAvanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(reproductor !=null){
                    posicion += 10000;
                    Toast.makeText(contexto, "Avanzace 10''", Toast.LENGTH_SHORT).show();
                    reproductor.seekTo(posicion);
                }
            }
        });

        /**RETROCEDER 10''**/

        btRetroceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(reproductor != null){
                    posicion -= 10000;
                    Toast.makeText(contexto, "Retroceso 10''", Toast.LENGTH_SHORT).show();
                    reproductor.seekTo(posicion);
                }
            }
        });

        /**CAMBIAR CANCION**/

        btCambiarCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detenemos y reseteamos el reproductor

                reproductor.reset();
                deshabilitarBotones();
                // Habilitamos de nuevo los elementos necesarios

                ivCancion.setImageResource(R.drawable.vacio);
                listadoCanciones.setEnabled(true);
                listadoCanciones.setAlpha(1);
                btCambiarCancion.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_musica, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.abrirVideo){

            // Llamamos al método onDestroy() que libera el recurso y lanzamos la Activity
                finish();

                Intent intent = new Intent(this, ReproductorVideo.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    // Sobrescribimos el método OnDestroy()
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }

    // Método que deshabilita todos los botones
    public void deshabilitarBotones(){
        btPlay.setEnabled(false);
        btPlay.setAlpha((float) 0.2);
        btPausa.setEnabled(false);
        btPausa.setAlpha((float) 0.2);
        btParar.setEnabled(false);
        btParar.setAlpha((float) 0.2);
        btAvanzar.setEnabled(false);
        btAvanzar.setAlpha((float) 0.2);
        btRetroceder.setEnabled(false);
        btRetroceder.setAlpha((float) 0.2);
        btCambiarCancion.setVisibility(View.GONE);
    }

    // Método que habilita todos los botones

    public void habilitarBotones(){
        btPausa.setEnabled(true);
        btPausa.setAlpha(1);
        btParar.setEnabled(true);
        btParar.setAlpha(1);
        btAvanzar.setEnabled(true);
        btAvanzar.setAlpha(1);
        btRetroceder.setEnabled(true);
        btRetroceder.setAlpha(1);
        btCambiarCancion.setVisibility(View.VISIBLE);
    }

}