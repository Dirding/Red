package com.example.praktika.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TransportRoute.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RouteDao routeDao();
}