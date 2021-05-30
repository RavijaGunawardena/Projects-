package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class EditMovies extends AppCompatActivity {

    List <Button> buttons = new LinkedList<>(); // To store Buttons
    List<MovieModel> selectAll;
    TextView genTextBoxes;
    LinearLayout ll2;
    ScrollView sv2;
    String getName;
    String fav;
    DataBase db;
    int x;

    ArrayList<String> sortedList = new ArrayList();
    ArrayList<String> favList = new ArrayList();
    ArrayList<String> sendData = new ArrayList();

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movies);

        ll2 = (LinearLayout) findViewById(R.id.ll3);
        sv2 = (ScrollView) findViewById(R.id.sv3);

        //Get data from db and store in a selectAll
        db = new DataBase(this);
        selectAll = db.getAllMovies();
        Log.d("Select All", String.valueOf(selectAll));

        //To Display a message if movies are not exist
        if (selectAll.size() == 0) {
            TextView tv = new TextView(this);
            tv.setPadding(280, 830, 0, 0);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            tv.setTextColor(Color.RED);
            tv.setText(R.string.noMoviesToDisplay);
            ll2.addView(tv);
        }

        //Add sorted data to sortedList
        for (int i = 0; i < selectAll.size(); i++) {
            getName = String.valueOf(selectAll.get(i).getMovieName());
            if (!getName.equals("null")) {
                sortedList.add(getName);
            }
        }

        for (int q = 0; q < selectAll.size(); q++) {
            fav = String.valueOf(selectAll.get(q).getFavourites());
            if (!fav.equals("null")) {
                favList.add(fav);
            }
        }

        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        Log.d("sssssssssssssss", String.valueOf(sortedList));
        Button [] btn = new Button[sortedList.size()];
        for (x = 0; x < sortedList.size(); x++) {
            //Create TextBoxes
            genTextBoxes = new TextView(this);
            genTextBoxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            genTextBoxes.setTextColor(Color.WHITE);
            genTextBoxes.setPadding(50, 80, 0, 0);
            ll2.addView(genTextBoxes);

            //Create Check boxes

            btn[x] = new Button(this);
            btn[x].setBackgroundColor(Color.parseColor("#FF9800"));
            btn[x].setText(sortedList.get(x));
            btn[x].setId(x);
            btn[x].setTextColor(Color.WHITE);
            btn[x].setTextSize(19);
            btn[x].setPadding(50, 40, 0, 0);
            btn[x].setBackground(this.getResources().getDrawable(R.drawable.button_ratings));
            ll2.setPadding(60, 0 , 60, 0);
            ll2.addView(btn[x]);
            Log.d("Name ------", btn[x].getText().toString());
            buttons.add(btn[x]);
            btn[x].setOnClickListener(handleOnClick(btn[x]));
        }
    }

    //Set Onclick listener to the button
    View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MovieDetails.class);

                for (int k = 0; k < selectAll.size(); k++){
                    if (button.getText().toString().equalsIgnoreCase(selectAll.get(k).getMovieName())) {

                        String movieName = selectAll.get(k).getMovieName();
                        int id = selectAll.get(k).getId();
                        int year = selectAll.get(k).getMovieYear();
                        String dir = selectAll.get(k).getDirector();
                        String act = selectAll.get(k).getActors();
                        int ratings = selectAll.get(k).getRatings();
                        String reviews = selectAll.get(k).getReviews();

                        String status = "Not in Favourites";
                        for (int m = 0; m < favList.size(); m ++) {
                            if (favList.get(m).equalsIgnoreCase(button.getText().toString())) {
                                status = "In Favourites";
                                break;
                            }
                        }
                        sendData.clear();
                        sendData.add(String.valueOf(id));
                        sendData.add(movieName);
                        sendData.add(String.valueOf(year));
                        sendData.add(dir);
                        sendData.add(act);
                        sendData.add(String.valueOf(ratings));
                        sendData.add(reviews);
                        sendData.add(status);
                        Log.d("send", String.valueOf(sendData));

                        // key is ReceiveData, by this key we will receive the value, and put the string
                        intent.putExtra("ReceiveData", sendData);

                        // start the Intent
                        startActivity(intent);
                    }
                }
            }
        };
    }
}