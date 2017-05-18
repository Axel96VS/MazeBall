package com.example.mazeball;

import java.io.Serializable;

public class Bola implements Serializable{

    public static final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;

    private boolean[][] lineasVerticales;
    private boolean[][] lineasHorizontales;
    private int tamanoX, tamanoY;
    private int actualX, actualY;
    private int finalX, finalY;
    private boolean juegoCompletado;

    public boolean mover(int direction) {
        boolean moved = false;
        if(direction == UP) {
            if(actualY != 0 && !lineasHorizontales[actualY-1][actualX]) {
                actualY--;
                moved = true;
            }
        }
        if(direction == DOWN) {
            if(actualY != tamanoY-1 && !lineasHorizontales[actualY][actualX]) {
                actualY++;
                moved = true;
            }
        }
        if(direction == RIGHT) {
            if(actualX != tamanoX-1 && !lineasVerticales[actualY][actualX]) {
                actualX++;
                moved = true;
            }
        }
        if(direction == LEFT) {
            if(actualX != 0 && !lineasVerticales[actualY][actualX-1]) {
                actualX--;
                moved = true;
            }
        }
        if(moved) {
            if(actualX == finalX && actualX == finalY) {
                juegoCompletado = true;
            }
        }
        return moved;
    }

    public boolean seHaAcabadoElJuego() {
        return juegoCompletado;
    }

    public int getAncho() {
        return tamanoX;
    }

    public int getAlto() {
        return tamanoY;
    }

    public void setPosicionInicial(int x, int y) {
        actualX = x;
        actualY = y;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public void setPosicionFinal(int x, int y) {
        finalX = x;
        finalY = y;
    }

    public int getActualX() {
        return actualX;
    }

    public int getActualY() {
        return actualY;
    }

    public boolean[][] getLineasHorizontales() {
        return lineasHorizontales;
    }

    public void setLineasHorizontales(boolean[][] lines) {
        lineasHorizontales = lines;
        tamanoX = lineasHorizontales[0].length;
    }

    public boolean[][] getLineasVerticales() {
        return lineasVerticales;
    }

    public void setLineasVerticales(boolean[][] lines) {
        lineasVerticales = lines;
        tamanoY = lineasVerticales.length;
    }
}
