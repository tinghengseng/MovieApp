package com.asengstudio.movieapp2.utils;

import android.util.Log;

import com.asengstudio.movieapp2.response.MovieDetailsResponse;
import com.asengstudio.movieapp2.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    //http://www.omdbapi.com/?apikey=b9bd48a6&s=Marvel&type=movie
    @GET("/")
    Call<MovieSearchResponse> searchMovie(
            @Query("apikey") String key,
            @Query("s") String query,
            @Query("type") String type,
            @Query("page") String page
    );

    @GET("/")
    Call<MovieDetailsResponse> getMovieDetails(
            @Query("apikey") String key,
            @Query("i") String imdbID
    );
}
