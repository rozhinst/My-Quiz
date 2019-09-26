package com.example.randomquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlayAgain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);
        Button playAgain = findViewById(R.id.play_again);

        playAgain.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent(PlayAgain.this, MainActivity.class));
            finish();
        });

    }
}
