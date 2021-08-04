package com.asengstudio.movieapp2.response;

import android.graphics.Movie;

import com.asengstudio.movieapp2.models.MovieDetailsModel;
import com.asengstudio.movieapp2.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailsResponse {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String DVD;
    private String BoxOffice;
    private String Production;
    private String Website;
    private String Response;

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getAwards() {
        return Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getDVD() {
        return DVD;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public String getProduction() {
        return Production;
    }

    public String getWebsite() {
        return Website;
    }

    public String getResponse() {
        return Response;
    }

    @Override
    public String toString() {
        return "MovieDetailsResponse{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Rated='" + Rated + '\'' +
                ", Released='" + Released + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Writer='" + Writer + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Language='" + Language + '\'' +
                ", Country='" + Country + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Metascore='" + Metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", DVD='" + DVD + '\'' +
                ", BoxOffice='" + BoxOffice + '\'' +
                ", Production='" + Production + '\'' +
                ", Website='" + Website + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }

    public MovieDetailsModel getMovieDetails() {
        MovieDetailsModel movieDetails = new MovieDetailsModel(
                Title,
                Year,
                Rated,
                Released,
                Runtime,
                Genre,
                Director,
                Writer,
                Actors,
                Plot,
                Language,
                Country,
                Awards,
                Poster,
                Metascore,
                imdbRating,
                imdbVotes,
                imdbID,
                Type,
                DVD,
                BoxOffice,
                Production,
                Website,
                Response
        );
        return movieDetails;
    }
}
