package com.example.mathgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Play extends AppCompatActivity {

    Button btnQuit;
    Button btnSubmit;
    EditText txtAnswer;
    TextView txtHeader;
    TextView txtNum;
    TextView txtEquation;
    TextView txtScore;
    TextView txtMistake;
    String difficulty;
    int num = 1;
    int score = 0;
    int mistakes = 0;
    int firstNum;
    int secondNum;
    int answer;
    int operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_play);

        btnQuit = findViewById(R.id.btnQuit);
        btnSubmit = findViewById(R.id.btnSubmit);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtHeader = findViewById(R.id.txtHeader);
        txtNum = findViewById(R.id.txtNum);
        txtEquation = findViewById(R.id.txtEquation);
        txtScore = findViewById(R.id.txtScore);
        txtMistake = findViewById(R.id.txtMistake);

        Intent intent = getIntent();
        Intent intentMain = new Intent(this, Main.class);

        difficulty = intent.getStringExtra("difficulty");

        txtHeader.setText("Difficulty: "+difficulty);
        setEquation();

        btnSubmit.setOnClickListener(v -> {
            answer = Integer.parseInt(txtAnswer.getText().toString());

            switch(difficulty) {
                case "Easy":
                            execute(firstNum, secondNum, answer, operation);
                            break; // end of case easy
                case "Normal":
                            execute(firstNum, secondNum, answer, operation);
                            break; // end of case normal
                case "Hard":
                            execute(firstNum, secondNum, answer, operation);
                            break; // end of case hard
            } // end of switch case

            txtNum.setText((++num)+"/10");
            txtAnswer.setText("");
            setEquation();
        });

        btnQuit.setOnClickListener(v -> {
            this.startActivity(intentMain);
        });
    }

    public int getRandomNumber(int start, int end) {
        return start + (int)(Math.random()*((end-start)+1));
    }

    public void setEquation() {
        switch(difficulty) {
            case "Easy":
                firstNum = getRandomNumber(1,15);
                secondNum = getRandomNumber(1,15);
                operation = getRandomNumber(1,4);
                this.setOperation(operation);
                break; // end of case easy
            case "Normal":
                firstNum = getRandomNumber(16,30);
                secondNum = getRandomNumber(16,30);
                operation = getRandomNumber(1,4);
                this.setOperation(operation);
                break; // end of case normal
            case "Hard":
                firstNum = getRandomNumber(31,100);
                secondNum = getRandomNumber(31,100);
                operation = getRandomNumber(1,4);
                this.setOperation(operation);
                break; // end of case hard
        } // end of switch case
    }

    public void setOperation(int operation) {
        String equation = "";
        switch(operation) {
            case 1: // Addition
                equation = firstNum+"+"+secondNum;
                txtEquation.setText(equation);
                break;
            case 2: // Subtraction
                equation = firstNum+"-"+secondNum;
                txtEquation.setText(equation);
                break;
            case 3: // Multiplication
                equation = firstNum+"*"+secondNum;
                txtEquation.setText(equation);
                break;
            case 4: // Division
                equation = firstNum+"/"+secondNum;
                txtEquation.setText(equation);
                break;
        }
    }

    public void execute(int firstNum, int secondNum, int answer, int operation) {
        boolean correct = true;

        switch(operation) {
            case 1: // Addition
                int sum = firstNum+secondNum;
                correct = (sum==answer)?true:false;
                break;
            case 2: // Subtraction
                int difference = firstNum-secondNum;
                correct = (difference==answer)?true:false;
                break;
            case 3: // Multiplication
                int product = firstNum*secondNum;
                correct = (product==answer)?true:false;
                break;
            case 4: // Division
                int quotient = firstNum/secondNum;
                correct = (quotient==answer)?true:false;
                break;
        }

        if(correct) {
            txtScore.setText("Score: "+(++score));
        }
        else {
            txtMistake.setText("Mistakes: "+(++mistakes));
        }
    }
}
