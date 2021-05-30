package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class IMDBDetails extends AppCompatActivity {

    ImageView img;
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_m_d_b_details);
        img = (ImageView)findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView32);
        textView1 = (TextView) findViewById(R.id.textView27);
        textView2 = (TextView) findViewById(R.id.textView25);
        textView3 = (TextView) findViewById(R.id.textView26);

        ArrayList<String> receivedData = (ArrayList<String>) getIntent().getSerializableExtra("ReceiveData");

        Log.d("Display data", String.valueOf(receivedData));
        String year = receivedData.get(0);
        String rating = receivedData.get(1);
        String name = receivedData.get(2);
        String url = receivedData.get(3);
        textView.setText(name);
        textView1.setText("IMDB Ratings and Released Date Of the Movie " + name + " is shown below respectively");
        textView2.setText(rating);
        if (textView2.getText().toString().equals("")) {
            textView2.setTextColor(Color.RED);
            textView2.setTextSize(13);
            textView2.setText(R.string.noData);
        }
        textView3.setText(year);
        if (textView3.getText().toString().equals("")) {
            textView3.setTextColor(Color.RED);
            textView3.setTextSize(15);
            textView3.setText(R.string.noData);
        }
        new DownLoadImageTask(img).execute(url);

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView img;

        public DownLoadImageTask(ImageView imageView){
            this.img = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            }
            catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }
        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            img.setImageBitmap(result);
        }
    }
}