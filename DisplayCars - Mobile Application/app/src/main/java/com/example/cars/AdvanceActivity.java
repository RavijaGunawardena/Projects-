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
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AdvanceActivity extends AppCompatActivity implements View.OnClickListener {

    Button submitButton;
    ImageView ranImage;
    ImageView ranImage2;
    ImageView ranImage3;
    int pickedImages = 0;
    int lastPicked = 0;
    int pickedImages2 = 0;
    int lastPicked2 = 0;
    int pickedImages3 = 0;
    int lastPicked3 = 0;
    int state = 0;
    int attempt = 0;
    int att = 0;
    int att2 = 0;
    int checkAttempt = 0;
    int changeButtonName = 0;
    int correctAnswers = 0;
    int buttonClickState = 0;
    int ifOne = 0;
    int ifTwo = 0;
    int ifThree = 0;
    int timeComplete = 0;
    String lastImageName;
    String lastImageName2;
    String lastImageName3;
    Random ran;
    Random ran2;
    Random ran3;
    String drawableName;
    String drawableName2;
    String drawableName3;
    String selectedImage;
    String correctAnswerTextView1;
    String correctAnswerTextView2;
    String correctAnswerTextView3;
    String getIntent;
    EditText firstEditBox;
    EditText secondEditBox;
    EditText thirdEditBox;
    TextView firstTextView;
    TextView secondTextView;
    TextView thirdTextView;
    TextView pointDisplay;
    TextView countDown;
    private static final long START_TIME_IN_MILLIS = 20000;
    public CountDownTimer mCountDownTimer;
    public boolean mTimerRunning;
    public String timeLeftFormatted;
    public long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    List<String> values = new ArrayList<String>();


    Integer[] imagesRan = {R.drawable.audi, R.drawable.bugatti, R.drawable.rolls_royce, R.drawable.benz, R.drawable.dodge, R.drawable.fiat, R.drawable.ford,
            R.drawable.genesis, R.drawable.hyundai, R.drawable.infiniti, R.drawable.jaguar, R.drawable.jeep, R.drawable.lincoln, R.drawable.lotus, R.drawable.maserati,
            R.drawable.mitsubishi, R.drawable.nikola, R.drawable.nissan, R.drawable.patrol, R.drawable.porsche, R.drawable.saab, R.drawable.saturn, R.drawable.tesla,
            R.drawable.toyota, R.drawable.volvo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);

        //Getting palette from hint activity using Ids

        ranImage = (ImageView) findViewById(R.id.imageView7);
        ranImage2 = (ImageView) findViewById(R.id.imageView8);
        ranImage3 = (ImageView) findViewById(R.id.imageView9);
        firstEditBox = (EditText) findViewById(R.id.typeAnswer1);
        secondEditBox = (EditText) findViewById(R.id.typeAnswer2);
        thirdEditBox = (EditText) findViewById(R.id.typeAnswer3);
        firstTextView = (TextView) findViewById(R.id.textViewAnswer1);
        secondTextView = (TextView) findViewById(R.id.textViewAnswer2);
        thirdTextView = (TextView) findViewById(R.id.textViewAnswer3);
        pointDisplay = (TextView) findViewById(R.id.textViewPoint);
        countDown = (TextView) findViewById(R.id.timeTextView2);
        ran = new Random();
        ran2 = new Random();
        ran3 = new Random();
        submitButton = (Button) findViewById(R.id.checkAnswers);
        submitButton.setOnClickListener(this);
        Intent time = getIntent();
        getIntent = time.getStringExtra("StartTimer");

        if (getIntent.equalsIgnoreCase("withoutTimer")) {
            countDown.setVisibility(View.INVISIBLE);
        }
        else {
            if (getIntent.equalsIgnoreCase("firstTimer")) {
                startTimer();
            }
        }

    }

    //To make countdown timer
    @SuppressLint("SetTextI18n")
    public void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDown.setText(timeLeftFormatted);
        if(timeLeftFormatted.equalsIgnoreCase("00:00")) {
            if (timeComplete == 0) {
                submitButton.setText("Next");
                timeComplete = 1;
            }
        }
    }

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

    //Method to display random numbers, names and display correct-wrong answers
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        if (timeComplete == 1) {
            attempt = attempt + 1;
            Intent newActivity = getIntent();
            finish();
            startActivity(newActivity);
        }

        if(buttonClickState != 1) {
            if (checkAttempt != 1) {
                do {
                    do {
                        submitButton.setText("Submit");
                        int randInt = ran.nextInt(25) + 1;
                        drawableName = "image" + randInt;
                        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                        ranImage.setImageResource(resID);
                        pickedImages = ran.nextInt(imagesRan.length);
                        lastImageName = drawableName;
                    }
                    while (pickedImages == lastPicked);

                    lastPicked = pickedImages;
                    ranImage.setImageResource(imagesRan[pickedImages]);
                    state = state + 1;

                    do {
                        int randInt2 = ran.nextInt(25) + 1;
                        drawableName2 = "image" + randInt2;
                        int resID2 = getResources().getIdentifier(drawableName2, "drawable", getPackageName());
                        ranImage2.setImageResource(resID2);
                        pickedImages2 = ran.nextInt(imagesRan.length);
                        lastImageName2 = drawableName2;
                    }
                    while (pickedImages2 == lastPicked2);

                    lastPicked2 = pickedImages2;
                    ranImage2.setImageResource(imagesRan[pickedImages2]);
                    state = state + 1;

                    do {
                        int randInt3 = ran.nextInt(25) + 1;
                        drawableName3 = "image" + randInt3;
                        int resID3 = getResources().getIdentifier(drawableName3, "drawable", getPackageName());
                        ranImage3.setImageResource(resID3);
                        pickedImages3 = ran.nextInt(imagesRan.length);
                        lastImageName3 = drawableName3;
                    }
                    while (pickedImages3 == lastPicked3);

                    lastPicked3 = pickedImages3;
                    ranImage3.setImageResource(imagesRan[pickedImages3]);
                    state = state + 1;
                    checkAttempt = checkAttempt + 1;
                }
                while (pickedImages == pickedImages2 && pickedImages2 == pickedImages3 && pickedImages == pickedImages3);
            }

            if (attempt != 6) {
                if (ifOne != 1) {
                    for (int i = 0; i < values.size(); i++) {
                        if (values.get(i).equalsIgnoreCase(firstEditBox.getText().toString())) {
                            firstEditBox.setBackgroundColor(Color.GREEN);
                            firstEditBox.setEnabled(false);
                            correctAnswers = correctAnswers + 1;
                            correctAnswerTextView1 = values.get(i);
                            ifOne = ifOne + 1;
                            break;
                        } else {
                            firstEditBox.setBackgroundColor(Color.RED);
                            attempt = attempt + 1;
                            if (attempt == 3) {
                                firstTextView.setBackgroundColor(Color.YELLOW);
                                firstTextView.setText(values.get(0));
                            }
                            break;
                        }
                    }
                }

                if (ifTwo != 1) {
                    for (int i = 1; i < values.size(); i++) {
                        if (values.get(i).equalsIgnoreCase(secondEditBox.getText().toString())) {
                            secondEditBox.setBackgroundColor(Color.GREEN);
                            secondEditBox.setEnabled(false);
                            correctAnswers = correctAnswers + 1;
                            correctAnswerTextView2 = values.get(i);
                            ifTwo = ifTwo + 1;
                            break;
                        } else {
                            att2 = att2 + 1;
                            secondEditBox.setBackgroundColor(Color.RED);
                            attempt = attempt + 1;
                            if (att2 == 3) {
                                secondTextView.setBackgroundColor(Color.YELLOW);
                                secondTextView.setText(values.get(1));
                            }
                            break;
                        }
                    }
                }

                if (ifThree != 1) {
                    for (int i = 2; i < values.size(); i++) {
                        if (values.get(i).equalsIgnoreCase(thirdEditBox.getText().toString())) {
                            thirdEditBox.setBackgroundColor(Color.GREEN);
                            thirdEditBox.setEnabled(false);
                            correctAnswers = correctAnswers + 1;
                            correctAnswerTextView3 = values.get(i);
                            ifThree = ifThree + 1;
                            break;
                        } else {
                            thirdEditBox.setBackgroundColor(Color.RED);
                            attempt = attempt + 1;
                            att = att + 1;
                            if (att == 3) {
                                thirdTextView.setBackgroundColor(Color.YELLOW);
                                thirdTextView.setText(values.get(2));
                            }
                            break;
                        }
                    }
                }

                changeButtonName = changeButtonName + 1;
            }
            else {
                if (correctAnswers != 3 && correctAnswers != 2 && correctAnswers != 1) {
                    Toast displayWrongMessage = new Toast(getApplicationContext());

                    TextView a = new TextView(AdvanceActivity.this);
                    a.setTextColor(Color.RED);
                    a.setTextSize(30);
                    a.setPadding(50, 0, 50, 2150);

                    Typeface w = Typeface.create("Serif", Typeface.BOLD);
                    a.setTypeface(w);
                    a.setText("WRONG !");
                    displayWrongMessage.setView(a);
                    displayWrongMessage.show();
                    changeButtonName = changeButtonName + 1;
                }
            }
            if (changeButtonName == 4 || correctAnswers == 3 || buttonClickState == 1) {
                submitButton.setText("Next");
                attempt = 6;
                buttonClickState = buttonClickState + 1;
                if (changeButtonName == 4) {
                    if (correctAnswers != 1 && correctAnswers != 2) {
                        firstTextView.setBackgroundColor(Color.YELLOW);
                        secondTextView.setBackgroundColor(Color.YELLOW);
                        thirdTextView.setBackgroundColor(Color.YELLOW);

                        firstTextView.setText(values.get(0));
                        secondTextView.setText(values.get(1));
                        thirdTextView.setText(values.get(2));
                    }
                }
            }
        }
        else {
            buttonClickState = 2;
        }
        if (buttonClickState == 2) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        pointDisplay.setText(String.valueOf(correctAnswers));
        values.clear();
        if (drawableName.equalsIgnoreCase("image1") || drawableName2.equalsIgnoreCase("image1") || drawableName3.equalsIgnoreCase("image1")) {
            selectedImage = "Audi";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image2") || drawableName2.equalsIgnoreCase("image2") || drawableName3.equalsIgnoreCase("image2")) {
            selectedImage = "Benz";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image3") || drawableName2.equalsIgnoreCase("image3") || drawableName3.equalsIgnoreCase("image3")) {
            selectedImage = "Bugatti";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image4") || drawableName2.equalsIgnoreCase("image4") || drawableName3.equalsIgnoreCase("image4")) {
            selectedImage = "Dodge";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image5") || drawableName2.equalsIgnoreCase("image5") || drawableName3.equalsIgnoreCase("image5")) {
            selectedImage = "Fiat";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image6") || drawableName2.equalsIgnoreCase("image6") || drawableName3.equalsIgnoreCase("image6")) {
            selectedImage = "Ford";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image7") || drawableName2.equalsIgnoreCase("image7") || drawableName3.equalsIgnoreCase("image7")) {
            selectedImage = "Genesis";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image8") || drawableName2.equalsIgnoreCase("image8") || drawableName3.equalsIgnoreCase("image8")) {
            selectedImage = "Hyundai";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image9") || drawableName2.equalsIgnoreCase("image9") || drawableName3.equalsIgnoreCase("image9")) {
            selectedImage = "Infinity";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image10") || drawableName2.equalsIgnoreCase("image10") || drawableName3.equalsIgnoreCase("image10")) {
            selectedImage = "Jaguar";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image11") || drawableName2.equalsIgnoreCase("image11") || drawableName3.equalsIgnoreCase("image11")) {
            selectedImage = "Jeep";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image12") || drawableName2.equalsIgnoreCase("image12") || drawableName3.equalsIgnoreCase("image12")) {
            selectedImage = "Lincoln";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image13") || drawableName2.equalsIgnoreCase("image13") || drawableName3.equalsIgnoreCase("image13")) {
            selectedImage = "Lotus";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image14") || drawableName2.equalsIgnoreCase("image14") || drawableName3.equalsIgnoreCase("image14")) {
            selectedImage = "Maserati";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image15") || drawableName2.equalsIgnoreCase("image15") || drawableName3.equalsIgnoreCase("image15")) {
            selectedImage = "Mitsubishi";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image16") || drawableName2.equalsIgnoreCase("image16") || drawableName3.equalsIgnoreCase("image16")) {
            selectedImage = "Nikola";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image17") || drawableName2.equalsIgnoreCase("image17") || drawableName3.equalsIgnoreCase("image17")) {
            selectedImage = "Nissan";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image18") || drawableName2.equalsIgnoreCase("image18") || drawableName3.equalsIgnoreCase("image18")) {
            selectedImage = "Patrol";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image19") || drawableName2.equalsIgnoreCase("image19") || drawableName3.equalsIgnoreCase("image19")) {
            selectedImage = "Polestar";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image20") || drawableName2.equalsIgnoreCase("image20") || drawableName3.equalsIgnoreCase("image20")) {
            selectedImage = "Porsche";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image21") || drawableName2.equalsIgnoreCase("image21") || drawableName3.equalsIgnoreCase("image21")) {
            selectedImage = "Rolls Royce";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image22") || drawableName2.equalsIgnoreCase("image22") || drawableName3.equalsIgnoreCase("image22")) {
            selectedImage = "Saab";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image23") || drawableName2.equalsIgnoreCase("image23") || drawableName3.equalsIgnoreCase("image23")) {
            selectedImage = "Saturn";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image24") || drawableName2.equalsIgnoreCase("image24") || drawableName3.equalsIgnoreCase("image24")) {
            selectedImage = "Subaru";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image25") || drawableName2.equalsIgnoreCase("image25") || drawableName3.equalsIgnoreCase("image25")) {
            selectedImage = "Saab";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image26") || drawableName2.equalsIgnoreCase("image26") || drawableName3.equalsIgnoreCase("image26")) {
            selectedImage = "Toyota";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equalsIgnoreCase("image27") || drawableName2.equalsIgnoreCase("image27") || drawableName3.equalsIgnoreCase("image27")) {
            selectedImage = "Volvo";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
    }
}

