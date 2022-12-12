package cn.edu.tongji.dwbackend.mysql.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "time", schema = "DW", catalog = "")
public class TimeEntity {
    private int timeId;
    private int year;
    private int month;
    private byte day;
    private int season;
    private byte weekday;
    private Date releaseTime;

    @Id
    @Column(name = "time_id")
    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    @Basic
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month")
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Basic
    @Column(name = "day")
    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    @Basic
    @Column(name = "season")
    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    @Basic
    @Column(name = "weekday")
    public byte getWeekday() {
        return weekday;
    }

    public void setWeekday(byte weekday) {
        this.weekday = weekday;
    }

    @Basic
    @Column(name = "release_time")
    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntity that = (TimeEntity) o;
        return timeId == that.timeId && year == that.year && month == that.month && day == that.day && season == that.season && weekday == that.weekday && Objects.equals(releaseTime, that.releaseTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeId, year, month, day, season, weekday, releaseTime);
    }
}
