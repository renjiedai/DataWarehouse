package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "view_actor_actor_collaboration", schema = "DW", catalog = "")
@IdClass(ViewActorActorCollaborationEntityPK.class)
public class ViewActorActorCollaborationEntity {
    private String actorName1;
    private String actorName2;
    private long collaborateCount;

    @Id
    @Basic
    @Column(name = "actor_name_1")
    public String getActorName1() {
        return actorName1;
    }

    public void setActorName1(String actorName1) {
        this.actorName1 = actorName1;
    }

    @Id
    @Basic
    @Column(name = "actor_name_2")
    public String getActorName2() {
        return actorName2;
    }

    public void setActorName2(String actorName2) {
        this.actorName2 = actorName2;
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
        ViewActorActorCollaborationEntity that = (ViewActorActorCollaborationEntity) o;
        return collaborateCount == that.collaborateCount && Objects.equals(actorName1, that.actorName1) && Objects.equals(actorName2, that.actorName2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName1, actorName2, collaborateCount);
    }
}
