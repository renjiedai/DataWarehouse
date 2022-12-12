package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ReviewEntityPK implements Serializable {
    private int reviewId;
    private int movieId;

    @Column(name = "review_id")
    @Id
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Column(name = "movie_id")
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntityPK that = (ReviewEntityPK) o;
        return reviewId == that.reviewId && movieId == that.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, movieId);
    }
}
