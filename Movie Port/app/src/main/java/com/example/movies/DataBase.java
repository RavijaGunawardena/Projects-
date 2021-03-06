package com.example.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    public static final String MOVIES_TABLE = "MOVIES_TABLE";
    public static final String COLUMN_MOVIE_NAME = "MOVIES_NAME";
    public static final String COLUMN_MOVIE_YEAR = "MOVIES_YEAR";
    public static final String COLUMN_MOVIE_DIRECTOR = "MOVIES_DIRECTOR";
    public static final String COLUMN_MOVIE_ACTORS = "MOVIES_ACTORS";
    public static final String COLUMN_MOVIE_RATINGS = "MOVIES_RATINGS";
    public static final String COLUMN_MOVIE_REVIEWS = "MOVIES_REVIEWS";
    public static final String COLUMN_MOVIE_FAVOURITES = "MOVIES_FAVOURITES";
    public static final String COLUMN_ID = "ID";

    public DataBase(@Nullable Context context) {
        super(context, "Movies.db", null, 1);
    }

    //TO create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + MOVIES_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MOVIE_NAME + " TEXT, " + COLUMN_MOVIE_YEAR + " INT, " + COLUMN_MOVIE_DIRECTOR + " TEXT, " + COLUMN_MOVIE_ACTORS + " TEXT, " + COLUMN_MOVIE_RATINGS + " INT, " + COLUMN_MOVIE_REVIEWS + " TEXT, " + COLUMN_MOVIE_FAVOURITES + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    //Add movies to data base
    public boolean addMovieToTheDataBase (MovieModel movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MOVIE_NAME, movie.getMovieName());
        cv.put(COLUMN_MOVIE_YEAR, movie.getMovieYear());
        cv.put(COLUMN_MOVIE_DIRECTOR, movie.getDirector());
        cv.put(COLUMN_MOVIE_ACTORS, movie.getActors());
        cv.put(COLUMN_MOVIE_RATINGS, movie.getRatings());
        cv.put(COLUMN_MOVIE_REVIEWS, movie.getReviews());
        cv.put(COLUMN_MOVIE_FAVOURITES, movie.getFavourites());

        long inset = db.insert(MOVIES_TABLE, null, cv);
        if (inset == -1){
            return false;
        }
        else {
            return true;
        }
    }

    //Get all movies and return 'data' list
    public List <MovieModel> getAllMovies() {
        //To store and send values
        List <MovieModel> returnData = new ArrayList<>();

        //Get data from the database

        String string = "SELECT * FROM " + MOVIES_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(string, null);

        if (cursor.moveToFirst()) {
            //Loop through the all data in the database
            do{
                int id = cursor.getInt(0);
                String movieName = cursor.getString(1);
                int  movieYear = cursor.getInt(2);
                String movieDirectors = cursor.getString(3);
                String movieActors = cursor.getString(4);
                int ratings = cursor.getInt(5);
                String reviews = cursor.getString(6);
                String favourites = cursor.getString(7);

                MovieModel newMovie = new MovieModel(id, movieName, movieYear, movieDirectors, movieActors, ratings, reviews, favourites);
                returnData.add(newMovie);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnData;
    }

    //Search for data
    public Cursor getSearchData(String value) {
        return this.getWritableDatabase().query(MOVIES_TABLE, null, COLUMN_MOVIE_NAME + " LIKE '%" + value + "%' " + " OR " + COLUMN_MOVIE_DIRECTOR + " LIKE '%" + value + "%' "  + " OR "
                        + COLUMN_MOVIE_ACTORS + " LIKE '%" + value + "%' ",
                null, null, null, null
        );
    }

    //Delete data from data base
    public void deleteMovie(String name, Integer cha){
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM " + MOVIES_TABLE + " WHERE " + COLUMN_MOVIE_FAVOURITES + " = '" + name + "'";
        db.execSQL(delete);
    }

    //Update data base and return boolean value
    public boolean update(String id, String director, int year, String actors, int ratings, String reviews){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_MOVIE_DIRECTOR,director);
        contentValues.put(COLUMN_MOVIE_YEAR,year);
        contentValues.put(COLUMN_MOVIE_ACTORS,actors);
        contentValues.put(COLUMN_MOVIE_RATINGS,ratings);
        contentValues.put(COLUMN_MOVIE_REVIEWS,reviews);
        db.update(MOVIES_TABLE, contentValues, "ID = ?",new String[] {id});
        return true;
    }
}
