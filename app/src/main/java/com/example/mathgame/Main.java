package com.example.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Main extends AppCompatActivity {

    Button btnPlay;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        btnExit = findViewById(R.id.btnExit);

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(this, Difficulty.class);
            this.startActivity(intent);
        });

        btnExit.setOnClickListener(v -> this.finishAffinity());
    }
}
