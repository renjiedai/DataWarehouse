package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "view_actor_score_time", schema = "DW", catalog = "")
public class ViewActorScoreTimeEntity {
    private int movieId;
    private String movieName;
    private double score;
    private String actor;
    private short year;

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
    @Column(name = "score")
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "actor")
    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    @Basic
    @Column(name = "year")
    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewActorScoreTimeEntity that = (ViewActorScoreTimeEntity) o;
        return movieId == that.movieId && Double.compare(that.score, score) == 0 && year == that.year && Objects.equals(movieName, that.movieName) && Objects.equals(actor, that.actor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, score, actor, year);
    }
}
