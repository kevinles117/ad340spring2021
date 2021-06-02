package com.example.helloworld.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.type.DateTime;

@Entity
public class Settings {
    @PrimaryKey
    private int id = 0;

    @ColumnInfo(name = "matches_reminder_time")
    private DateTime date;

    @ColumnInfo(name = "maximum_distance_search")
    private int distance;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "account_privacy")
    private Boolean privacy;

    @ColumnInfo(name = "interested_age_range")
    private String ageRange;

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
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

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
}
