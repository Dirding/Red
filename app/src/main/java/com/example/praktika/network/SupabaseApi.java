package com.example.praktika.network;

import com.example.praktika.model.AuthRequest;
import com.example.praktika.model.AuthResponse;
import com.example.praktika.model.TransportRoute;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SupabaseApi {
    String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR4bXV5ZWt6dmptenVzb21rc3ppIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NTI4OTYsImV4cCI6MjA3OTEyODg5Nn0.JLRZxl3rFR8A_CFBPW-8oS87Ua2QngTHzKSmJ38MLnw";
    @Headers({
            "apikey: " + API_KEY,
            "Authorization: Bearer " + API_KEY
    })
    @GET("rest/v1/routes")
    Call<List<TransportRoute>> getRoutes(@Query("select") String select);
    @Headers("apikey: " + API_KEY)
    @POST("auth/v1/signup")
    Call<AuthResponse> signUp(@Body AuthRequest request);
    @Headers("apikey: " + API_KEY)
    @POST("auth/v1/token?grant_type=password")
    Call<AuthResponse> signIn(@Body AuthRequest request);
}