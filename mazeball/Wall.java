package com.example.mazeball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Wall extends View {
    public float inicioX, finalX;
    public float inicioY, finalY;
    private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Wall(Context context, float inicioX, float inicioY, float finalX, float finalY) {
        super(context);
        mPaint.setColor(0xFFFFFFFF); // Color blanco para el muro
        mPaint.setStrokeWidth(20); // Anchura del muro
        this.inicioX = inicioX; // Posicion inicial X del muro
        this.inicioY = inicioY; // Posicion inicial Y del muro
        this.finalX = finalX; // Posicion inicial X del muro
        this.finalY = finalY; // Posicion final Y del muro
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Se dibuja una linea en las coordenadas indicadas
        canvas.drawLine(inicioX, inicioY, finalX, finalY, mPaint);
    }

}