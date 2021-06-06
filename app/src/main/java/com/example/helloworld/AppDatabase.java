package com.example.helloworld;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.helloworld.dao.SettingsDao;
import com.example.helloworld.entity.Settings;

@Database(entities = {Settings.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingsDao settingsDao();
}
