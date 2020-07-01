package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
    TextView userOneTextView;
    TextView userTwoTextView;
    TextView timeRemainingView;
    TextView scoreOneTextView;
    TextView scoreTwoTextView;

    Button one;
    Button two;
    Button three;
    Button four;

    final static int MAX_TARDINESS = 10000;
    ProgressBar progressBar;
    int scorePlayerOne = 0;
    int scorePlayerTwo = 0;
    Timer t = new Timer();
    int seconds = 10;


    public QuizActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String usernameReceived = bundle.getString("username");

        userOneTextView = (TextView) findViewById(R.id.UserOne);
        userTwoTextView = (TextView) findViewById(R.id.UserTwo);
        timeRemainingView = (TextView) findViewById(R.id.TimeRemainingView) ;
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        scoreOneTextView = (TextView) findViewById(R.id.UserOneScore);

        userOneTextView.setText(usernameReceived);
        userTwoTextView.setText("Joueur 2");

        one = (Button) findViewById(R.id.buttonOne);
        one.setOnClickListener(this);
        two = (Button) findViewById(R.id.buttonTwo);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.buttonThree);
        three.setOnClickListener(this);
        four = (Button) findViewById(R.id.buttonFour);
        four.setOnClickListener(this);
        setQuestion(0);

    }

    protected void setQuestion(int num) {

        timeManagement();
    }

    protected void timeManagement() {
        t.scheduleAtFixedRate(new TimerTask() {
            int numQuestion = 0;
            @Override
            public void run(){
                if (seconds <= 0) {
                    seconds = 10;
                    setQuestion(numQuestion+1);
                } else {
                    seconds = seconds-1;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(seconds);
                        timeRemainingView.setText(""+seconds);
                    }
                });
            }
        },1000,1000);
    }

    protected void updateScore(boolean reussi) {
        if (reussi) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    one.setBackgroundColor(Color.GREEN);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    two.setBackgroundColor(Color.RED);

                }
            });
        }
        scoreOneTextView.setText(""+scorePlayerOne);
    }

    protected void checkResponse(int resp){
        boolean reussi;
        if (resp == 1){
            scorePlayerOne = scorePlayerOne + seconds;
            reussi = true;
        } else {
            scorePlayerOne = scorePlayerOne + 0;
            reussi = false;
        }
        updateScore(reussi);
    }

    @Override
    public void onClick(View v) {
        t.cancel();
        switch (v.getId()) {
            case R.id.buttonOne:
                checkResponse(1);
                break;

            case R.id.buttonTwo:
                checkResponse(2);
                break;

            case R.id.buttonThree:
                checkResponse(3);
                break;

            case R.id.buttonFour:
                checkResponse(4);
                break;
            default:
                break;
        }
    }
}