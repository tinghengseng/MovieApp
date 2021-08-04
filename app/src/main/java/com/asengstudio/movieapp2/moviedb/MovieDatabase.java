package com.asengstudio.movieapp2.moviedb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.asengstudio.movieapp2.models.MovieModel;

@Database(entities = {Movie.class, MovieDetails.class} , version = 1)
// @TypeConverters({DataRoomConverter.class})
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao getMovieDao();

    private static MovieDatabase movieDB;

    public static MovieDatabase getInstance(Context context) {
        if (movieDB == null) {
            movieDB = buildDatabaseInstance(context);
        }
        return movieDB;
    }

    private static MovieDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                MovieDatabase.class,
                Constants.DB_NAME).allowMainThreadQueries().build();
    }

    public void cleanUp() {
        movieDB = null;
    }
}
