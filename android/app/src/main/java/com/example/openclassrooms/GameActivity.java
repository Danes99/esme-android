package com.example.openclassrooms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    // Get widgets
    private TextView mQuestionText;
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get widgets values
        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswer1Button = (Button) findViewById(R.id.activity_game_answer1_button);
        mAnswer2Button = (Button) findViewById(R.id.activity_game_answer2_button);
        mAnswer3Button = (Button) findViewById(R.id.activity_game_answer3_button);
        mAnswer4Button = (Button) findViewById(R.id.activity_game_answer4_button);
    }
}