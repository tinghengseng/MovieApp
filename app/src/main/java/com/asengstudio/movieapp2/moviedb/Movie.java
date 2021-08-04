package com.asengstudio.movieapp2.moviedb;


import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_MOVIE)
public class Movie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long movie_id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "year")
    private String year;

    @ColumnInfo(name = "imdbID")
    private String imdbID;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "poster")
    private String poster;

    public Movie(String title, String year, String imdbID, String type, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.poster = poster;
    }

    @Ignore
    public Movie() {
    }

    public long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(long movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;

        if (!(o instanceof Movie))  return false;

        Movie movie = (Movie) o;

        if(movie_id != movie.movie_id) return false;
        return title == null ? title.equals(movie.title) : movie.title == null;
    }

    @Override
    public int hashCode() {
        int result = (int) movie_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
