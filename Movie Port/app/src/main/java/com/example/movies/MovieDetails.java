package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MovieDetails extends AppCompatActivity {

    String getRatings;
    String getDirector;
    String getId;
    String getReviews;
    String getActors;
    String getMovieName;
    String getFavStatus;
    int getYear;
    int ratingScore = 0;
    int r;
    TextView textView;
    EditText editDirector;
    EditText yearEdit;
    EditText editActor;
    EditText editRatings;
    EditText editReviews;
    Button edit;
    Button edit2;
    Button edit3;
    Button edit4;
    Button edit5;
    Button favourites;
    DataBase db;

    MovieModel addFavourites;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        textView = (TextView) findViewById(R.id.textView10);
        editDirector = (EditText) findViewById(R.id.editTextTextPersonName8);
        yearEdit = (EditText) findViewById(R.id.editTextTextPersonName9);
        editActor = (EditText) findViewById(R.id.editTextTextPersonName10);
        editRatings = (EditText) findViewById(R.id.editTextTextPersonName11);
        editReviews = (EditText) findViewById(R.id.editTextTextPersonName13);
        edit = (Button) findViewById(R.id.button10);
        edit2 = (Button) findViewById(R.id.button12);
        edit3 = (Button) findViewById(R.id.button13);
        edit4 = (Button) findViewById(R.id.button14);
        edit5 = (Button) findViewById(R.id.button15);
        favourites = (Button) findViewById(R.id.button11);

        ArrayList<String> receivedData = (ArrayList<String>) getIntent().getSerializableExtra("ReceiveData");

        Log.d("Display data", String.valueOf(receivedData));

        for (int i = 0; i < receivedData.size(); i++) {

            getId = receivedData.get(0);
            getMovieName = receivedData.get(1);
            getYear = Integer.parseInt(receivedData.get(2));
            getDirector = receivedData.get(3);
            getActors = receivedData.get(4);
            getRatings = receivedData.get(5);
            getReviews = receivedData.get(6);
            getFavStatus = receivedData.get(7);

            Log.d("Ratings", getRatings);
        }

        textView.setText(getMovieName);
        yearEdit.setText(String.valueOf(getYear));
        editActor.setText(getActors);
        editRatings.setText(getRatings);
        editReviews.setText(getReviews);
        editDirector.setText(getDirector);

        yearEdit.setEnabled(false);
        editActor.setEnabled(false);
        editRatings.setEnabled(false);
        editReviews.setEnabled(false);
        editDirector.setEnabled(false);


        editRatings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                     r = Integer.parseInt(editRatings.getText().toString());
                }
                catch (NumberFormatException e) {
                    simpleRatingBar.setRating((float) 0.0);
                }
                simpleRatingBar.setRating(r);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        int n = Integer.parseInt(getRatings);
        simpleRatingBar.setRating(n);
        simpleRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingScore = (int) ratingBar.getRating();
            }
        });

        //create database object
        db = new DataBase(this);

        final int[] btnState = {0};
        final int[] updateState = {0};
        final int[] btnState1 = {0};
        final int[] updateState1 = {0};
        final int[] btnState2 = {0};
        final int[] updateState2 = {0};
        final int[] btnState3 = {0};
        final int[] updateState3 = {0};
        final int[] btnState4 = {0};
        final int[] updateState4 = {0};

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnState[0] == 0) {
                    editDirector.setEnabled(true);
                    edit.setText(R.string.update);
                    btnState[0] = btnState[0] + 1;
                }
                updateState[0] = updateState[0] + 1;
                if (updateState[0] == 2) {
                    try {
                        String n = editDirector.getText().toString();
                        Log.d("n", n);
                        boolean isUpdated = db.update(getId, editDirector.getText().toString(), Integer.parseInt(yearEdit.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editDirector.setEnabled(false);
                        edit.setText(R.string.edit);
                        btnState[0] = 0;
                        updateState[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }

                    } catch (NumberFormatException e) {
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.emptyFields, Toast.LENGTH_SHORT);
                        Log.d("Updated", "No");
                        btnState[0] = 0;
                        updateState[0] = 0;
                        toast.show();
                    }
                }
            }
        });

        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnState1[0] == 0) {
                    yearEdit.setEnabled(true);
                    edit2.setText(R.string.update);
                    btnState1[0] = btnState1[0] + 1;
                }
                updateState1[0] = updateState1[0] + 1;
                if (updateState1[0] == 2) {
                    try {
                        boolean isUpdated = db.update(getId, editDirector.getText().toString(), Integer.parseInt(yearEdit.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        yearEdit.setEnabled(false);
                        edit2.setText(R.string.edit);
                        btnState1[0] = 0;
                        updateState1[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }
                    }
                    catch (NumberFormatException e) {
                        Log.d("Updated", "No");
                        btnState1[0] = 0;
                        updateState1[0] = 0;
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.emptyFields, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnState2[0] == 0) {
                    editActor.setEnabled(true);
                    edit3.setText(R.string.update);
                    btnState2[0] = btnState2[0] + 1;
                }
                updateState2[0] = updateState2[0] + 1;
                try {
                    if (updateState2[0] == 2) {
                        boolean isUpdated = db.update(getId, editDirector.getText().toString(), Integer.parseInt(yearEdit.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editActor.setEnabled(false);
                        edit3.setText(R.string.edit);
                        btnState2[0] = 0;
                        updateState2[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }

                    }
                }
                catch(NumberFormatException e){
                    btnState2[0] = 0;
                    updateState2[0] = 0;
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.emptyFields, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnState3[0] == 0) {
                    editRatings.setEnabled(true);
                    edit4.setText(R.string.update);
                    btnState3[0] = btnState3[0] + 1;
                }
                updateState3[0] = updateState3[0] + 1;
                if (updateState3[0] == 2) {
                    try {
                        boolean isUpdated = db.update(getId, editDirector.getText().toString(), Integer.parseInt(yearEdit.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editRatings.setEnabled(false);
                        edit4.setText(R.string.edit);
                        btnState3[0] = 0;
                        updateState3[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }
                    }
                    catch (NumberFormatException e) {
                        btnState3[0] = 0;
                        updateState3[0] = 0;
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.emptyFields, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

        edit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnState4[0] == 0) {
                    editReviews.setEnabled(true);
                    edit5.setText(R.string.update);
                    btnState4[0] = btnState4[0] + 1;
                }
                updateState4[0] = updateState4[0] + 1;
                if (updateState4[0] == 2) {
                    try {
                        boolean isUpdated = db.update(getId, editDirector.getText().toString(), Integer.parseInt(yearEdit.getText().toString()), editActor.getText().toString(),
                                Integer.parseInt(editRatings.getText().toString()), editReviews.getText().toString());
                        editReviews.setEnabled(false);
                        edit5.setText(R.string.edit);
                        btnState4[0] = 0;
                        updateState4[0] = 0;
                        if (isUpdated) {
                            Log.d("Updated", "OK");
                        }
                    }
                    catch (NumberFormatException e) {
                        btnState4[0] = 0;
                        updateState4[0] = 0;
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.emptyFields, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
        DataBase dataBase = new DataBase(this);
        if (getFavStatus.equalsIgnoreCase("Not in Favourites")) {
            favourites.setText(R.string.addToFavourites);
            favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addFavourites = new MovieModel(-1, null, 0, null, null, 0, null, getMovieName);
                    boolean addSuccessFully = dataBase.addMovieToTheDataBase(addFavourites);
                    favourites.setText(R.string.addedToFavourites);
                    favourites.setEnabled(false);
                }
            });
        }

        else {
            favourites.setText(R.string.InYourFavourites);
            favourites.setEnabled(false);
        }

    }
}