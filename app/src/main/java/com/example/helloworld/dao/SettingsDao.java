package com.example.helloworld.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.helloworld.entity.Settings;
import com.google.type.DateTime;

import java.util.List;

@Dao
public interface SettingsDao {
    @Query("SELECT * FROM settings")
    LiveData<List<Settings>> getAll();

    @Query("SELECT * FROM settings WHERE uid IN (:uid)")
    LiveData<List<Settings>> loadAllByIds(String[] uid);

    @Query("SELECT * FROM settings WHERE matches_reminder_time IN (:matches_reminder_time)")
    LiveData<Settings> findByName(long matches_reminder_time);

    @Query("SELECT * FROM settings WHERE maximum_distance_search IN (:maximum_distance_search)")
    LiveData<Settings> findByName(double maximum_distance_search);

    @Query("SELECT * FROM settings WHERE gender IN (:gender)")
    LiveData<Settings> findByName(String gender);

    @Query("SELECT * FROM settings WHERE account_privacy IN (:account_privacy)")
    LiveData<Settings> findByName(Boolean account_privacy);

    @Query("SELECT * FROM settings WHERE interested_age_range IN (:interested_age_range)")
    LiveData<Settings> findByName(int interested_age_range);

    @Update
    void updateSettings(Settings... settings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Settings... settings);

    @Delete
    void delete(Settings settings);
}
