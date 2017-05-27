package com.example.mazeball;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;

public class Maze extends Activity {

    Ball mBall = null;
    Wall[] muros;
    Handler RedrawHandler = new Handler();
    Timer mTmr = null;
    TimerTask mTsk = null;
    int mScrWidth, mScrHeight;
    android.graphics.PointF mBallPos, mBallSpd;
    float muroH, muroV;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Oculta la barra
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Permite que la app se ejecute en pantalla completa y con la pantalla siempre encendida
        getWindow().setFlags(0xFFFFFFFF, LayoutParams.FLAG_FULLSCREEN | LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Indicamos el layout que usaremos cuando inicialicemos la partida
        setContentView(R.layout.activity_level1);

        // Recogemos el intent que enviamos desde el MainActivity y lo guardamos
        // en una variable para poder elegir un mazo segun lo que recibimos
        final Intent intent = getIntent();
        String vLevel = intent.getExtras().getString("level");

        // Creamos una variable FrameLayout indicando que contendra el layout de la partida
        // Se usara para ir anadiendo los elementos Bola/Muro a este layout
        final FrameLayout mainView = (android.widget.FrameLayout) findViewById(R.id.level1);

        // Cogemos las dimensiones de la pantalla
        Display display = getWindowManager().getDefaultDisplay();
        mScrWidth = display.getWidth();
        mScrHeight = display.getHeight();

        // Elegimos el mazo segun lo que enviamos
        if (vLevel.equals("Mazo 1")) {
            inicializarMaze(mainView, 1);
            generarLaberinto(mainView, 1);
        } else if (vLevel.equals("Mazo 2")) {
            inicializarMaze(mainView, 2);
            generarLaberinto(mainView, 1);
        }

        acelerometro();

    }//OnCreate



    // Metodo que inicializa las variables de la posicion/velocidad/tamano de la bola y
    // en cuantas filas y columnas estara dividida la pantalla.
    // Lo hacemos asi por si en algun mapa queremos que la bola vaya mas rapido, sea mas grande, etc..
    public void inicializarMaze(FrameLayout mainView, int maze) {
        if (maze == 1) {
            // Inicializamos las variables de la posicion de la bola
            mBallPos = new android.graphics.PointF();
            mBallPos.x = mScrWidth / 12;
            mBallPos.y = mScrHeight / 18;

            // Inicializamos las variables de la velocidad de la bola
            mBallSpd = new android.graphics.PointF();
            mBallSpd.x = 0;
            mBallSpd.y = 0;

            // Creamos la bola
            mBall = new Ball(this, mBallPos.x, mBallPos.y, 30);
            // Anado el elemento a la vista del juego
            mainView.addView(mBall);
            // El metodo invalidate() llama al metodo onDraw()
            mBall.invalidate();


            // Inicializamos las variables muroH y muroV a el ancho y alto de la pantalla
            // dividido entre las filas y columnas que nosotros queremos, para que los mapas
            // queden igual en todas las resoluciones y no salgan movidos
            muroH = mScrWidth / 5;
            muroV = mScrHeight / 6;
        } else if (maze == 2) {
            // Inicializamos las variables de la posicion de la bola
            mBallPos = new android.graphics.PointF();
            mBallPos.x = mScrWidth / 12;
            mBallPos.y = mScrHeight / 18;

            // Inicializamos las variables de la velocidad de la bola
            mBallSpd = new android.graphics.PointF();
            mBallSpd.x = 0;
            mBallSpd.y = 0;

            // Creamos la bola
            mBall = new Ball(this, mBallPos.x, mBallPos.y, 30);
            // Anado el elemento a la vista del juego
            mainView.addView(mBall);
            // El metodo invalidate() llama al metodo onDraw()
            mBall.invalidate();


            // Inicializamos las variables muroH y muroV a el ancho y alto de la pantalla
            // dividido entre las filas y columnas que nosotros queremos, para que los mapas
            // queden igual en todas las resoluciones y no salgan movidos
            muroH = mScrWidth / 5;
            muroV = mScrHeight / 6;
        } else {
            // Inicializamos las variables de la posicion de la bola
            mBallPos = new android.graphics.PointF();
            mBallPos.x = mScrWidth / 12;
            mBallPos.y = mScrHeight / 18;

            // Inicializamos las variables de la velocidad de la bola
            mBallSpd = new android.graphics.PointF();
            mBallSpd.x = 0;
            mBallSpd.y = 0;

            // Creamos la bola
            mBall = new Ball(this, mBallPos.x, mBallPos.y, 30);
            // Anado el elemento a la vista del juego
            mainView.addView(mBall);
            // El metodo invalidate() llama al metodo onDraw()
            mBall.invalidate();


            // Inicializamos las variables muroH y muroV a el ancho y alto de la pantalla
            // dividido entre las filas y columnas que nosotros queremos, para que los mapas
            // queden igual en todas las resoluciones y no salgan movidos
            muroH = mScrWidth / 5;
            muroV = mScrHeight / 6;
        }
    }



