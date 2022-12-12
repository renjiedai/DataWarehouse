package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "view_movie_time", schema = "DW", catalog = "")
public class ViewMovieTimeEntity {
    private int movieId;
    private String movieName;
    private short year;
    private byte month;
    private byte day;
    private byte weekday;
    private byte season;

    @Id
    @Basic
    @Column(name = "movie_id")
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "movie_name")
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Basic
    @Column(name = "year")
    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month")
    public byte getMonth() {
        return month;
    }

    public void setMonth(byte month) {
        this.month = month;
    }

    @Basic
    @Column(name = "day")
    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    @Basic
    @Column(name = "weekday")
    public byte getWeekday() {
        return weekday;
    }

    public void setWeekday(byte weekday) {
        this.weekday = weekday;
    }

    @Basic
    @Column(name = "season")
    public byte getSeason() {
        return season;
    }

    public void setSeason(byte season) {
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewMovieTimeEntity that = (ViewMovieTimeEntity) o;
        return movieId == that.movieId && year == that.year && month == that.month && day == that.day && weekday == that.weekday && season == that.season && Objects.equals(movieName, that.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, year, month, day, weekday, season);
    }
}
