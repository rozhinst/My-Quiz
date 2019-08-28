package com.example.randomquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity implements View.OnClickListener, Serializable {
    private TextView view;
    private Button trueButton;
    private Button falseButton;
    private ArrayList questions;
    private ArrayList randNumbers;
    private int counter;
    private int arrayCnt;
    private Question quest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        view = findViewById(R.id.show_question);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        randNumbers = new ArrayList();
        arrayCnt = 0;

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        Bundle extra = getIntent().getExtras();
        questions = (ArrayList) extra.getSerializable("array");

        for(int i=0; i<questions.size();i++)
            randNumbers.add(i);
        Collections.shuffle(randNumbers);

        quest = (Question)questions.get((int)randNumbers.get(arrayCnt));
        System.out.println(quest.getQuestion()+ " rand is" + randNumbers.get(arrayCnt));
        view.setText(quest.getQuestion());
        counter = 1;
       // arrayCnt++;



    }

    @Override
    public void onClick(View view) {////problemasssssss
        switch (view.getId()){
            case R.id.true_button:
                if(counter < questions.size()) {
                    checkAnswer(quest, true);
                    quest = (Question)questions.get((int)randNumbers.get(arrayCnt));
                    this.view.setText(quest.getQuestion());
                }

                else {
                    startActivity(new Intent(GameActivity.this, PlayAgain.class));
                    finish();
                }
                break;

            case R.id.false_button:
                this.view.setText("hiiiiii");
                if(counter < questions.size()) {
                    checkAnswer(quest, false);
                    quest = (Question)questions.get((int)randNumbers.get(arrayCnt));
                    this.view.setText(quest.getQuestion());
                }
                else {
                    startActivity(new Intent(GameActivity.this, PlayAgain.class));
                    finish();
                }

                break;


        }

    }

    private void checkAnswer(Question question, boolean ans){
        if(question.getAnswer() == ans)
            Toast.makeText(GameActivity.this, "True", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(GameActivity.this, "False", Toast.LENGTH_SHORT).show();
        counter++;
        arrayCnt++;

    }
}
