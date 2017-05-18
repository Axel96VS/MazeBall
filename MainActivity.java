package com.example.mazeball;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play, options, acercade, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] levels = {"Bola 1", "Bola 2", "Bola 3"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.levelSelect));
                builder.setItems(levels, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Intent game = new Intent(MainActivity.this, Juego.class);
                        Bola bola = Laberintos.getLaberinto(item+1);
                        game.putExtra("bola", bola);
                        startActivity(game);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        options = (Button) findViewById(R.id.opciones);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Opciones.class);
                startActivity(i);
            }
        });

        acercade = (Button) findViewById(R.id.acercade);
        acercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(i);
            }
        });

        salir = (Button) findViewById(R.id.salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
