package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "time_movie", schema = "DW", catalog = "")
public class TimeMovieEntity {
    private int movieId;
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
        TimeMovieEntity that = (TimeMovieEntity) o;
        return movieId == that.movieId && Objects.equals(releaseTime, that.releaseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, releaseTime);
    }
}
