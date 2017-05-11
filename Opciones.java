package com.example.mazeball;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;

public class Opciones extends AppCompatActivity {
    public static final String PREFS_NAME = "FitxerPrefs";

    Switch bSwitch;
    RadioButton eRB, mRB, hRB;
    Button SaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);

        final SharedPreferences config = getSharedPreferences(PREFS_NAME, 0);
        bSwitch.setEnabled(config.getBoolean("switchSound", true));
        eRB.setChecked(config.getBoolean("easy", true));
        mRB.setChecked(config.getBoolean("medium", false));
        hRB.setChecked(config.getBoolean("hard", false));

        bSwitch= (Switch) findViewById(R.id.switchsonido);
        bSwitch.setEnabled(true);
        eRB = (RadioButton) findViewById(R.id.dif1);
        eRB.setChecked(true);
        eRB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!eRB.isChecked()){
                    eRB.setChecked(true);
                    mRB.setChecked(false);
                    hRB.setChecked(false);
                }
            }
        });
        mRB = (RadioButton) findViewById(R.id.dif2);
        mRB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!mRB.isChecked()){
                    eRB.setChecked(false);
                    mRB.setChecked(true);
                    hRB.setChecked(false);
                }
            }
        });
        hRB = (RadioButton) findViewById(R.id.dif3);
        hRB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!hRB.isChecked()){
                    eRB.setChecked(false);
                    mRB.setChecked(false);
                    hRB.setChecked(true);
                }
            }
        });
        SaveButton = (Button) findViewById(R.id.saveOptions);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = config.edit();
                editor.putBoolean("switchSound", bSwitch.isEnabled());
                editor.putBoolean("easy", eRB.isChecked());
                editor.putBoolean("medium", mRB.isChecked());
                editor.putBoolean("hard", hRB.isChecked());
                editor.commit();

                //Intent i2 = new Intent(Opciones.this, MainActivity.class);
                //startActivity(i2);
            }
        });
    }
}
