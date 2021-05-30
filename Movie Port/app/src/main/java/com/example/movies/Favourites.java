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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Favourites extends AppCompatActivity implements View.OnClickListener{

    TextView genTextBoxes;
    private CheckBox cb;
    int checked = 0;
    ScrollView sv;
    LinearLayout ll;
    String getName;
    Button addToFavourite;
    List<CheckBox> listCheckBoxes = new LinkedList<>(); //To store check boxes
    List <MovieModel> selectAll;
    ArrayList <String> favouritesList = new ArrayList<>();
    DataBase db;

    ArrayList<String> sortedList = new ArrayList();

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        addToFavourite = (Button) findViewById(R.id.button9);
        addToFavourite.setOnClickListener(this);

        //Get data from db and store in a selectAll
        db = new DataBase(this);
        selectAll = db.getAllMovies();
        Log.d("Select All", String.valueOf(selectAll));

        //Create Scroll view and Layout
        sv = (ScrollView) findViewById(R.id.sv);
        ll = (LinearLayout) findViewById(R.id.ll);

        //To remove duplicates values from ArrayList
        HashSet<String> hashSet = new HashSet<String>();
        //Add sorted data to sortedList
        for (int i = 0; i < selectAll.size(); i++) {
            int r = 0;
            getName = String.valueOf(selectAll.get(i).getFavourites());
            if (!getName.equals("null")) {
                favouritesList.add(getName);
                sortedList.add(getName);
                hashSet.addAll(sortedList);
                sortedList.clear();
                sortedList.addAll(hashSet);
            }
        }

        if (favouritesList.size() == 0) {
            TextView tv = new TextView(this);
            tv.setPadding(320, 830, 0, 0);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            tv.setTextColor(Color.RED);
            tv.setText(R.string.noFavourites);
            ll.addView(tv);
            addToFavourite.setVisibility(View.INVISIBLE);
        }


        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        Log.d("sssssssssssssss", String.valueOf(sortedList));
        for (int x = 0; x< sortedList.size(); x++) {

            genTextBoxes = new TextView(this);
            genTextBoxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            genTextBoxes.setTextColor(Color.parseColor("#FF494575"));
            genTextBoxes.setPadding(50, 80, 0, 0);
            ll.addView(genTextBoxes);

            //Create Check boxes

            cb = new CheckBox(getApplicationContext());
            cb.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            cb.setBackground(this.getResources().getDrawable(R.drawable.checkbox));
            cb.setChecked(true);
            cb.setText(sortedList.get(x));
            cb.setId(x);
            listCheckBoxes.add(cb);
            cb.setTextColor(Color.WHITE);
            cb.setTextSize(17);
            cb.setPadding(50, 0, 0, 0);
            ll.setPadding(20, 0, 20, 0 );
            ll.addView(cb);
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        for(CheckBox cb: listCheckBoxes){
            if(cb.isChecked()) {
                Log.d("Checked", "chk");
            }
            else{
                //Delete movie from checkbox if it is unchecked
                db.deleteMovie(cb.getText().toString(), null);
                cb.setEnabled(false);
                cb.setTextColor(Color.RED);
                cb.setBackgroundColor(Color.WHITE);
                cb.setText("Removed " + cb.getText().toString() + " From Your Favourites");
                Toast toast = Toast.makeText(getApplicationContext(), R.string.successDelete, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}