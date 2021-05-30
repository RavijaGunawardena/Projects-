package com.example.movies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tomer.fadingtextview.FadingTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Ratings extends AppCompatActivity {

    ScrollView sv;
    LinearLayout ll;
    int x;
    TextView genTextBoxes;
    String getName;
    RequestQueue idRequest;
    RequestQueue requestQueue;
    FadingTextView fadingTextView;
    ArrayList<String> sortedList = new ArrayList();
    List <Button> buttons = new LinkedList<>(); // To store Buttons

    String[] text
            = { "Click Movie Name To Find IMDB Details"};

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);

        DataBase db = new DataBase(this);
        List<MovieModel> selectAll = db.getAllMovies();

        //Create Scroll view and Layout
        sv = (ScrollView) findViewById(R.id.scrollViewRating);
        ll = (LinearLayout) findViewById(R.id.linearRatings);
        fadingTextView = findViewById(R.id.textView24);
        fadingTextView.setTexts(text);

        //To Display a message if movies are not exist
        if (selectAll.size() == 0) {
            String[] text
                    = { "No Registered Movies ! "};
            fadingTextView.setTexts(text);
        }

        //Add sorted data to sortedList
        for (int i = 0; i < selectAll.size(); i++) {
            getName = String.valueOf(selectAll.get(i).getMovieName());
            if (!getName.equals("null")) {
                sortedList.add(getName);
            }
        }

        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        Log.d("sssssssssssssss", String.valueOf(sortedList));
        Button[] btn = new Button[sortedList.size()];
        for (x = 0; x < sortedList.size(); x++) {
            //Create TextBoxes
            genTextBoxes = new TextView(this);
            genTextBoxes.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            genTextBoxes.setTextColor(Color.WHITE);
            genTextBoxes.setPadding(50, 80, 0, 0);
            ll.addView(genTextBoxes);

            //Create Check boxes

            btn[x] = new Button(this);
            btn[x].setBackgroundColor(Color.parseColor("#FF9800"));
            btn[x].setText(sortedList.get(x));
            btn[x].setId(x);
            btn[x].setTextColor(Color.WHITE);
            btn[x].setTextSize(19);
            btn[x].setPadding(50, 55, 0, 55);
            btn[x].setAlpha((float) 0.85);
            btn[x].setBackground(this.getResources().getDrawable(R.drawable.button_ratings));
            ll.setPadding(60, 0 , 60, 0);
            ll.addView(btn[x]);
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
                Intent intent = new Intent(getApplicationContext(), IMDBDetails.class);

                String getValue = button.getText().toString().toLowerCase();
                requestQueue = Volley.newRequestQueue(Ratings.this);
                idRequest = Volley.newRequestQueue(Ratings.this);
                //Backup url
//                String movieDetailsUrl = "https://imdb-api.com/en/API/Search/k_jh0fvwxh/" + getValue;
                String movieDetailsUrl = "https://imdb-api.com/en/API/Search/k_80apy9zi/" + getValue;

                StringRequest req = new StringRequest(Request.Method.GET, movieDetailsUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Image", response);
                        try {
                            JSONObject movieDetails = new JSONObject(response);
                            JSONArray params = movieDetails.getJSONArray("results");
                            JSONObject param1 = params.getJSONObject(0);
                            String result = param1.getString("id");
                            String urlImage = param1.getString("image");

//                            String movieIdUrl = "https://imdb-api.com/en/API/UserRatings/k_jh0fvwxh/" + result;
                            String movieIdUrl = "https://imdb-api.com/en/API/UserRatings/k_80apy9zi/" + result;
                            StringRequest idReq = new StringRequest(Request.Method.GET, movieIdUrl, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("User Rating", response);

                                    try {
                                        //Get json data through the API
                                        JSONObject movieDetails = new JSONObject(response);
                                        String year = movieDetails.getString("year");
                                        String title = movieDetails.getString("fullTitle");
                                        String ratings = movieDetails.getString("totalRating");
                                        Log.d("Movie Rating Is Now", ratings);
                                        Log.d("Movie Rating Is Now", urlImage);
                                        Log.d("Movie Rating Is Now", year);
                                        Log.d("Movie Rating Is Now", title);

                                        ArrayList <String> detailsMovie = new ArrayList();
                                        detailsMovie.add(year);
                                        detailsMovie.add(ratings);
                                        detailsMovie.add(title);
                                        detailsMovie.add(urlImage);

                                        // key is ReceiveData, by this key we will receive the value, and put the string
                                        intent.putExtra("ReceiveData", detailsMovie);

                                        // start the Intent
                                        startActivity(intent);
                                    }

                                     catch (JSONException e) {
                                        Log.d("Error Message", "Error When Getting Value");
                                    }
                                }
                            },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) { }
                                    });
                            idRequest.add(idReq);

                        }
                        catch (JSONException e) {
                            Log.d("Error Message", "Error When Getting Value");

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                });

                requestQueue.add(req);
            }
        };
    }
}