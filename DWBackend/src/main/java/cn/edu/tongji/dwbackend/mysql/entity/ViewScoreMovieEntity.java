package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "view_score_movie", schema = "DW", catalog = "")
public class ViewScoreMovieEntity {
    private int movieId;
    private String movieName;
    private double score;
    private double positiveRate;
    private double negativeRate;

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
    @Column(name = "positive_rate")
    public double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(double positiveRate) {
        this.positiveRate = positiveRate;
    }

    @Basic
    @Column(name = "negative_rate")
    public double getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(double negativeRate) {
        this.negativeRate = negativeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewScoreMovieEntity that = (ViewScoreMovieEntity) o;
        return movieId == that.movieId && Double.compare(that.score, score) == 0 && Double.compare(that.positiveRate, positiveRate) == 0 && Double.compare(that.negativeRate, negativeRate) == 0 && Objects.equals(movieName, that.movieName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, score, positiveRate, negativeRate);
    }
}
