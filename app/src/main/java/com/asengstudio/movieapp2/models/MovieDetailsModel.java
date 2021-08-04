package com.asengstudio.movieapp2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieDetailsModel implements Parcelable {

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

    public MovieDetailsModel() {
    }

    public MovieDetailsModel(String title, String year, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String poster, String metascore, String imdbRating, String imdbVotes, String imdbID, String type, String DVD, String boxOffice, String production, String website, String response) {
        Title = title;
        Year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Writer = writer;
        Actors = actors;
        Plot = plot;
        Language = language;
        Country = country;
        Awards = awards;
        Poster = poster;
        Metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        Type = type;
        this.DVD = DVD;
        BoxOffice = boxOffice;
        Production = production;
        Website = website;
        Response = response;
    }

    protected MovieDetailsModel(Parcel in) {
        Title = in.readString();
        Year = in.readString();
        Rated = in.readString();
        Released = in.readString();
        Runtime = in.readString();
        Genre = in.readString();
        Director = in.readString();
        Writer = in.readString();
        Actors = in.readString();
        Plot = in.readString();
        Language = in.readString();
        Country = in.readString();
        Awards = in.readString();
        Poster = in.readString();
        Metascore = in.readString();
        imdbRating = in.readString();
        imdbVotes = in.readString();
        imdbID = in.readString();
        Type = in.readString();
        DVD = in.readString();
        BoxOffice = in.readString();
        Production = in.readString();
        Website = in.readString();
        Response = in.readString();
    }

    public static final Creator<MovieDetailsModel> CREATOR = new Creator<MovieDetailsModel>() {
        @Override
        public MovieDetailsModel createFromParcel(Parcel in) {
            return new MovieDetailsModel(in);
        }

        @Override
        public MovieDetailsModel[] newArray(int size) {
            return new MovieDetailsModel[size];
        }
    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Year);
        dest.writeString(Rated);
        dest.writeString(Released);
        dest.writeString(Runtime);
        dest.writeString(Genre);
        dest.writeString(Director);
        dest.writeString(Writer);
        dest.writeString(Actors);
        dest.writeString(Plot);
        dest.writeString(Language);
        dest.writeString(Country);
        dest.writeString(Awards);
        dest.writeString(Poster);
        dest.writeString(Metascore);
        dest.writeString(imdbRating);
        dest.writeString(imdbVotes);
        dest.writeString(imdbID);
        dest.writeString(Type);
        dest.writeString(DVD);
        dest.writeString(BoxOffice);
        dest.writeString(Production);
        dest.writeString(Website);
        dest.writeString(Response);
    }
}
