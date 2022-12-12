package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DirectorMovieEntityPK implements Serializable {
    private String directorName;
    private int movieId;

    @Column(name = "director_name")
    @Id
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
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
        DirectorMovieEntityPK that = (DirectorMovieEntityPK) o;
        return movieId == that.movieId && Objects.equals(directorName, that.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorName, movieId);
    }
}
