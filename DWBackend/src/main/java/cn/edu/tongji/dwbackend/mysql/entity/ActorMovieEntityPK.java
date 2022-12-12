package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ActorMovieEntityPK implements Serializable {
    private String actorName;
    private int movieId;
    private boolean starring;

    @Column(name = "actor_name")
    @Id
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Column(name = "movie_id")
    @Id
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Column(name = "is_starring")
    @Id
    public boolean isStarring() {
        return starring;
    }

    public void setStarring(boolean starring) {
        this.starring = starring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorMovieEntityPK that = (ActorMovieEntityPK) o;
        return movieId == that.movieId && starring == that.starring && Objects.equals(actorName, that.actorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName, movieId, starring);
    }
}