    // Metodo que genera el laberinto segun la eleccion del usuario
    public void generarLaberinto( FrameLayout mainView, int maze) {
        if (maze == 1) {
            muros = new Wall[]{
                    //Creamos las paredes de la pantalla

                    // PARED DE IZQUIERDA
                    new Wall(this, 0, 0, 0, mScrHeight),
                    // PARED DE ARRIBA
                    new Wall(this, 0, 0, mScrWidth, 0),
                    // PARED DE DERECHA
                    new Wall(this, mScrWidth, 0, mScrWidth, mScrHeight),
                    // PARED DE IZQUIERDA
                    new Wall(this, 0, mScrHeight, 1080, mScrHeight),


                    // Creamos el dibujo del mapa
                    new Wall(this, muroH, 0, muroH, muroV),
                    new Wall(this, muroH * 4, 0, muroH * 4, muroV),
                    new Wall(this, muroH, muroV, muroH * 2, muroV),
                    new Wall(this, muroH * 2, muroV, muroH * 3, muroV),
                    new Wall(this, muroH * 4, muroV, muroH * 5, muroV),
                    new Wall(this, 0, muroV * 2, muroH, muroV * 2),
                    new Wall(this, muroH, muroV * 2, muroH * 2, muroV * 2),
                    new Wall(this, muroH * 2, muroV * 2, muroH * 3, muroV * 2),
                    new Wall(this, muroH * 4, muroV * 2, muroH * 5, muroV * 2),
                    new Wall(this, muroH, muroV * 2, muroH, muroV * 3),
                    new Wall(this, muroH * 2, muroV * 2, muroH * 2, muroV * 3),
                    new Wall(this, muroH * 3, muroV * 2, muroH * 3, muroV * 3),
                    new Wall(this, muroH * 4, muroV * 2, muroH * 4, muroV * 3),
                    new Wall(this, 0, muroV * 3, muroH, muroV * 3),
                    new Wall(this, muroH * 2, muroV * 3, muroH * 3, muroV * 3),
                    new Wall(this, muroH * 4, muroV * 3, muroH * 4, muroV * 4),
                    new Wall(this, 0, muroV * 4, muroH, muroV * 4),
                    new Wall(this, muroH, muroV * 4, muroH * 2, muroV * 4),
                    new Wall(this, muroH * 3, muroV * 4, muroH * 4, muroV * 4),
                    new Wall(this, muroH * 3, muroV * 4, muroH * 3, muroV * 5),
                    new Wall(this, muroH * 4, muroV * 4, muroH * 4, muroV * 4),
                    new Wall(this, 0, muroV * 5, muroH, muroV * 5),
                    new Wall(this, muroH * 3, muroV * 5, muroH * 4, muroV * 5)
            };
        } else if (maze == 2) {
            muros = new Wall[]{
                    //Creamos las paredes de la pantalla

                    // PARED DE IZQUIERDA
                    new Wall(this, 0, 0, 0, mScrHeight),
                    // PARED DE ARRIBA
                    new Wall(this, 0, 0, mScrWidth, 0),
                    // PARED DE DERECHA
                    new Wall(this, mScrWidth, 0, mScrWidth, mScrHeight),
                    // PARED DE IZQUIERDA
                    new Wall(this, 0, mScrHeight, 1080, mScrHeight),

                    // Creamos el dibujo del mapa
                    new Wall(this, muroH, 0, muroH, muroV * 2), // Vertical
                    new Wall(this, muroH - 10, muroV * 2, muroH * 3, muroV * 2), // Horizontal
                    new Wall(this, muroH * 3, muroV, muroH * 3, (muroV * 2) + 10), // Vertical
                    new Wall(this, muroH * 2, muroV, (muroH * 3) + 10, muroV), // Horizontal
                    new Wall(this, 0, muroV * 3, muroH * 4, muroV * 3), // Horizontal
                    new Wall(this, muroH * 4, muroV, muroH * 4, (muroV * 3) + 10), // Vertical
                    new Wall(this, muroH, muroV * 4, muroH * 5, muroV * 4), // Horizontal
                    new Wall(this, muroH, muroV * 5, muroH * 4, muroV * 5), // Horizontal
                    new Wall(this, muroH, (muroV * 5) - 10, muroH, muroV * 6) // Vertical
            };
        } else {

        }

        // Recorremos la matriz de muros
        for (int i = 0; i < muros.length; i++) {
            // Los anadimos al layout
            mainView.addView(muros[i]);
            // Los dibujamos, el metodo invalidate llama al metodo onDraw()
            muros[i].invalidate();
        }
    }



