package com.example.helloworld.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.type.DateTime;

import java.time.LocalTime;

@Entity
public class Settings {
    @PrimaryKey
    @NonNull
    private String uid = "";

    @ColumnInfo(name = "matches_reminder_time")
    private long time;

    @ColumnInfo(name = "maximum_distance_search")
    private double distance;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "account_privacy")
    private Boolean privacy;

    @ColumnInfo(name = "interested_age_range")
    private int ageRange;

    public String getUid() { return uid; }

    public void setUid(String uid) {this.uid = uid; }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public int getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int ageRange) {
        this.ageRange = ageRange;
    }
}
