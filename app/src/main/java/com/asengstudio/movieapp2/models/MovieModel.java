package com.asengstudio.movieapp2.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.asengstudio.movieapp2.moviedb.Constants;

public class MovieModel implements Parcelable {


    private String Title;
    private String Type;
    private String Year;
    private String imdbID;
    private String Poster;

    public MovieModel(String title, String type, String year, String imdbID, String poster) {
        Title = title;
        Type = type;
        Year = year;
        this.imdbID = imdbID;
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public String getType() {
        return Type;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getPoster() {
        return Poster;
    }


    protected MovieModel(Parcel in) {
        Title = in.readString();
        Type = in.readString();
        Year = in.readString();
        imdbID = in.readString();
        Poster = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Type);
        dest.writeString(Year);
        dest.writeString(imdbID);
        dest.writeString(Poster);
    }
}
