package com.example.cc10624rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button btn_startMainActivity, btn_startMechs, btn_quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btn_startMainActivity = (Button) findViewById(R.id.btn_startMainActivity);
        btn_startMechs = (Button) findViewById(R.id.btn_startMechs);
        btn_quit = (Button) findViewById(R.id.btn_quit);
        btn_startMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btn_startMechs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Mechanics.class);
                startActivity(intent);
            }
        });

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }
}