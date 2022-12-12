package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ViewDirectorScoreTypeEntityPK implements Serializable {
    private String director;
    private String movieName;
    private double score;
    private double positiveRate;
    private double negativeRate;
    private String type;

    @Id
    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Id
    @Column(name = "movie_name")
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Id
    @Column(name = "score")
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Id
    @Column(name = "positive_rate")
    public double getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(double positiveRate) {
        this.positiveRate = positiveRate;
    }

    @Id
    @Column(name = "negative_rate")
    public double getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(double negativeRate) {
        this.negativeRate = negativeRate;
    }

    @Id
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDirectorScoreTypeEntityPK that = (ViewDirectorScoreTypeEntityPK) o;
        return Double.compare(that.score, score) == 0 && Double.compare(that.positiveRate, positiveRate) == 0 && Double.compare(that.negativeRate, negativeRate) == 0 && Objects.equals(director, that.director) && Objects.equals(movieName, that.movieName) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(director, movieName, score, positiveRate, negativeRate, type);
    }

}
