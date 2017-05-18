package com.example.mazeball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Juego extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Bola bola = (Bola)extras.get("bola");
        GameView view = new GameView(this, bola);
        setContentView(view);
    }
}
