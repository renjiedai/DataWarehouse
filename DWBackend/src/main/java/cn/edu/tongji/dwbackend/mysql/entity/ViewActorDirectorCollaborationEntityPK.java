package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ViewActorDirectorCollaborationEntityPK implements Serializable {
    private String actorName;
    private String directorName;
    private long collaborateCount;

    @Id
    @Column(name = "actor_name")
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Id
    @Column(name = "director_name")
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Id
    @Column(name = "collaborate_count")
    public long getCollaborateCount() {
        return collaborateCount;
    }

    public void setCollaborateCount(long collaborateCount) {
        this.collaborateCount = collaborateCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewActorDirectorCollaborationEntityPK that = (ViewActorDirectorCollaborationEntityPK) o;
        return collaborateCount == that.collaborateCount && Objects.equals(actorName, that.actorName) && Objects.equals(directorName, that.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName, directorName, collaborateCount);
    }

}
