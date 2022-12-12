package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "score_movie", schema = "DW", catalog = "")
public class ScoreMovieEntity {
    private int movieId;
    private double movieScore;
    private double positiveRate;
    private double negativeRate;

    @Id
    @Column(name = "movie_id")
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Basic
    @Column(name = "movie_score")
    public double getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(double movieScore) {
        this.movieScore = movieScore;
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
        ScoreMovieEntity that = (ScoreMovieEntity) o;
        return movieId == that.movieId && Double.compare(that.movieScore, movieScore) == 0 && Double.compare(that.positiveRate, positiveRate) == 0 && Double.compare(that.negativeRate, negativeRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieScore, positiveRate, negativeRate);
    }
}
