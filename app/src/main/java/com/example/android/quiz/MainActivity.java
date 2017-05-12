package com.example.android.quiz;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
    // Create an array of QuestionAnswer objects
    final ArrayList<QuestionAnswer> quiz = new ArrayList<QuestionAnswer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz.add(new QuestionAnswer("What year did Japan attack Pearl Harbor?", "1943"));
        quiz.add(new QuestionAnswer("What year did Germany invade Poland starting WW2?", "1939"));
        quiz.add(new QuestionAnswer("What year did WW2 end?", "1945"));
        quiz.add(new QuestionAnswer("The First Atomic Bomb was dropped in what city?", "Hiroshima"));
        quiz.add(new QuestionAnswer("Around __ million people died in World War 2?", "64"));
        quiz.add(new QuestionAnswer("Name one neutral Eurpoean country?", "Spain Sweden Switzerland"));
        quiz.add(new QuestionAnswer("Italy was part of the Axis or Allies?", "Axis"));
        quiz.add(new QuestionAnswer("The country with the largest number of WWII causalities was?",
                "Russia, with over 21 million"));
        quiz.add(new QuestionAnswer("The greatest tank battle in history occurred between the Germans and Russians at?",
                "Kursk"));
        quiz.add(new QuestionAnswer("The code name for the D-Day invasion was Operation?", "Overlord"));

        // display the questions
        for (int cnt = 0; cnt < quiz.size(); cnt++) {
            QuestionAnswer tmpQuiz = quiz.get(cnt);  // get the next obj in the array

            String idStr = "text_ques"+ Integer.toString(cnt + 1);  // Create the resource id name

            int id = getResources().getIdentifier(idStr, "id", getPackageName());   // get the resId

            TextView question = (TextView) findViewById(id);    // find the view

            question.setText(tmpQuiz.getQuestion());    // display the question
        }
    }

    /**
     * This method is called when the uses submits their answers.
     *  It grades their answers.
     */
    public void checkAnswers(View view) {
        int numberRight = 0;    // number of questions answered correctly
        int numberWrong = 0;    // number of questions answered incorrectly
        int id = 0; // Resource ID

        String answerStr = "";  // User answer
        String idStr = "";  // Name of resource to find

        // Grade user's answers against the correct answer
        for (int cnt = 0; cnt < quiz.size(); cnt++) {
            QuestionAnswer tmpQuiz = quiz.get(cnt);  // get the next obj in the array

            idStr = "edit_answer"+ Integer.toString(cnt + 1);  // Create the resource id name

            id = getResources().getIdentifier(idStr, "id", getPackageName());   // get the resId

            EditText answer = (EditText) findViewById(id);    // find the view

            answerStr = (String) answer.getText().toString().trim();    // get the answer

            // Check the answer
            if (answerStr.toUpperCase().equals(tmpQuiz.getAnswer().toUpperCase())) {
                // Answer is correct
                numberRight++;

                //  Show answer in green for correct answers
                answer.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.rightAnswer));
            } else {
                //  Assume an asnwer is two or more characters
                if (answerStr.length() > 1) {
                    //  Check if users answer is correct by seeing if its in answer
                    if (tmpQuiz.getAnswer().toUpperCase().contains(answerStr.toUpperCase())) {
                        // Answer is correct
                        numberRight++;

                        //  Show answer in green for correct answers
                        answer.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.rightAnswer));

                        continue;   // Check next answer
                    }
                }

                // Answer is wrong
                numberWrong++;

                // text for question that is incorrect
                String correctAnswer = "Your Answer: " + answerStr + " Correct Answer: " + tmpQuiz.getAnswer();

                //  Display the text to the user
                answer.setText(correctAnswer);

                //  Show text in different color to denote its incorrect
                answer.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.wrongAnswer));
            }
        }

        //  User correct and incorrect
        String score = "You answered " + Integer.toString(numberRight) + " RIGHT and "
                + Integer.toString(numberWrong) + " WRONG!";

        //  display score
        Toast.makeText(MainActivity.this, score, Toast.LENGTH_LONG).show();
    }

    /**
     * Clear out all the old answers
     */
    public void resetQuiz(View view) {
        int id = 0; // Resource ID
        
        String idStr = "";  // Name of resource to find

        // Grade user's answers against the correct answer
        for (int cnt = 0; cnt < quiz.size(); cnt++) {
            QuestionAnswer tmpQuiz = quiz.get(cnt);  // get the next obj in the array

            idStr = "edit_answer" + Integer.toString(cnt + 1);  // Create the resource id name

            id = getResources().getIdentifier(idStr, "id", getPackageName());   // get the resId

            EditText answer = (EditText) findViewById(id);    // find the view

            answer.setText("");    // empty the field
        }
    }
}
