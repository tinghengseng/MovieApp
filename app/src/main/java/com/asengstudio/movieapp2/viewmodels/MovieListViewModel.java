package com.asengstudio.movieapp2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asengstudio.movieapp2.models.MovieModel;
import com.asengstudio.movieapp2.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    // Constructor
    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }

    // 3. Calling method in view-model
    public void searchMovieApi(String query, int pageNumber) {
        movieRepository.searchMovieApi(query, pageNumber);
    }

    public void searchNextPage(){
        movieRepository.searchNextPage();
    }
}
