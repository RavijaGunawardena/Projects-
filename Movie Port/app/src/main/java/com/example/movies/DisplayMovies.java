package com.example.movies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DisplayMovies extends AppCompatActivity implements View.OnClickListener {

    TextView genTextBoxes;
    private CheckBox cb;
    int checked = 0;
    int x;
    ScrollView sv;
    LinearLayout ll;
    String getName;
    Button addToFavourite;
    List<CheckBox> listCheckBoxes = new LinkedList<>(); //To store check boxes

    ArrayList<String> sortedList = new ArrayList();

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);
        addToFavourite = (Button) findViewById(R.id.button8);
        addToFavourite.setOnClickListener(this);


        //Get data from db and store in a listCheckBoxes
        DataBase db = new DataBase(this);
        List<MovieModel> selectAll = db.getAllMovies();

        //Create Scroll view and Layout
        sv = (ScrollView) findViewById(R.id.sv);
        ll = (LinearLayout) findViewById(R.id.ll);

        //To Display a message if movies are not exist
        if (selectAll.size() == 0) {
            TextView tv = new TextView(this);
            tv.setPadding(280, 830, 0, 0);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            tv.setTextColor(Color.RED);
            tv.setText(R.string.noMoviesToDisplay);
            ll.addView(tv);
            addToFavourite.setVisibility(View.INVISIBLE);
        }

        //Add sorted data to sortedList
        for (int i = 0; i < selectAll.size(); i++) {
            getName = String.valueOf(selectAll.get(i).getMovieName());
            if (!getName.equals("null")) {
                sortedList.add(getName);
            }
        }

        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        for (x = 0; x < sortedList.size(); x++) {
            //Create TextBoxes
            genTextBoxes = new TextView(this);
            genTextBoxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            genTextBoxes.setTextColor(Color.BLACK);
            genTextBoxes.setPadding(50, 80, 0, 0);
            ll.addView(genTextBoxes);

            //Create Check boxes
            cb = new CheckBox(getApplicationContext());
            cb.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            cb.setBackground(this.getResources().getDrawable(R.drawable.checkbox));
            cb.setText(sortedList.get(x));
            listCheckBoxes.add(cb);
            cb.setTextColor(Color.WHITE);
            cb.setTextSize(17);
            cb.setPadding(50, 0, 0, 0);
            ll.setPadding(20, 0, 20, 0 );
            ll.addView(cb);
        }

    }

    //Button on click method
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        MovieModel addFavourites;
        for (CheckBox cb : listCheckBoxes) {
            if (cb.isChecked()) {
                System.out.println(listCheckBoxes);
                checked = checked + 1;
                addFavourites = new MovieModel(-1, null, 0, null, null, 0, null, cb.getText().toString());
                DataBase dataBase = new DataBase(this);
                dataBase.addMovieToTheDataBase(addFavourites);
            }
        }
        Toast toast = Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT);
        toast.show();
    }
}