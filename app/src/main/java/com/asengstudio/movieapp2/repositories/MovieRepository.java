package com.asengstudio.movieapp2.repositories;

import android.graphics.Movie;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asengstudio.movieapp2.models.MovieDetailsModel;
import com.asengstudio.movieapp2.models.MovieModel;
import com.asengstudio.movieapp2.request.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;

    public static MovieRepository getInstance() {
        if(instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }

    public LiveData<MovieDetailsModel> getMovieDetails() {
        return movieApiClient.getMovieDetails();
    }

    // 2. Calling the method in repository
    public void searchMovieApi(String query, int pageNumber) {
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery, mPageNumber+1);
    }

    public void getMovieDetailsApi(String imdbID) {
        movieApiClient.getMovieDetailsApi(imdbID);
    }
}


