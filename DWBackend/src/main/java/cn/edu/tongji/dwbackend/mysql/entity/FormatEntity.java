package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "format", schema = "DW", catalog = "")
public class FormatEntity {
    private int formatId;
    private String formatName;

    @Id
    @Column(name = "format_id")
    public int getFormatId() {
        return formatId;
    }

    public void setFormatId(int formatId) {
        this.formatId = formatId;
    }

    @Basic
    @Column(name = "format_name")
    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormatEntity that = (FormatEntity) o;
        return formatId == that.formatId && Objects.equals(formatName, that.formatName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formatId, formatName);
    }
}
