package com.example.randomquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button myButton;
    private Button falseButton;
    private Button trueButton;
    private Button playButton;
    private TextView showText;
    private EditText enterName;
    private ArrayList<Question> questions;
    private Question question;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.button);
        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        playButton = findViewById(R.id.play_button);
        enterName  = findViewById(R.id.editText);
        showText = findViewById(R.id.textView);
        questions = new ArrayList<>();

        enterName.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        myButton.setOnClickListener(this);
        playButton.setOnClickListener(this);

//        showText.setText(questions.size());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.editText:
                //enterName.setText("");
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
//                System.out.println(enterName.getText().length());
                if (enterName.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this , "Question cannot be empty!!" , Toast.LENGTH_SHORT).show();
                }else {
                    enterName.setText("");
                    questions.add(question);
                    Toast.makeText(MainActivity.this, "Question Successfully added!", Toast.LENGTH_SHORT).show();
                    showText.setText(questions.size() + "");
                    falseButton.setEnabled(true);
                    trueButton.setEnabled(true);
                }
//                enterName.setText(null);

                break;
            case R.id.play_button:
                startActivity(new Intent(MainActivity.this,GameActivity.class));

                break;






        }
    }
}
