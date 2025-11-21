package com.example.praktika;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.Executors;
import com.example.praktika.model.AppDatabase;
import androidx.room.Room;

public class ActivitySettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnClearCache = findViewById(R.id.btnClearCache);
        btnClearCache.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "transport-db").build();
                db.clearAllTables();
                runOnUiThread(() ->
                        Toast.makeText(this, "Кэш очищен!", Toast.LENGTH_SHORT).show()
                );
            });
        });

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            getSharedPreferences("AppPrefs", MODE_PRIVATE)
                    .edit()
                    .remove("access_token")
                    .apply();

            Intent intent = new Intent(ActivitySettings.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            Toast.makeText(this, "Вы вышли из системы", Toast.LENGTH_SHORT).show();
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}