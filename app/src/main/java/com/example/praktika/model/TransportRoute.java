package com.example.praktika.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "routes")
public class TransportRoute implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    public long id;

    @SerializedName("number")
    public String number;

    @SerializedName("start_point")
    public String startPoint;

    @SerializedName("end_point")
    public String endPoint;

    @SerializedName("schedule")
    public String schedule;

    @SerializedName("vehicle_type")
    public String vehicleType;

    @SerializedName("stops")
    public String stops;
}