package com.example.mazeball;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button play, options, acercade, salir;
    boolean easy, medium, hard, sound;
    MediaPlayer mediaPlayer;

    public static final String PREFS_NAME = "FitxerPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.theme);
        mediaPlayer.start();

        play = (Button) findViewById(R.id.play);

        options = (Button) findViewById(R.id.opciones);

        acercade = (Button) findViewById(R.id.acercade);

        salir = (Button) findViewById(R.id.salir);

    }

    @Override
    public void onResume(){
        final SharedPreferences config = getSharedPreferences(PREFS_NAME, 0);
        sound = config.getBoolean("switchSound", true);
        easy = config.getBoolean("easy", true);
        medium = config.getBoolean("medium", false);
        hard = config.getBoolean("hard", false);

        // Si seleccionamos que queremos musica en Opciones, se activara la musica
        // del juego, y hasta que no seleccionemos lo contrario, no parara
        if(sound) {
            if(!mediaPlayer.isPlaying()){
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(100,100);
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        }else{
            if(mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }

        // IMPLEMENTAMOS LOS onClick() A LOS BOTONES

        // El boton play redireccionara al usuario a la partida, habiendo
        // seleccionado un laberinto anteriormente (estando el 1 por defecto)
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = i = new Intent(MainActivity.this, Maze.class);
                if(easy){
                    i.putExtra("level", "Mazo 1");
                }else if(medium){
                    i.putExtra("level","Mazo 2");
                }/*else{
                    //i = new Intent(MainActivity.this, Level3.class);
                }*/
                startActivity(i);
            }
        });

        // El boton de opciones redireccionara a la pantalla de Opciones
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Opciones.class);
                startActivity(i);
            }
        });

        // El boton de acercade redireccionara a la pantalla de Acerca De
        acercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(i);
            }
        });

        // El boton de salir hace que se apague la aplicacion
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        super.onResume();
    }

}