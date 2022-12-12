package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ViewActorActorCollaborationEntityPK implements Serializable {
    private String actorName1;
    private String actorName2;
    private long collaborateCount;

    @Id
    @Column(name = "actor_name_1")
    public String getActorName1() {
        return actorName1;
    }

    public void setActorName1(String actorName1) {
        this.actorName1 = actorName1;
    }

    @Id
    @Column(name = "actor_name_2")
    public String getActorName2() {
        return actorName2;
    }

    public void setActorName2(String actorName2) {
        this.actorName2 = actorName2;
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
        ViewActorActorCollaborationEntityPK that = (ViewActorActorCollaborationEntityPK) o;
        return collaborateCount == that.collaborateCount && Objects.equals(actorName1, that.actorName1) && Objects.equals(actorName2, that.actorName2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorName1, actorName2, collaborateCount);
    }

}
