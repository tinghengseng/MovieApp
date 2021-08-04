package com.asengstudio.movieapp2.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.asengstudio.movieapp2.models.MovieDetailsModel;
import com.asengstudio.movieapp2.models.MovieModel;
import com.asengstudio.movieapp2.repositories.MovieRepository;

import java.util.List;

public class MovieDetailsViewModel extends ViewModel {

    private MovieRepository movieRepository;

    // Constructor
    public MovieDetailsViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<MovieDetailsModel> getMovieDetails() {
        return movieRepository.getMovieDetails();
    }

    // 3. Calling method in view-model
    public void getMovieDetailsApi(String imdbID) {
        movieRepository.getMovieDetailsApi(imdbID);

    }
}
