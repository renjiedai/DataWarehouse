package cn.edu.tongji.dwbackend.mysql.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "actor_movie", schema = "DW", catalog = "")
@IdClass(ActorMovieEntityPK.class)
public class ActorMovieEntity {
    private String actorName;
    private int movieId;
    @JsonProperty("isStarring")
    private boolean isStarring;

    @Id
    @Column(name = "actor_name")
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Id
    @Column(name = "movie_id")
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Id
    @Column(name = "is_starring")
    public boolean isStarring() {
        return isStarring;
    }

    public void setStarring(boolean starring) {
        isStarring = starring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorMovieEntity that = (ActorMovieEntity) o;
        return movieId == that.movieId && isStarring == that.isStarring && Objects.equals(actorName, that.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName, movieId, isStarring);
    }
}
