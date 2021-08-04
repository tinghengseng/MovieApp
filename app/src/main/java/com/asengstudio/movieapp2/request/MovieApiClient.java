package com.asengstudio.movieapp2.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asengstudio.movieapp2.AppExecutors;
import com.asengstudio.movieapp2.models.MovieDetailsModel;
import com.asengstudio.movieapp2.models.MovieModel;
import com.asengstudio.movieapp2.response.MovieDetailsResponse;
import com.asengstudio.movieapp2.response.MovieSearchResponse;
import com.asengstudio.movieapp2.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    // LiveData for search
    private MutableLiveData<List<MovieModel>> mMovies;
    private static MovieApiClient instance;

    // Search : making Global RUNNABLE
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    // LiveData for details
    private MutableLiveData<MovieDetailsModel> mMovieDetails;

    // Details : making Global RUNNABLE
    private RetrieveMovieDetailsRunnable retrieveMovieDetailsRunnable;

    public static MovieApiClient getInstance() {
        if(instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mMovieDetails = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }
    public LiveData<MovieDetailsModel> getMovieDetails() {
        return mMovieDetails;
    }

    // 1
    public void searchMoviesApi(String query, int pageNumber) {
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);
    }

    public void getMovieDetailsApi(String imdbID) {
        if (retrieveMovieDetailsRunnable != null) {
            retrieveMovieDetailsRunnable = null;
        }

        retrieveMovieDetailsRunnable = new RetrieveMovieDetailsRunnable(imdbID);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMovieDetailsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);
    }

    // Retrieving data from RestAPI by runnable class
    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            // Getting the response objects
            try {
                Response response = getMovies(query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                    if (pageNumber == 1) {
                        //Sending data to live data
                        // PostValue: used for background thread
                        // setValue: not for background thread
                        mMovies.postValue(list);
                    } else {
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag","Error " + error);
                    mMovies.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }

        }

        // Search Method/query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return Service.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    "movie",
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

    // Retrieving data from RestAPI by runnable class
    private class RetrieveMovieDetailsRunnable implements Runnable {

        private String imdbID;
        boolean cancelRequest;

        public RetrieveMovieDetailsRunnable(String imdbID) {
            this.imdbID = imdbID;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            // Getting the response objects
            try {
                Response response = getMovieDetails(imdbID).execute();
                if (cancelRequest) {
                    Log.v("Tag", "cancelrequest  " + imdbID);
                    return;
                }

                if (response.code() == 200) {
                    MovieDetailsModel movieDetail = ((MovieDetailsResponse) response.body()).getMovieDetails();
                    mMovieDetails.postValue(movieDetail);
                } else {
                    String error = response.errorBody().string();
                    mMovieDetails.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mMovieDetails.postValue(null);
            }

        }

        // Search Method/query
        private Call<MovieDetailsResponse> getMovieDetails(String imdbID) {
            return Service.getMovieApi().getMovieDetails(
                    Credentials.API_KEY,
                    imdbID
            );
        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Movie Details Request");
            cancelRequest = true;
        }
    }
}
