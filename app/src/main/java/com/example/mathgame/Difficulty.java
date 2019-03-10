package com.example.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Difficulty extends AppCompatActivity {


    Button btnEasy;
    Button btnNormal;
    Button btnHard;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_difficulty);

        btnEasy = findViewById(R.id.btnEasy);
        btnNormal = findViewById(R.id.btnNormal);
        btnHard = findViewById(R.id.btnHard);
        btnBack = findViewById(R.id.btnBack);

        Intent intentPlay = new Intent(this, Play.class);;
        Intent intentMain = new Intent(this, Main.class);

        btnEasy.setOnClickListener(v -> {
            intentPlay.putExtra("difficulty", "Easy");
            this.startActivity(intentPlay);
        });

        btnNormal.setOnClickListener(v -> {
            intentPlay.putExtra("difficulty", "Normal");
            this.startActivity(intentPlay);
        });

        btnHard.setOnClickListener(v -> {
            intentPlay.putExtra("difficulty", "Hard");
            this.startActivity(intentPlay);
        });

        btnBack.setOnClickListener(v -> {
            this.startActivity(intentMain);
        });
    }
}
