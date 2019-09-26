package com.example.randomquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.randomquiz.MainActivity.QUESTIONS_ARRAY_EXTRA;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private ArrayList questions;
    private ArrayList<Integer> randNumbers;
    private int counter;
    private int arrayCnt;
    private Question quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        textView = findViewById(R.id.show_question);
        Button trueButton = findViewById(R.id.true_button);
        Button falseButton = findViewById(R.id.false_button);
        randNumbers = new ArrayList<>();
        arrayCnt = 0;

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            questions = (ArrayList) extra.getSerializable(QUESTIONS_ARRAY_EXTRA);
        }

        if (questions != null) {
            for (int i = 0; i < questions.size(); i++)
                randNumbers.add(i);
        }
        Collections.shuffle(randNumbers);

        quest = (Question) questions.get(randNumbers.get(arrayCnt));
        textView.setText(quest.getQuestion());
        counter = 1;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.true_button:
                if (counter < questions.size()) {
                    checkAnswer(quest, true);
                    quest = (Question) questions.get(randNumbers.get(arrayCnt));
                    this.textView.setText(quest.getQuestion());
                } else {
                    startActivity(new Intent(GameActivity.this, PlayAgain.class));
                    finish();
                }
                break;

            case R.id.false_button:
                if (counter < questions.size()) {
                    checkAnswer(quest, false);
                    quest = (Question) questions.get(randNumbers.get(arrayCnt));
                    Log.d("Quest", "onClick: " + quest.getQuestion());
                    this.textView.setText(quest.getQuestion());
                } else {
                    startActivity(new Intent(GameActivity.this, PlayAgain.class));
                    finish();
                }

                break;


        }

    }

    private void checkAnswer(Question question, boolean ans) {
        if (question.getAnswer() == ans)
            Toast.makeText(GameActivity.this, "True", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(GameActivity.this, "False", Toast.LENGTH_SHORT).show();
        Log.d("Debug", "checkAnswer: " + question.getQuestion() + "  " + randNumbers.get(arrayCnt));
        counter++;
        arrayCnt++;


    }
}
