package com.example.praktika.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RouteDao {
    @Query("SELECT * FROM routes")
    LiveData<List<TransportRoute>> getAllRoutes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TransportRoute> routes);
}