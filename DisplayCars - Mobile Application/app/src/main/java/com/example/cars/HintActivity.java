package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class HintActivity extends AppCompatActivity implements View.OnClickListener{

    int timeComplete = 0;
    private ImageView displayImage;
    private Button submitBtn;
    private TextView userGuessView, timerView;
    private EditText userInputView;
    private int randomImageNum;
    private int maxAttempts = 3;
    private String randomCarName;
    private ArrayList<String> listOne, listTwo;
    private static final long START_TIME_IN_MILLIS = 20000;
    public CountDownTimer mCountDownTimer;
    public boolean mTimerRunning;
    public String timeLeftFormatted;
    public long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    TextView correctAnswer;
    String getIntent;

    //Make images list
    private int[] imagesList = new int[] {
            R.drawable.audi, R.drawable.bugatti, R.drawable.rolls_royce, R.drawable.benz, R.drawable.dodge, R.drawable.fiat, R.drawable.ford,
            R.drawable.genesis, R.drawable.hyundai, R.drawable.infiniti, R.drawable.jaguar, R.drawable.jeep, R.drawable.lincoln, R.drawable.lotus, R.drawable.maserati,
            R.drawable.mitsubishi, R.drawable.nikola, R.drawable.nissan, R.drawable.patrol, R.drawable.porsche, R.drawable.saab, R.drawable.saturn, R.drawable.tesla,
            R.drawable.toyota, R.drawable.volvo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        //Getting palette from hint activity using Ids
        timerView = findViewById(R.id.timeTextView3);
        correctAnswer = findViewById(R.id.hintCorrectAnswerDisplay);
        submitBtn = findViewById(R.id.submitButton);
//        startTimer();
        // Getting imageView
        displayImage = findViewById(R.id.hintImageView);
        // Getting random image
        randomImageNum = (int)(Math.random() * imagesList.length);
        // Setting random image for the start
        try {
            displayImage.setImageResource(imagesList[randomImageNum]);
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Please restart the application", Toast.LENGTH_SHORT);
            toast.show();
        }
        // Displaying car name converted to dash
        displayCarNameConverted();
        submitBtn.setOnClickListener(this);
        Intent time = getIntent();
        getIntent = time.getStringExtra("StartTimer");

        if (getIntent.equalsIgnoreCase("withoutTimer")) {
            timerView.setVisibility(View.INVISIBLE);
        }
        else {
            if (getIntent.equalsIgnoreCase("firstTimer")) {
                startTimer();
            }
        }
    }

    //Method to update count down timer
    @SuppressLint("SetTextI18n")
    public void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timerView.setText(timeLeftFormatted);
        if(timeLeftFormatted.equalsIgnoreCase("00:00")) {
            if (timeComplete == 0) {
                submitBtn.setText("Next");
                timeComplete = 1;
            }
        }
    }

    //Method to start the timer
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
    }

    //Method to covert car name
    private void displayCarNameConverted() {
        randomCarName = getCarName();
        populateArrays(randomCarName.toLowerCase());
        convertNameToDash();
    }

    //Assign names for images
    private String getCarName() {
        if (randomImageNum >= 0 && randomImageNum <= 4) return "Audi";
        else if (randomImageNum >= 5 && randomImageNum <= 9) return "Benz";
        else if (randomImageNum >= 10 && randomImageNum <= 14) return "BMW";
        else if (randomImageNum >= 15 && randomImageNum <= 19) return "Bugatti";
        else if (randomImageNum >= 20 && randomImageNum <= 24) return "Lamborghini";
        else return "Ferrari";
    }

    private void populateArrays(String carName) {
        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();

        for (int i=0; i<carName.length(); i++) {
            String nameChar = String.valueOf(carName.charAt(i));
            listOne.add(nameChar.toUpperCase());
            Log.d("Car name", nameChar);
            listTwo.add("     -      ");

        }
    }

    private void convertNameToDash() {
        StringBuilder strBuilder = new StringBuilder();
        userGuessView =(TextView) findViewById(R.id.wordDisplay);

        for (String str : listTwo) {
            strBuilder.append(str);
            strBuilder.append(" ");
        }

        String dashedName = strBuilder.toString();
        userGuessView.setText(dashedName);
    }

    @Override
    public void onClick(View v) {

        if (timeComplete == 1) {
            Intent newActivity = getIntent();
            finish();
            startActivity(newActivity);
        }

        userInputView = (EditText) findViewById(R.id.typeLetterTextView);

        String userInput = userInputView.getText().toString().toUpperCase();

        if (maxAttempts != 1) {
            if ((userInput.equals("")) || (!listOne.contains(userInput))) {
                maxAttempts -= 1;
                System.out.println(maxAttempts);
            }

            if ((listOne.contains(userInput)) && (!listOne.equals(listTwo)) || (listOne.contains(userInput) && (listTwo.isEmpty()))) {
                for (int i = 0; i < listOne.size(); i++) {
                    if (listOne.get(i).equals(userInput)) {
                        listTwo.set(i, userInput.toUpperCase());
                    }
                }
                convertNameToDash();
                userInputView.setText("");

                if (((listOne.equals(listTwo) && maxAttempts == 1)) || ((listOne.equals(listTwo) && maxAttempts != 1))) {
                    correctAns();
                }
            }
        }

        else {
            if (listOne.contains(userInput)) {
                for (int i = 0; i < listOne.size(); i++) {
                    if (listOne.get(i).equals(userInput)) {
                        listTwo.set(i, userInput.toUpperCase());
                    }

                    convertNameToDash();
                    userInputView.setText("");
                    if (listOne.equals(listTwo)) {
                        correctAns();
                    }
                }
            }
            else {
                wrongAns();
            }
        }
    }

    private void changeSubmitBtn(Button submitBtn) {
        submitBtn.setOnClickListener(v -> reloadScreen());
    }

    private void reloadScreen() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    private void correctAns() {
        Toast displayWrongAnswer = new Toast(getApplicationContext());

        TextView a = new TextView(HintActivity.this);
        a.setTextColor(Color.BLUE);
        a.setTextSize(25);

        Typeface w = Typeface.create("Serif", Typeface.BOLD);
        a.setTypeface(w);
        a.setText(R.string.correct);
        displayWrongAnswer.setView(a);
        displayWrongAnswer.show();
    }

    @SuppressLint("SetTextI18n")
    private void wrongAns() {
        Toast displayWrongAnswer = new Toast(getApplicationContext());

        TextView a = new TextView(HintActivity.this);
        a.setBackgroundColor(Color.WHITE);
        a.setTextColor(Color.RED);
        a.setTextSize(15);

        Typeface w = Typeface.create("Serif", Typeface.BOLD);
        a.setTypeface(w);
        a.setText("Answer is Wrong");
        displayWrongAnswer.setView(a);
        displayWrongAnswer.show();

        userInputView.setBackgroundColor(Color.RED);
        userInputView.setEnabled(false);
        correctAnswer.setText("Correct Answer - " + randomCarName);

    }

}