package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    EditText ed;
    DataBase db;
    Cursor cursor;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ed = (EditText) findViewById(R.id.editTextTextPersonName2);
        textView1 = (TextView) findViewById(R.id.textView13);
        textView2 = (TextView) findViewById(R.id.textView19);
        textView3 = (TextView) findViewById(R.id.textView20);
        textView4 = (TextView) findViewById(R.id.textView21);
        textView5 = (TextView) findViewById(R.id.textView22);
        textView6 = (TextView) findViewById(R.id.textView23);

        db = new DataBase(this);

        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // get a Cursor with the extracted movies
                cursor = db.getSearchData(ed.getText().toString());
                // Loop Through the Cursor
                while (cursor.moveToNext()) {
                    textView1.setText("Movie Name - " + cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_MOVIE_NAME)));
                    textView2.setText("Movie Year - " + cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_MOVIE_YEAR)));
                    textView3.setText("Movie Director - " + cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_MOVIE_DIRECTOR)));
                    textView4.setText("Movie Actors/Actresses - " + cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_MOVIE_ACTORS)));
                    textView5.setText("Movie Ratings - " + cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_MOVIE_RATINGS)));
                    textView6.setText("Movie Reviews - " + cursor.getString(cursor.getColumnIndex(DataBase.COLUMN_MOVIE_REVIEWS)));
                }

                if (cursor.getCount() < 1) {
                    textView1.setText(R.string.noResult);
                    textView2.setText(R.string.noResult);
                    textView3.setText(R.string.noResult);
                    textView4.setText(R.string.noResult);
                    textView5.setText(R.string.noResult);
                    textView6.setText(R.string.noResult);
                }
                cursor.close(); //Should always close Cursors when done with them
            }

            @Override
            public void afterTextChanged(Editable s) {
                
            }
        });

    }

}