package com.asengstudio.movieapp2.moviedb;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_MOVIE_DETAILS)
public class MovieDetails implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long movie_detail_id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "year")
    private String year;
    @ColumnInfo(name = "runtime")
    private String runtime;
    @ColumnInfo(name = "director")
    private String director;
    @ColumnInfo(name = "writer")
    private String writer;
    @ColumnInfo(name = "actors")
    private String actors;
    @ColumnInfo(name = "plot")
    private String plot;
    @ColumnInfo(name = "poster")
    private String poster;
    @ColumnInfo(name = "imdbrating")
    private String imdbrating;
    @ColumnInfo(name = "imdb_id")
    private String imdbID;
    @ColumnInfo(name = "type")
    private String type;

    public MovieDetails(String title, String year, String runtime, String director, String writer, String actors, String plot, String poster, String imdbrating, String imdbID, String type) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.poster = poster;
        this.imdbrating = imdbrating;
        this.imdbID = imdbID;
        this.type = type;
    }

    @Ignore
    public MovieDetails() {}

    public long getMovie_detail_id() {
        return movie_detail_id;
    }

    public void setMovie_detail_id(long movie_detail_id) {
        this.movie_detail_id = movie_detail_id;
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

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(String imdbrating) {
        this.imdbrating = imdbrating;
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

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;

        if (!(o instanceof MovieDetails))  return false;

        MovieDetails moviedetails = (MovieDetails) o;

        if(movie_detail_id != moviedetails.movie_detail_id) return false;
        return title == null ? title.equals(moviedetails.title) : moviedetails.title == null;
    }

    @Override
    public int hashCode() {
        int result = (int) movie_detail_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "movie_detail_id=" + movie_detail_id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", runtime='" + runtime + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", poster='" + poster + '\'' +
                ", imdbrating='" + imdbrating + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
