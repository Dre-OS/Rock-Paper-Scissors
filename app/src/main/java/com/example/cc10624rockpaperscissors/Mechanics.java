package com.example.cc10624rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mechanics extends AppCompatActivity {

    Button btn_mainMenu, btn_startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanics);

        btn_mainMenu = (Button) findViewById(R.id.btn_mainMenu);
        btn_startGame = (Button) findViewById(R.id.btn_startMainActivity);

        btn_mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mechanics.this, MainMenu.class);
                startActivity(intent);
            }
        });

        btn_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Mechanics.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}