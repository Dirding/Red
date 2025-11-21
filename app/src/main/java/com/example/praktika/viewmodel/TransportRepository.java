package com.example.praktika.viewmodel;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.example.praktika.model.AppDatabase;
import com.example.praktika.model.RouteDao;
import com.example.praktika.model.TransportRoute;
import com.example.praktika.network.RetrofitClient;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransportRepository {
    private RouteDao routeDao;
    private LiveData<List<TransportRoute>> allRoutes;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public TransportRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "transport-db").build();
        routeDao = db.routeDao();
        allRoutes = routeDao.getAllRoutes();
    }

    public LiveData<List<TransportRoute>> getAllRoutes() {
        refreshData();
        return allRoutes;
    }

    private void refreshData() {
        RetrofitClient.getApi().getRoutes("*").enqueue(new Callback<List<TransportRoute>>() {
            @Override
            public void onResponse(Call<List<TransportRoute>> call, Response<List<TransportRoute>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    executor.execute(() -> routeDao.insertAll(response.body()));
                }
            }
            @Override
            public void onFailure(Call<List<TransportRoute>> call, Throwable t) {
            }
        });
    }
}