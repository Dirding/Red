package com.example.praktika;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.praktika.model.AuthRequest;
import com.example.praktika.model.AuthResponse;
import com.example.praktika.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etLogin = findViewById(R.id.etLoginReg);
        EditText etPassword = findViewById(R.id.etPasswordReg);
        Button btnRegister = findViewById(R.id.btnRegisterAction);
        Button btnBack = findViewById(R.id.btnBackToLogin);

        btnBack.setOnClickListener(v -> finish());

        btnRegister.setOnClickListener(v -> {
            String login = etLogin.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if(login.length() >= 3 && password.length() >= 6) {
                doRegister(login, password);
            } else {
                Toast.makeText(this, "Логин от 3 символов, пароль от 6", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doRegister(String login, String password) {
        AuthRequest request = new AuthRequest(login, password);

        RetrofitClient.getApi().signUp(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Успешно! Теперь войдите.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Ошибка: Логин занят", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Ошибка сети", Toast.LENGTH_SHORT).show();
            }
        });
    }
}