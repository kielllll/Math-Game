package com.example.mathgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends AppCompatActivity {

    Button btnQuit;
    Button btnSubmit;
    Button btnRestart;
    EditText txtAnswer;
    TextView txtHeader;
    TextView txtNum;
    TextView txtEquation;
    TextView txtScore;
    TextView txtMistake;
    TextView txtRemarks;
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
        btnRestart = findViewById(R.id.btnRestart);
        txtAnswer = findViewById(R.id.txtAnswer);
        txtHeader = findViewById(R.id.txtHeader);
        txtNum = findViewById(R.id.txtNum);
        txtEquation = findViewById(R.id.txtEquation);
        txtScore = findViewById(R.id.txtScore);
        txtMistake = findViewById(R.id.txtMistake);
        txtRemarks = findViewById(R.id.txtRemarks);

        Intent intent = getIntent();
        Intent intentMain = new Intent(this, Main.class);

        difficulty = intent.getStringExtra("difficulty");

        txtHeader.setText("Difficulty: "+difficulty);
        btnRestart.setVisibility(View.INVISIBLE);
        setEquation();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Return to main menu");
        builder.setMessage("Muondang nka?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Play.this.startActivity(intentMain);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        btnSubmit.setOnClickListener(v -> {
            try {
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

                if(num == 15) {
                    String remarks = "";
                    btnSubmit.setEnabled(false);

                    if(score == 15) remarks = "KA PERFECT BA UY.";
                    else if(score == 14) remarks = "HAPITA LAGE.";
                    else if(score>=11&&score<=13) remarks = "Not bad.";
                    else if(score>=8&&score>=10) remarks = "Good job, practice pa!";
                    else remarks = "AY OY UNSA MANA";

                    txtRemarks.setText(remarks);
                    btnRestart.setVisibility(View.VISIBLE);
                } else {
                    txtNum.setText((++num)+"/15");
                    setEquation();
                }

                txtAnswer.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Please enter your answer", Toast.LENGTH_SHORT).show();
            }
        });

        btnRestart.setOnClickListener(v -> {
            setEquation();
            btnRestart.setVisibility(View.INVISIBLE);
            btnSubmit.setEnabled(true);
            txtRemarks.setText("");
            score = 0;
            mistakes = 0;
            txtScore.setText("Score: "+score);
            txtRemarks.setText("Mistakes: "+mistakes);
        });

        btnQuit.setOnClickListener(v -> {
            builder.show();
        });
    }

    public void initDialog() {

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