    // Metodo que cambia la velocidad y la direccion de la bola segun la
    // inclinacion del telefono
    public void acelerometro() {
        //Listener para el acelerómetro
        ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).registerListener(
                new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent event) {
                        //Cambia la velocidad de la bola dependiendo de la inclinación del telefono
                        mBallSpd.x = -event.values[0];
                        mBallSpd.y = event.values[1];
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    } //Esto no hace nada
                }, ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);
    }



    @Override
    public void onResume() {

        // Creamos un timer para mover la bola a una nueva posicion
        mTmr = new Timer();
        mTsk = new TimerTask() {
            public void run() {

                android.util.Log.d("TiltBall", "Timer Hit - " + mBallPos.x + ":" + mBallPos.y);

                // Movemos la bola basandome en la velocidad actual
                mBallPos.x += mBallSpd.x;
                mBallPos.y += mBallSpd.y;

                anadirColisiones();

                // Actualizamos la posicion anterior de la bola a la posicion actual
                mBall.x = mBallPos.x;
                mBall.y = mBallPos.y;

                //Redibujamos la bola con el metodo invalidate()
                RedrawHandler.post(new Runnable() {
                    public void run() {
                        mBall.invalidate();
                    }
                });
            }
        };// TimerTask
        mTmr.schedule(mTsk, 10, 10); //start timer
        super.onResume();
    }//onResume



    // Metodo que anade las colisiones a los muros
    public void anadirColisiones() {
        // Anadimos colisiones a los muros (Casi morimos con esto)
        for (int i = 0; i < muros.length; i++) {
            // DIRECCION ARRIBA
            // Si la posicion de Y de la bola es mayor a la posicion inicial de Y del muro
            if (mBallPos.y > muros[i].inicioY) {
                // Si la bola va en direccion hacia abajo (mBallSpd.y < 0)
                // y la posicion de la bola es menor que su radio + la posicion inicial de Y del muro
                // y si la posicion de X de la bola es mayor a la posicion inicial de X del muro
                // y la posicion de X de la bola es menor a la posicion final de X del muro
                if (mBallSpd.y < 0 && mBallPos.y < muros[i].inicioY + mBall.radio() && (mBallPos.x > muros[i].inicioX && mBallPos.x < muros[i].finalX)) {
                    // Dejamos la bola en esa posicion. En este caso, se quedaría en la parte superior del muro
                    mBallPos.y = muros[i].finalY + mBall.radio();
                }
            }

            // Los if de abajo son identicos al de arriba, cambia Y por X y los simbolos > por < y viceversa

            // DIRECCION ABAJO
            if (mBallPos.y < muros[i].inicioY) {
                if (mBallSpd.y > 0 && mBallPos.y > muros[i].inicioY - mBall.radio() && (mBallPos.x > muros[i].inicioX && mBallPos.x < muros[i].finalX)) {
                    mBallPos.y = muros[i].finalY - mBall.radio();
                }
            }
            // DIRECCION IZQUIERDA
            if (mBallPos.x > muros[i].inicioX) {
                if (mBallSpd.x < 0 && mBallPos.x < muros[i].inicioX + mBall.radio() && (mBallPos.y > muros[i].inicioY && mBallPos.y < muros[i].finalY)) {
                    mBallPos.x = muros[i].finalX + mBall.radio();
                }
            }
            // DIRECCION DERECHA
            if (mBallPos.x < muros[i].inicioX) {
                if (mBallSpd.x > 0 && mBallPos.x > muros[i].inicioX - mBall.radio() && (mBallPos.y > muros[i].inicioY && mBallPos.y < muros[i].finalY)) {
                    mBallPos.x = muros[i].finalX - mBall.radio();
                }
            }
        }
    }



    //Listener para el botón de menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Exit");
        return super.onCreateOptionsMenu(menu);
    }



    //Listener para cuando se clica a menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle() == "Exit")
            finish();
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onPause() {
        mTmr.cancel();
        mTmr = null;
        mTsk = null;
        super.onPause();
    }



    //Listener para cambio de la configuración.
    //Se utiliza cuando el usuario inclina tanto el teléfono que se puede poner en modo Landscape(horizontal)
    //Esto hace que siga estando en Portrait(vertical)
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}