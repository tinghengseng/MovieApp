package com.asengstudio.movieapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asengstudio.movieapp2.adapters.MovieRecycleView;
import com.asengstudio.movieapp2.adapters.OnMovieListener;
import com.asengstudio.movieapp2.models.MovieModel;
import com.asengstudio.movieapp2.moviedb.Movie;
import com.asengstudio.movieapp2.moviedb.MovieDatabase;
import com.asengstudio.movieapp2.moviedb.MovieDetails;
import com.asengstudio.movieapp2.request.Service;
import com.asengstudio.movieapp2.response.MovieSearchResponse;
import com.asengstudio.movieapp2.utils.Credentials;
import com.asengstudio.movieapp2.utils.MovieApi;
import com.asengstudio.movieapp2.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kotlin.collections.ArrayDeque;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    private RecyclerView recyclerView;
    private MovieRecycleView movieRecycleAdapter;

    private MovieDatabase movieDatabase;
    private Movie movie;
    private List<MovieDetails> movieDetails;
    private List<Movie> movies;
    private List<MovieModel> movieList;


    // ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SetupSearchView();

        recyclerView = findViewById(R.id.recycleView);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        // Calling the observers
        ObserveAnyChange();

        ConfigureRecyclerView();

        movieDatabase = MovieDatabase.getInstance(this);

        movies = new ArrayList<>();
        movies = movieDatabase.getMovieDao().getMovies();
        movieList = new ArrayList<>();
        for(Movie movie: movies){
            Log.v("Tag", "ID = " + movie.getMovie_id());
            MovieModel tempMovie = new MovieModel(movie.getTitle(), movie.getType(), movie.getYear(), movie.getImdbID(), movie.getPoster());
            movieList.add(tempMovie);
        }
        movieRecycleAdapter.setmMovies(movieList);

    }

    private void SetupSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (isNetworkAvailable()) {
                    movieListViewModel.searchMovieApi(query, 1);
                } else{
                    Toast.makeText(getApplicationContext(), "Please check your connection and try again", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    // Observing any data change
    private void ObserveAnyChange() {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // Observing for any data change
                if (movieModels != null) {
                    for (MovieModel movieModel: movieModels) {
                        Log.v("Tag", "onChanged: " + movieModel.getTitle());
                        movieRecycleAdapter.setmMovies(movieModels);
                        movie = new Movie(movieModel.getTitle(), movieModel.getYear(), movieModel.getImdbID(), movieModel.getType(), movieModel.getPoster());
                        int existCount = movieDatabase.getMovieDao().checkMovieExists(movieModel.getImdbID());
                        if (existCount == 0){
                            Long i = movieDatabase.getMovieDao().insertMovie(movie);
                            Log.v("Tag", "ID = " + i);
                        }
                    }
                }
            }
        });
    }


    // 4.
    private void ConfigureRecyclerView() {
        movieRecycleAdapter = new MovieRecycleView(this);

        recyclerView.setAdapter(movieRecycleAdapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    movieListViewModel.searchNextPage();
                }
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        if (isNetworkAvailable()){
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtra("movie",movieRecycleAdapter.getSelectedMovie(position));
            startActivity(intent);
        } else{
            int existCount = movieDatabase.getMovieDao().checkMovieDetailsExists(movieRecycleAdapter.getSelectedMovie(position).getImdbID());
            if (existCount != 0){
                Intent intent = new Intent(this, MovieDetailsActivity.class);
                intent.putExtra("movie",movieRecycleAdapter.getSelectedMovie(position));
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Please check your connection and try again", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onCategoryClick(String category) {

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }



//    private void GetRetrofitResponse() {
//        MovieApi movieApi = Service.getMovieApi();
//        Call<MovieSearchResponse> responseCall = movieApi
//                .searchMovie(
//                        Credentials.API_KEY,
//                        "Marvel",
//                        "movie",
//                        "1"
//                );
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                Log.v("Tag", response.toString());
//                if (response.code() == 200){
//                    Log.v("Tag", "the response " + response.body().toString());
//
//                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
//
//                    for(int i = 0;i < response.body().getMovies().size();i++){
//                        Log.v("Tag","The movie title " + response.body().getMovies().get(i).getTitle());
//                    }
////                    for (MovieModel movie: movies){
////                        Log.v("Tag","The movie title " + movie.getTitle());
////                    }
//                }
//                else{
//                    try{
//                        Log.v("Tag","Error " + response.errorBody().string());
//                    } catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }
}