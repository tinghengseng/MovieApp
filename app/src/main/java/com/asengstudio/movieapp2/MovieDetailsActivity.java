package com.asengstudio.movieapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.asengstudio.movieapp2.models.MovieDetailsModel;
import com.asengstudio.movieapp2.models.MovieModel;
import com.asengstudio.movieapp2.moviedb.Movie;
import com.asengstudio.movieapp2.moviedb.MovieDatabase;
import com.asengstudio.movieapp2.moviedb.MovieDetails;
import com.asengstudio.movieapp2.viewmodels.MovieDetailsViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails, yearDetails, categoryDetails, durationDetails;
    private TextView directorDetails, writerDetails, actorDetails;
    private RatingBar ratingBarDetails;
    private ImageButton imageButtonDetails;

    // ViewModel
    private MovieDetailsViewModel movieDetailsViewModel;

    private MovieDetailsModel movieDetailsModel;

    private MovieDatabase movieDatabase;
    private MovieDetails movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.movie_title_details);
        descDetails = findViewById(R.id.description_details);
        ratingBarDetails = findViewById(R.id.ratingBar_details);
        imageButtonDetails = findViewById(R.id.image_back_details);
        yearDetails = findViewById(R.id.movie_year_details);
        categoryDetails = findViewById(R.id.movie_category_details);
        durationDetails = findViewById(R.id.movie_duration_details);
        directorDetails = findViewById(R.id.movie_director_details);
        writerDetails = findViewById(R.id.movie_writer_details);
        actorDetails = findViewById(R.id.movie_actor_details);

        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        movieDetailsModel = new MovieDetailsModel();

        movieDatabase = MovieDatabase.getInstance(this);

        // Calling the observers
        ObserveAnyChange();

        imageButtonDetails.setOnClickListener(this);

        GetDataFromIntents();
    }

    private void GetDataFromIntents() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");

            if (isNetworkAvailable()){
                movieDetailsViewModel.getMovieDetailsApi(movieModel.getImdbID());
            } else {
                movieDetails = movieDatabase.getMovieDao().getMovieDetail(movieModel.getImdbID());

                MovieDetailsModel temp = new MovieDetailsModel(
                            movieDetails.getTitle(),
                            movieDetailsModel.getYear(),
                            "",
                            "",
                            movieDetails.getRuntime(),
                            "",
                            movieDetails.getDirector(),
                            movieDetails.getWriter(),
                            movieDetails.getActors(),
                            movieDetails.getPlot(),
                            "",
                            "",
                            "",
                            movieDetails.getPoster(),
                            "",
                            movieDetails.getImdbrating(),
                            "",
                            movieDetails.getImdbID(),
                            movieDetails.getType(),
                            "",
                            "",
                            "",
                            "",
                            ""
                        );
                movieDetailsModel = temp;
                updateUI();
            }
        }
    }

    private float GetRandomFloat(){
        Random r = new Random();
        float randomRating = (float) (1.0 + r.nextFloat() * (5.0 - 1.0));
        return randomRating;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.image_back_details :
                onBackPressed();
        }
    }

    // Observing any data change
    private void ObserveAnyChange() {
        movieDetailsViewModel.getMovieDetails().observe(this, new Observer<MovieDetailsModel>() {
            @Override
            public void onChanged(MovieDetailsModel mDetailsModel) {
                // Observing for any data change
                if (mDetailsModel != null) {
                    movieDetailsModel = mDetailsModel;
                    MovieDetails temp = new MovieDetails(
                            movieDetailsModel.getTitle(),
                            movieDetailsModel.getYear(),
                            movieDetailsModel.getRuntime(),
                            movieDetailsModel.getDirector(),
                            movieDetailsModel.getWriter(),
                            movieDetailsModel.getActors(),
                            movieDetailsModel.getPlot(),
                            movieDetailsModel.getPoster(),
                            movieDetailsModel.getImdbRating(),
                            movieDetailsModel.getImdbID(),
                            movieDetailsModel.getType()
                    );
                    Long i = movieDatabase.getMovieDao().insertMovieDetails(temp);
                    updateUI();
                }
            }
        });
    }

    private void updateUI() {
        titleDetails.setText(movieDetailsModel.getTitle());
        descDetails.setText(movieDetailsModel.getPlot());
        yearDetails.setText(movieDetailsModel.getYear());
        categoryDetails.setText(movieDetailsModel.getType());
        durationDetails.setText(movieDetailsModel.getRuntime());
        float rating = Float.parseFloat(movieDetailsModel.getImdbRating())/2;
        ratingBarDetails.setRating(rating);
        directorDetails.setText(movieDetailsModel.getDirector());
        writerDetails.setText(movieDetailsModel.getWriter());
        actorDetails.setText(movieDetailsModel.getActors());

        Glide.with(this)
                .load(movieDetailsModel.getPoster())
                .into(imageViewDetails);
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
}