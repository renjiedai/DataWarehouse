package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TypeMovieEntityPK implements Serializable {
    private String typeName;
    private int movieId;

    @Column(name = "type_name")
    @Id
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        TypeMovieEntityPK that = (TypeMovieEntityPK) o;
        return movieId == that.movieId && Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, movieId);
    }
}
