package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "review", schema = "DW", catalog = "")
@IdClass(ReviewEntityPK.class)
public class ReviewEntity {
    private int reviewId;
    private String reviewerName;
    private Integer reviewScore;
    private Date reviewTime;
    private String reviewSummary;
    private String reviewText;
    private Byte reviewHelpfulness;
    private int movieId;
    private String movieAsin;
    private Byte isActive;

    @Id
    @Column(name = "review_id")
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Basic
    @Column(name = "reviewer_name")
    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    @Basic
    @Column(name = "review_score")
    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    @Basic
    @Column(name = "review_time")
    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Basic
    @Column(name = "review_summary")
    public String getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(String reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    @Basic
    @Column(name = "review_text")
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    @Basic
    @Column(name = "review_helpfulness")
    public Byte getReviewHelpfulness() {
        return reviewHelpfulness;
    }

    public void setReviewHelpfulness(Byte reviewHelpfulness) {
        this.reviewHelpfulness = reviewHelpfulness;
    }

    @Id
    @Column(name = "movie_id")
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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
    @Column(name = "is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return reviewId == that.reviewId && movieId == that.movieId && Objects.equals(reviewerName, that.reviewerName) && Objects.equals(reviewScore, that.reviewScore) && Objects.equals(reviewTime, that.reviewTime) && Objects.equals(reviewSummary, that.reviewSummary) && Objects.equals(reviewText, that.reviewText) && Objects.equals(reviewHelpfulness, that.reviewHelpfulness) && Objects.equals(movieAsin, that.movieAsin) && Objects.equals(isActive, that.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, reviewerName, reviewScore, reviewTime, reviewSummary, reviewText, reviewHelpfulness, movieId, movieAsin, isActive);
    }
}
