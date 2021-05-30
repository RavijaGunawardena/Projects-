package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Create variables
    private static final long START_TIME_IN_MILLIS = 600000;
    public TextView mTextViewCountDown;
    public CountDownTimer mCountDownTimer;
    public boolean mTimerRunning;
    public String timeLeftFormatted;
    public long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    String runTimerInActivities;
    Switch timerSwitch;
    Intent startCarMakeTimer;
    @SuppressLint("StaticFieldLeak")
    static MainActivity sendData;
    String pauseTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sendData = this;
        //remove action bar
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toSecActivity = findViewById(R.id.carMake);
        Button toThirdActivity = findViewById(R.id.hint);
        Button tOForthActivity = findViewById(R.id.image);
        Button toAdvanceActivity = findViewById(R.id.advance);
        timerSwitch = (Switch) findViewById(R.id.switch1);

        pauseTimer = "";
        runTimerInActivities = "NoTimer";
        //Assign timer switch
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(timerSwitch.isChecked()){
                    startTimer();
                    runTimerInActivities = "Timers";
                }
                else {
                    runTimerInActivities = "NoTimer";
                }
            }
        });

        //To move between activities
        toSecActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToSecondActivity();
            }
        });

        toThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToThirdActivity();
            }
        });

        tOForthActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveTOForthActivity();
            }
        });
        toAdvanceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveToAdvanceActivity();
            }
        });

        updateCountDownText();

    }

    //Update timer
    public void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

    }

    //start timer
    public void startTimer() {
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

    //Methods to move between activities
    private void MoveToSecondActivity(){
        if (runTimerInActivities.equals("NoTimer")) {
            startCarMakeTimer = new Intent(getApplicationContext(), CarMakeActivity.class);
            String strName = "withoutTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }
        else {
            startCarMakeTimer = new Intent(getApplicationContext(), CarMakeActivity.class);
            String strName = "firstTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }
    }

    private void MoveToThirdActivity(){
        if (runTimerInActivities.equals("NoTimer")) {
            startCarMakeTimer = new Intent(getApplicationContext(), HintActivity.class);
            String strName = "withoutTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }
        else {
            startCarMakeTimer = new Intent(getApplicationContext(), HintActivity.class);
            String strName = "firstTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }
    }

    private void MoveTOForthActivity(){

        if (runTimerInActivities.equals("NoTimer")) {
            startCarMakeTimer = new Intent(getApplicationContext(), ImageActivity.class);
            String strName = "withoutTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }
        else {
            startCarMakeTimer = new Intent(getApplicationContext(), ImageActivity.class);
            String strName = "firstTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }

    }

    private void MoveToAdvanceActivity() {

        if (runTimerInActivities.equals("NoTimer")) {
            startCarMakeTimer = new Intent(getApplicationContext(), AdvanceActivity.class);
            String strName = "withoutTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }
        else {
            startCarMakeTimer = new Intent(getApplicationContext(), AdvanceActivity.class);
            String strName = "firstTimer";
            startCarMakeTimer.putExtra("StartTimer", strName);
            startActivity(startCarMakeTimer);
        }

    }

}