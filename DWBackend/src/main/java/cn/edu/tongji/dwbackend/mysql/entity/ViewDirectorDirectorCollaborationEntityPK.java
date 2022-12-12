package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ViewDirectorDirectorCollaborationEntityPK implements Serializable {
    private String directorName1;
    private String directorName2;
    private long collaborateCount;

    @Id
    @Column(name = "director_name1")
    public String getDirectorName1() {
        return directorName1;
    }

    public void setDirectorName1(String directorName1) {
        this.directorName1 = directorName1;
    }

    @Id
    @Column(name = "director_name2")
    public String getDirectorName2() {
        return directorName2;
    }

    public void setDirectorName2(String directorName2) {
        this.directorName2 = directorName2;
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
        ViewDirectorDirectorCollaborationEntityPK that = (ViewDirectorDirectorCollaborationEntityPK) o;
        return collaborateCount == that.collaborateCount && Objects.equals(directorName1, that.directorName1) && Objects.equals(directorName2, that.directorName2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorName1, directorName2, collaborateCount);
    }

}
