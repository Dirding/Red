package com.example.praktika.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.praktika.model.TransportRoute;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private TransportRepository repository;
    private LiveData<List<TransportRoute>> allRoutes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new TransportRepository(application);
        allRoutes = repository.getAllRoutes();
    }

    public LiveData<List<TransportRoute>> getRoutes() {
        return allRoutes;
    }
}