package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "movie", schema = "DW", catalog = "")
public class MovieEntity {
    private int movieId;
    private String movieName;
    private byte movieTypeNum;
    private double movieScore;
    private String movieAsin;
    private String movieType;
    private int commentNum;
    private Date releaseTime;

    @Id
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
    @Column(name = "movie_type_num")
    public byte getMovieTypeNum() {
        return movieTypeNum;
    }

    public void setMovieTypeNum(byte movieTypeNum) {
        this.movieTypeNum = movieTypeNum;
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
    @Column(name = "movie_asin")
    public String getMovieAsin() {
        return movieAsin;
    }

    public void setMovieAsin(String movieAsin) {
        this.movieAsin = movieAsin;
    }

    @Basic
    @Column(name = "movie_type")
    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    @Basic
    @Column(name = "comment_num")
    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    @Basic
    @Column(name = "release_time")
    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return movieId == that.movieId && movieTypeNum == that.movieTypeNum && Double.compare(that.movieScore, movieScore) == 0 && commentNum == that.commentNum && Objects.equals(movieName, that.movieName) && Objects.equals(movieAsin, that.movieAsin) && Objects.equals(movieType, that.movieType) && Objects.equals(releaseTime, that.releaseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, movieTypeNum, movieScore, movieAsin, movieType, commentNum, releaseTime);
    }
}
