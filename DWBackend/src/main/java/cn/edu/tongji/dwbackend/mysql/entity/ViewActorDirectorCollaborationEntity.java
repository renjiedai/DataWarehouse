package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "view_actor_director_collaboration", schema = "DW", catalog = "")
@IdClass(ViewActorDirectorCollaborationEntityPK.class)
public class ViewActorDirectorCollaborationEntity {
    private String actorName;
    private String directorName;
    private long collaborateCount;

    @Id
    @Basic
    @Column(name = "actor_name")
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Id
    @Basic
    @Column(name = "director_name")
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Id
    @Basic
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
        ViewActorDirectorCollaborationEntity that = (ViewActorDirectorCollaborationEntity) o;
        return collaborateCount == that.collaborateCount && Objects.equals(actorName, that.actorName) && Objects.equals(directorName, that.directorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName, directorName, collaborateCount);
    }
}
