package com.example.randomquiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String QUESTIONS_ARRAY_EXTRA = "QUESTIONS_ARRAY_EXTRA";
    private Button falseButton;
    private Button trueButton;
    private TextView showText;
    private EditText enterName;
    private ArrayList<Question> questions;
    private Question question;
    private final int RESULT_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.button);
        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        Button playButton = findViewById(R.id.play_button);
        enterName = findViewById(R.id.question_edittext);
        showText = findViewById(R.id.count_textview);
        questions = new ArrayList<>();

        enterName.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        myButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.question_edittext:
                question = new Question(enterName.getText().toString());
                break;
            case R.id.false_button:
                question.setAnswer(false);
                trueButton.setEnabled(false);
                Toast.makeText(this, getString(R.string.false_button_text), Toast.LENGTH_SHORT).show();
                break;
            case R.id.true_button:
                question.setAnswer(true);
                falseButton.setEnabled(false);
                Toast.makeText(this, getString(R.string.true_button_text), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                if (TextUtils.isEmpty(enterName.getText().toString())) {
                    Toast.makeText(this, getString(R.string.empty_question_error_text), Toast.LENGTH_SHORT).show();
                } else {
                    question.setQuestion(enterName.getText().toString());
                    enterName.setText("");
                    questions.add(question);
                    Toast.makeText(this, getString(R.string.question_added_message), Toast.LENGTH_SHORT).show();
                    showText.setText(String.valueOf(questions.size()));
                    falseButton.setEnabled(true);
                    trueButton.setEnabled(true);
                    question = new Question(null);
                }

                break;
            case R.id.play_button:
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra(QUESTIONS_ARRAY_EXTRA, questions);
                startActivityForResult(intent, RESULT_CODE);
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_CODE) {
            Toast.makeText(MainActivity.this, getString(R.string.start_again), Toast.LENGTH_SHORT).show();
            questions = new ArrayList<>();
            showText.setText("0");
        }

    }
}
