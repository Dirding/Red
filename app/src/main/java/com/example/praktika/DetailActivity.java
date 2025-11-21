package com.example.praktika;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktika.model.TransportRoute;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TransportRoute route = (TransportRoute) getIntent().getSerializableExtra("route_data");

        if (route != null) {
            TextView tvNumber = findViewById(R.id.detailNumber);
            TextView tvRoute = findViewById(R.id.detailRoute);
            TextView tvSchedule = findViewById(R.id.detailSchedule);
            RecyclerView rvStops = findViewById(R.id.rvStops);

            tvNumber.setText(route.number);
            tvRoute.setText(route.startPoint + " — " + route.endPoint);
            tvSchedule.setText(route.schedule);

            List<StopAdapter.StopItem> stopList = new ArrayList<>();

            if (route.stops != null && !route.stops.isEmpty()) {
                String[] rawStops = route.stops.split(",");

                for (String raw : rawStops) {
                    String[] parts = raw.trim().split("\\|");

                    if (parts.length == 2) {
                        stopList.add(new StopAdapter.StopItem(parts[0].trim(), parts[1].trim()));
                    } else {
                        stopList.add(new StopAdapter.StopItem(parts[0].trim(), "--:--"));
                    }
                }
            }

            rvStops.setLayoutManager(new LinearLayoutManager(this));
            rvStops.setAdapter(new StopAdapter(stopList));
        }
    }
}