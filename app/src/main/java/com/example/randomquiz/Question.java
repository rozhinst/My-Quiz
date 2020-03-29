package com.example.randomquiz;

import java.io.Serializable;

class Question implements Serializable {
    private String question;
    private boolean answer;

    String getQuestion() {
        return question;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    boolean getAnswer() {
        return answer;
    }

    void setAnswer(boolean answer) {
        this.answer = answer;
    }

    Question(String question) {
        this.question = question;
    }

}

