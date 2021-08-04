package com.asengstudio.movieapp2.moviedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM " + Constants.TABLE_MOVIE)
    List<Movie> getMovies();

    @Query("SELECT * FROM " + Constants.TABLE_MOVIE_DETAILS)
    List<MovieDetails> getMovieDetails();

    @Query("SELECT * FROM " + Constants.TABLE_MOVIE_DETAILS + " WHERE imdb_id = :imdbID")
    MovieDetails getMovieDetail(String imdbID);

    @Query("SELECT COUNT() FROM " + Constants.TABLE_MOVIE + " WHERE imdbID = :imdbID")
    int checkMovieExists(String imdbID);

    @Query("SELECT COUNT() FROM " + Constants.TABLE_MOVIE_DETAILS + " WHERE imdb_id = :imdbID")
    int checkMovieDetailsExists(String imdbID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertMovie(Movie movie);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertMovieDetails(MovieDetails movieDetails);

    @Update
    void updateMovie(Movie repos);

    @Delete
    void deleteMovie(Movie movie);

    // Delete all
    @Delete
    void deleteMovies(Movie... movie);


}
