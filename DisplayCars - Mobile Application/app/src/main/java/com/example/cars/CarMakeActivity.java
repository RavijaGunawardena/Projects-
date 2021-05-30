package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class CarMakeActivity extends AppCompatActivity implements View.OnClickListener {

    //Create variables
    private int state = 0;
    String lastImageName;
    ImageView carImages;
    Button identifyToNext;
    Random ran;
    int pickedImages = 0;
    int lastPicked = 0;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String selectedCarImageName;
    String wrongAnswerDisplay;
    String timeStr;
    String getTime;
    int timeComplete = 0;
    public TextView mTextViewCountDown;
    private static final long START_TIME_IN_MILLIS = 20000;
    public CountDownTimer mCountDownTimer;
    public boolean mTimerRunning;
    public String timeLeftFormatted;
    public long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    Integer[] images = {R.drawable.audi, R.drawable.bugatti, R.drawable.rolls_royce, R.drawable.benz, R.drawable.dodge, R.drawable.fiat, R.drawable.ford,
            R.drawable.genesis, R.drawable.hyundai, R.drawable.infiniti, R.drawable.jaguar, R.drawable.jeep, R.drawable.lincoln, R.drawable.lotus, R.drawable.maserati,
            R.drawable.mitsubishi, R.drawable.nikola, R.drawable.nissan, R.drawable.patrol, R.drawable.porsche, R.drawable.saab, R.drawable.saturn, R.drawable.tesla,
            R.drawable.toyota, R.drawable.volvo};

    //Getting palette from hint activity using Ids
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_carmake);

        carImages = (ImageView) findViewById(R.id.imageRavView);
        identifyToNext = (Button) findViewById(R.id.button5);
        mTextViewCountDown = (TextView) findViewById(R.id.timeTextView5);
        identifyToNext.setOnClickListener(this);
        ran = new Random();
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

        //Create spinner
        spinner = (Spinner) findViewById(R.id.spinner);
        String current = String.valueOf(R.array.names);
        adapter = ArrayAdapter.createFromResource(this, Integer.parseInt(current), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Initialize with spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();

                if(parent.getItemAtPosition(position).equals("Audi")){
                    selectedCarImageName = "image1";
                }
                else if (parent.getItemAtPosition(position).equals("Bugatti")){
                    selectedCarImageName = "image2";
                }
                else if (parent.getItemAtPosition(position).equals("Benz")){
                    selectedCarImageName = "image4";
                }
                else if (parent.getItemAtPosition(position).equals("Dodge")){
                    selectedCarImageName = "image5";
                }
                else if (parent.getItemAtPosition(position).equals("Fiat")){
                    selectedCarImageName = "image6";
                }
                else if (parent.getItemAtPosition(position).equals("Ford")){
                    selectedCarImageName = "image7";
                }
                else if (parent.getItemAtPosition(position).equals("Genesis")){
                    selectedCarImageName = "image8";
                }
                else if (parent.getItemAtPosition(position).equals("Hyundai")){
                    selectedCarImageName = "image9";
                }
                else if (parent.getItemAtPosition(position).equals("Infinity")){
                    selectedCarImageName = "image10";
                }
                else if (parent.getItemAtPosition(position).equals("Jaguar")){
                    selectedCarImageName = "image11";
                }
                else if (parent.getItemAtPosition(position).equals("Jeep")){
                    selectedCarImageName = "image12";
                }
                else if (parent.getItemAtPosition(position).equals("Lincoln")){
                    selectedCarImageName = "image13";
                }
                else if (parent.getItemAtPosition(position).equals("Lotus")){
                    selectedCarImageName = "image14";
                }
                else if (parent.getItemAtPosition(position).equals("Maserati")){
                    selectedCarImageName = "image15";
                }
                else if (parent.getItemAtPosition(position).equals("Mitsubishi")){
                    selectedCarImageName = "image16";
                }
                else if (parent.getItemAtPosition(position).equals("Nikola")){
                    selectedCarImageName = "image17";
                }
                else if (parent.getItemAtPosition(position).equals("Nissan")){
                    selectedCarImageName = "image18";
                }
                else if (parent.getItemAtPosition(position).equals("Patrol")){
                    selectedCarImageName = "image19";
                }
                else if (parent.getItemAtPosition(position).equals("Porsche")){
                    selectedCarImageName = "image20";
                }
                else if (parent.getItemAtPosition(position).equals("Rolls Royce")){
                    selectedCarImageName = "image3";
                }
                else if (parent.getItemAtPosition(position).equals("Saab")){
                    selectedCarImageName = "image21";
                }
                else if (parent.getItemAtPosition(position).equals("Saturn")){
                    selectedCarImageName = "image22";
                }
                else if (parent.getItemAtPosition(position).equals("Tesla")){
                    selectedCarImageName = "image23";
                }
                else if (parent.getItemAtPosition(position).equals("Toyota")){
                    selectedCarImageName = "image24";
                }
                else if (parent.getItemAtPosition(position).equals("Volvo")){
                    selectedCarImageName = "image25";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                identifyToNext.setText("Next");
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
        getTime = mTextViewCountDown.getText().toString();
        if (state == 0) {
            // State 1
            identifyToNext.setText("Identify");
            do{
                int randInt = ran.nextInt(25) + 1;
                String drawableName = "image"+ randInt;
                int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                carImages.setImageResource(resID);
                pickedImages = ran.nextInt(images.length);
                lastImageName = drawableName;
            }
            while (pickedImages == lastPicked);

            lastPicked = pickedImages;
            carImages.setImageResource(images[pickedImages]);
            state = state + 1;
        }
        else if (state == 1) {
            // State 2
            ImageView backGroundImage = (ImageView) findViewById(R.id.imageRavView);
            if (selectedCarImageName.equals(lastImageName)){
                Toast correctAnswer = new Toast(getApplicationContext());

                TextView c = new TextView(CarMakeActivity.this);
                c.setBackgroundColor(Color.GREEN);
                c.setTextColor(Color.WHITE);
                c.setTextSize(15);

                Typeface o = Typeface.create("Serif", Typeface.BOLD);
                c.setTypeface(o);
                c.setText("Your answer is Correct");
                correctAnswer.setView(c);
                correctAnswer.show();
                identifyToNext.setText("Next");
                //return to previous state
                state = state - 1;
            }
            else{
                switch (lastImageName) {
                    case "image1":
                        wrongAnswerDisplay = "Correct Answer is Audi";
                        break;
                    case "image2":
                        wrongAnswerDisplay = "Correct Answer is Bugatti";
                        break;
                    case "image4":
                        wrongAnswerDisplay = "Correct Answer is Benz";
                        break;
                    case "image5":
                        wrongAnswerDisplay = "Correct Answer is Dodge";
                        break;
                    case "image6":
                        wrongAnswerDisplay = "Correct Answer is Fiat";
                        break;
                    case "image7":
                        wrongAnswerDisplay = "Correct Answer is Ford";
                        break;
                    case "image8":
                        wrongAnswerDisplay = "Correct Answer is Genesis";
                        break;
                    case "image9":
                        wrongAnswerDisplay = "Correct Answer is Hyundai";
                        break;
                    case "image10":
                        wrongAnswerDisplay = "Correct Answer is Infinity";
                        break;
                    case "image11":
                        wrongAnswerDisplay = "Correct Answer is Jaguar";
                        break;
                    case "image12":
                        wrongAnswerDisplay = "Correct Answer is Jeep";
                        break;
                    case "image13":
                        wrongAnswerDisplay = "Correct Answer is Lincoln";
                        break;
                    case "image14":
                        wrongAnswerDisplay = "Correct Answer is Lotus";
                        break;
                    case "image15":
                        wrongAnswerDisplay = "Correct Answer is Maserati";
                        break;
                    case "image16":
                        wrongAnswerDisplay = "Correct Answer is Mitsubishi";
                        break;
                    case "image17":
                        wrongAnswerDisplay = "Correct Answer is Nikola";
                        break;
                    case "image18":
                        wrongAnswerDisplay = "Correct Answer is Nissan";
                        break;
                    case "image19":
                        wrongAnswerDisplay = "Correct Answer is Patrol";
                        break;
                    case "image20":
                        wrongAnswerDisplay = "Correct Answer is Porsche";
                        break;
                    case "image3":
                        wrongAnswerDisplay = "Correct Answer is Rolls Royce";
                        break;
                    case "image21":
                        wrongAnswerDisplay = "Correct Answer is Saab";
                        break;
                    case "image22":
                        wrongAnswerDisplay = "Correct Answer is Saturn";
                        break;
                    case "image23":
                        wrongAnswerDisplay = "Correct Answer is Tesla";
                        break;
                    case "image24":
                        wrongAnswerDisplay = "Correct Answer is Toyota";
                        break;
                    case "image25":
                        wrongAnswerDisplay = "Correct Answer is Volvo";
                        break;
                }

                //Display wrong answer using toast
                Toast showCorrectAnswer = new Toast(getApplicationContext());

                TextView w = new TextView(CarMakeActivity.this);
                w.setBackgroundColor(Color.WHITE);
                w.setTextColor(Color.RED);
                w.setTextSize(15);

                Typeface f = Typeface.create("Serif", Typeface.BOLD);
                w.setTypeface(f);
                w.setText("Answer is Wrong ! " + wrongAnswerDisplay);
                showCorrectAnswer.setView(w);
                showCorrectAnswer.show();
            }
        }
    }
}