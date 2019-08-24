package com.example.randomquiz;
import android.text.Editable;

public class Question {
    private String question;
    private boolean answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Question(String question) {
        this.question = question;
        this.answer = answer;
    }

}

