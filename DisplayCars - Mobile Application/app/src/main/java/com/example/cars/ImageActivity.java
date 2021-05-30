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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{

    //Create variables
    private String[] carMakeNames;
    TextView displayRandomCarName;
    Button displayRandomName;
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
    int timeComplete = 0;
    String lastImageName;
    String lastImageName2;
    String lastImageName3;
    String randomName;
    Random ran;
    Random ran2;
    Random ran3;
    String drawableName;
    String drawableName2;
    String drawableName3;
    String selectedImage;
    String timeStr;
    private static final long START_TIME_IN_MILLIS = 20000;
    public TextView mTextViewCountDown;
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
        setContentView(R.layout.activity_image);

        //Getting palette from hint activity using Ids
        ranImage = (ImageView) findViewById(R.id.imageRanView);
        ranImage2 = (ImageView) findViewById(R.id.imageRanView2);
        ranImage3 = (ImageView) findViewById(R.id.imageRanView3);
        ran = new Random();
        ran2 = new Random();
        ran3 = new Random();
        carMakeNames = getResources().getStringArray(R.array.names);
        displayRandomName = (Button) findViewById(R.id.ranButton);
        displayRandomName.setOnClickListener(this);
        displayRandomCarName = (TextView) findViewById(R.id.randomCarImage);
        mTextViewCountDown = (TextView) findViewById(R.id.timeTextView);

        //Getting value from main activity
        Intent time = getIntent();
        timeStr = time.getStringExtra("StartTimer");

        if (timeStr.equals("withoutTimer")) {
            mTextViewCountDown.setVisibility(View.INVISIBLE);
        }
        else {
            if (timeStr.equals("firstTimer")) {
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
        mTextViewCountDown.setText("Remaining Time : " + timeLeftFormatted);
        if(timeLeftFormatted.equals("00:00")) {
            if (timeComplete == 0) {
                displayRandomName.setText("Next");
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        //Make button to start new activity
        if (timeComplete == 1) {
            Intent newActivity = getIntent();
            finish();
            startActivity(newActivity);
        }

        //To generate random images and random name
        do {
            displayRandomName.setText("Next");
            do {
                int randInt = ran.nextInt(30) + 1;
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
        }
        while (pickedImages == pickedImages2 && pickedImages2 == pickedImages3);

        //Assign relevant name for each random image
        values.clear();
        if (drawableName.equals("image1") || drawableName2.equals("image1") || drawableName3.equals("image1")) {
            Log.d("Car name", "Audi");
            selectedImage = "Audi";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image2") || drawableName2.equals("image2") || drawableName3.equals("image2")) {
            Log.d("Car name", "Benz");
            selectedImage = "Benz";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image3") || drawableName2.equals("image3") || drawableName3.equals("image3")) {
            Log.d("Car name", "Bugatti");
            selectedImage = "Bugatti";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image4") || drawableName2.equals("image4") || drawableName3.equals("image4")) {
            Log.d("Car name", "Dodge");
            selectedImage = "Dodge";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image5") || drawableName2.equals("image5") || drawableName3.equals("image5")) {
            Log.d("Car name", "Fiat");
            selectedImage = "Fiat";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image6") || drawableName2.equals("image6") || drawableName3.equals("image6")) {
            Log.d("Car name", "Ford");
            selectedImage = "Ford";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image7") || drawableName2.equals("image7") || drawableName3.equals("image7")) {
            Log.d("Car name", "Genesis");
            selectedImage = "Genesis";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image8") || drawableName2.equals("image8") || drawableName3.equals("image8")) {
            Log.d("Car name", "Hyundai");
            selectedImage = "Hyundai";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image9") || drawableName2.equals("image9") || drawableName3.equals("image9")) {
            Log.d("Car name", "Infinity");
            selectedImage = "Infinity";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image10") || drawableName2.equals("image10") || drawableName3.equals("image10")) {
            Log.d("Car name", "Jaguar");
            selectedImage = "Jaguar";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image11") || drawableName2.equals("image11") || drawableName3.equals("image11")) {
            Log.d("Car name", "Jeep");
            selectedImage = "Jeep";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image12") || drawableName2.equals("image12") || drawableName3.equals("image12")) {
            Log.d("Car name", "Lincoln");
            selectedImage = "Lincoln";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image13") || drawableName2.equals("image13") || drawableName3.equals("image13")) {
            Log.d("Car name", "Lotus");
            selectedImage = "Lotus";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image14") || drawableName2.equals("image14") || drawableName3.equals("image14")) {
            Log.d("Car name", "Maserati");
            selectedImage = "Maserati";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image15") || drawableName2.equals("image15") || drawableName3.equals("image15")) {
            Log.d("Car name", "Mitsubishi");
            selectedImage = "Mitsubishi";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image16") || drawableName2.equals("image16") || drawableName3.equals("image16")) {
            Log.d("Car name", "Nikola");
            selectedImage = "Nikola";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image17") || drawableName2.equals("image17") || drawableName3.equals("image17")) {
            Log.d("Car name", "Nissan");
            selectedImage = "Nissan";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image18") || drawableName2.equals("image18") || drawableName3.equals("image18")) {
            Log.d("Car name", "Patrol");
            selectedImage = "Patrol";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image19") || drawableName2.equals("image19") || drawableName3.equals("image19")) {
            Log.d("Car name", "Polestar");
            selectedImage = "Polestar";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image20") || drawableName2.equals("image20") || drawableName3.equals("image20")) {
            Log.d("Car name", "Porsche");
            selectedImage = "Porsche";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image21") || drawableName2.equals("image21") || drawableName3.equals("image21")) {
            Log.d("Car name", "Rolls Royce");
            selectedImage = "Rolls Royce";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image22") || drawableName2.equals("image22") || drawableName3.equals("image22")) {
            Log.d("Car name", "Saab");
            selectedImage = "Saab";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image23") || drawableName2.equals("image23") || drawableName3.equals("image23")) {
            Log.d("Car name", "Saturn");
            selectedImage = "Saturn";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image24") || drawableName2.equals("image24") || drawableName3.equals("image24")) {
            Log.d("Car name", "Subaru");
            selectedImage = "Subaru";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image25") || drawableName2.equals("image25") || drawableName3.equals("image25")) {
            Log.d("Car name", "Tesla");
            selectedImage = "Saab";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image26") || drawableName2.equals("image26") || drawableName3.equals("image26")) {
            Log.d("Car name", "Toyota");
            selectedImage = "Toyota";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        if (drawableName.equals("image27") || drawableName2.equals("image27") || drawableName3.equals("image27")) {
            Log.d("Car name", "Volvo");
            selectedImage = "Volvo";
            values.add(selectedImage);
            Log.d("Values", String.valueOf(values));
        }
        Log.d("Real Values", String.valueOf(values));
        int randomIndex = new Random().nextInt(values.size());
        randomName = values.get(randomIndex);
        displayRandomCarName.setText(randomName);
        Log.d("Random value", randomName);
    }

    //Check for correct answers and wrong answers
    @SuppressLint("SetTextI18n")
    public void submitAnswerView1(View view) {
        if (attempt != 1) {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i).equals(randomName)) {
                    Toast displayAnswer = new Toast(getApplicationContext());

                    TextView w = new TextView(ImageActivity.this);
                    w.setBackgroundColor(Color.RED);
                    w.setTextColor(Color.GREEN);
                    w.setTextSize(15);

                    Typeface f = Typeface.create("Serif", Typeface.BOLD_ITALIC);
                    w.setTypeface(f);
                    w.setText("Answer is Correct !");
                    displayAnswer.setView(w);
                    displayAnswer.show();
                    break;
                }
                else {

                    Toast displayWrongAnswer = new Toast(getApplicationContext());

                    TextView a = new TextView(ImageActivity.this);
                    a.setBackgroundColor(Color.WHITE);
                    a.setTextColor(Color.RED);
                    a.setTextSize(15);

                    Typeface w = Typeface.create("Serif", Typeface.BOLD_ITALIC);
                    a.setTypeface(w);
                    a.setText("Answer is Wrong !");
                    displayWrongAnswer.setView(a);
                    displayWrongAnswer.show();
                    attempt =+ 1;
                    break;
                }
            }
        }
        else{
            Toast attemptsError = new Toast(getApplicationContext());

            TextView a = new TextView(ImageActivity.this);
            a.setBackgroundColor(Color.BLACK);
            a.setTextColor(Color.RED);
            a.setTextSize(18);

            Typeface w = Typeface.create("Serif", Typeface.BOLD);
            a.setTypeface(w);
            a.setText("You have reached maximum number of attempts !");
            attemptsError.setView(a);
            attemptsError.show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void submitAnswerView2(View view) {
        if (attempt != 1) {
            for (int n = 1; n < values.size(); n++) {
                if (values.get(n).equals(randomName)) {
                    Toast displayAnswer = new Toast(getApplicationContext());

                    TextView w = new TextView(ImageActivity.this);
                    w.setBackgroundColor(Color.RED);
                    w.setTextColor(Color.GREEN);
                    w.setTextSize(15);

                    Typeface f = Typeface.create("Serif", Typeface.BOLD_ITALIC);
                    w.setTypeface(f);
                    w.setText("Answer is Correct !");
                    displayAnswer.setView(w);
                    displayAnswer.show();
                    break;
                } else {

                    Toast displayWrongAnswer = new Toast(getApplicationContext());

                    TextView a = new TextView(ImageActivity.this);
                    a.setBackgroundColor(Color.WHITE);
                    a.setTextColor(Color.RED);
                    a.setTextSize(15);

                    Typeface w = Typeface.create("Serif", Typeface.BOLD_ITALIC);
                    a.setTypeface(w);
                    a.setText("Answer is Wrong !");
                    displayWrongAnswer.setView(a);
                    displayWrongAnswer.show();
                    attempt =+1;
                    break;
                }
            }
        }
        else{
            Toast attemptsError2 = new Toast(getApplicationContext());

            TextView a = new TextView(ImageActivity.this);
            a.setBackgroundColor(Color.BLACK);
            a.setTextColor(Color.RED);
            a.setTextSize(18);

            Typeface w = Typeface.create("Serif", Typeface.BOLD);
            a.setTypeface(w);
            a.setText("You have reached maximum number of attempts !");
            attemptsError2.setView(a);
            attemptsError2.show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void submitAnswerView3(View view) {
        if (attempt != 1) {
            for (int i = 2; i < values.size(); i++) {
                if (values.get(i).equals(randomName)) {
                    Toast displayAnswer = new Toast(getApplicationContext());

                    TextView w = new TextView(ImageActivity.this);
                    w.setBackgroundColor(Color.RED);
                    w.setTextColor(Color.GREEN);
                    w.setTextSize(15);

                    Typeface f = Typeface.create("Serif", Typeface.BOLD_ITALIC);
                    w.setTypeface(f);
                    w.setText("Answer is Correct !");
                    displayAnswer.setView(w);
                    displayAnswer.show();
                    break;
                }
                else {

                    Toast displayWrongAnswer = new Toast(getApplicationContext());

                    TextView a = new TextView(ImageActivity.this);
                    a.setBackgroundColor(Color.WHITE);
                    a.setTextColor(Color.RED);
                    a.setTextSize(15);

                    Typeface w = Typeface.create("Serif", Typeface.BOLD_ITALIC);
                    a.setTypeface(w);
                    a.setText("Answer is Wrong !");
                    displayWrongAnswer.setView(a);
                    displayWrongAnswer.show();
                    attempt =+ 1;
                    break;
                }
            }
        }
        else{
            Toast attemptsError3 = new Toast(getApplicationContext());

            TextView a = new TextView(ImageActivity.this);
            a.setBackgroundColor(Color.BLACK);
            a.setTextColor(Color.RED);
            a.setTextSize(18);

            Typeface w = Typeface.create("Serif", Typeface.BOLD);
            a.setTypeface(w);
            a.setText("You have reached maximum number of attempts !");
            attemptsError3.setView(a);
            attemptsError3.show();
        }
    }
}