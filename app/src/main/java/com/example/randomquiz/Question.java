package com.example.randomquiz;

import java.io.Serializable;

public class Question implements Serializable {
    private String question;
    private boolean answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Question(String question) {
        this.question = question;
    }

}

