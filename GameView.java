package com.example.mazeball;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    private int ancho, alto, anchoDeLinea;

    private int tamanoX, tamanoY;

    float anchoCelda, altoCelda;

    float anchoTotalCelda, altoTotalCelda;

    private int finalX, finalY;
    private Bola bola;
    private Activity context;
    private Paint linea, rojo, fondo;

    public GameView(Context context, Bola bola) {
        super(context);
        this.context = (Activity)context;
        this.bola = bola;
        finalX = bola.getFinalX();
        finalY = bola.getFinalY();

        tamanoX = bola.getAncho();
        tamanoY = bola.getAlto();

        linea = new Paint();
        linea.setColor(getResources().getColor(R.color.line));

        this.rojo = new Paint();
        this.rojo.setColor(getResources().getColor(R.color.position));

        fondo = new Paint();
        fondo.setColor(getResources().getColor(R.color.game_bg));

        setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    protected void onSizeChanged(int ancho, int alto, int anchoAnterior, int altoAnterior) {
        ancho = (ancho < alto) ? ancho : alto;
        alto = ancho;

        anchoDeLinea = 1;

        anchoCelda = (ancho - ((float)tamanoX*anchoDeLinea)) / tamanoX;
        anchoTotalCelda = anchoCelda+anchoDeLinea;

        altoCelda = (alto - ((float)tamanoY*anchoDeLinea)) / tamanoY;
        altoTotalCelda = altoCelda+anchoDeLinea;

        rojo.setTextSize(altoCelda*0.75f);
        super.onSizeChanged(ancho, alto, anchoAnterior, altoAnterior);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        canvas.drawRect(0, 0, ancho, alto, fondo);

        boolean[][] horizontales = bola.getLineasHorizontales();
        boolean[][] verticales = bola.getLineasVerticales();

        for(int i = 0; i < tamanoX; i++) {
            for(int j = 0; j < tamanoY; j++){
                float x = j * anchoTotalCelda;
                float y = i * altoTotalCelda;

                if(j < tamanoX - 1 && verticales[i][j]) {
                    canvas.drawLine(x + anchoCelda,
                            y,
                            x + anchoCelda,
                            y + altoCelda,
                            linea);
                }

                if(i < tamanoY - 1 && horizontales[i][j]) {
                    canvas.drawLine(x,
                            y + altoCelda,
                            x + anchoCelda,
                            y + altoCelda,
                            linea);
                }
            }
        }
        int actualX = bola.getActualX(), actualY = bola.getActualY();



        canvas.drawCircle((actualX * anchoTotalCelda)+(anchoCelda/2),
                (actualY * altoTotalCelda)+(anchoCelda/2),
                (anchoCelda*0.45f),
                rojo);

        canvas.drawText("F",
                (finalX * anchoTotalCelda)+(anchoCelda*0.25f),
                (finalY * altoTotalCelda)+(altoCelda*0.75f),
                rojo);
    }


    @Override
    public boolean onTouchEvent (MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent evt) {
        boolean moved;
        switch(keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                moved = bola.mover(Bola.UP);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                moved = bola.mover(Bola.DOWN);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                moved = bola.mover(Bola.RIGHT);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                moved = bola.mover(Bola.LEFT);
                break;
            default:
                return super.onKeyDown(keyCode,evt);
        }

        if(moved) {
            invalidate();
            if(bola.seHaAcabadoElJuego()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(context.getText(R.string.finished_title));

                LayoutInflater inflater = context.getLayoutInflater();

                View view = inflater.inflate(R.layout.finish, null);
                builder.setView(view);

                View closeButton =view.findViewById(R.id.closeGame);
                closeButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View clicked) {
                        if(clicked.getId() == R.id.closeGame) {
                            context.finish();
                        }
                    }
                });
                AlertDialog finishDialog = builder.create();
                finishDialog.show();
            }
        }
        return true;
    }
}
