package com.example.android.quiz;

/**
 * Created by PaulN on 5/9/2017.
 */

public class QuestionAnswer {
    private String mQuestion = "";  //  Strong to hold the question
    private String mAnswer = "";    // String to hold the correct answer

    public QuestionAnswer(String question, String answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public String getQuestion () {
        return mQuestion;
    }
}