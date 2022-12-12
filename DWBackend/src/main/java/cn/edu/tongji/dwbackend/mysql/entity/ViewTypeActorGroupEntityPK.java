package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ViewTypeActorGroupEntityPK implements Serializable {
    private int movieId;
    private String type;
    private String actor1;
    private String actor2;
    private int comment;

    @Id
    @Column(name = "movie_id")
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Id
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "actor1")
    public String getActor1() {
        return actor1;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    @Id
    @Column(name = "actor2")
    public String getActor2() {
        return actor2;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    @Id
    @Column(name = "comment")
    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewTypeActorGroupEntityPK that = (ViewTypeActorGroupEntityPK) o;
        return movieId == that.movieId && comment == that.comment && Objects.equals(type, that.type) && Objects.equals(actor1, that.actor1) && Objects.equals(actor2, that.actor2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, type, actor1, actor2, comment);
    }

}
