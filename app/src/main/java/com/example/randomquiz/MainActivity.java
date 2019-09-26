package com.example.randomquiz;

import android.content.Intent;
import android.os.Bundle;
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
        enterName  = findViewById(R.id.editText);
        showText = findViewById(R.id.textView);
        questions = new ArrayList<>();

        enterName.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        myButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editText:
                question = new Question(enterName.getText().toString());
                break;
            case R.id.false_button:
                question.setAnswer(false);
                trueButton.setEnabled(false);
                Toast.makeText(MainActivity.this,"False",Toast.LENGTH_SHORT).show();
                break;
            case R.id.true_button:
                question.setAnswer(true);
                falseButton.setEnabled(false);
                Toast.makeText(MainActivity.this,"True",Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                if (enterName.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this , "Question cannot be empty!!" , Toast.LENGTH_SHORT).show();
                }else {
                    question.setQuestion(enterName.getText().toString());
                    enterName.setText("");
                    questions.add(question);
                    Toast.makeText(MainActivity.this, "Question Successfully added!", Toast.LENGTH_SHORT).show();
                    showText.setText(String.valueOf(questions.size()));
                    falseButton.setEnabled(true);
                    trueButton.setEnabled(true);
                    question = new Question(null);
                }

                break;
            case R.id.play_button:
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                intent.putExtra(QUESTIONS_ARRAY_EXTRA,  questions);
                startActivityForResult(intent, RESULT_CODE);
                break;




        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_CODE){
            Toast.makeText(MainActivity.this, "Start Again!", Toast.LENGTH_SHORT).show();
            questions = new ArrayList<>();
            showText.setText("0");
        }

    }
}
