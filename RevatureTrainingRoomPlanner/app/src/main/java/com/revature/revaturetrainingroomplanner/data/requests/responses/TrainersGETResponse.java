package com.revature.revaturetrainingroomplanner.data.requests.responses;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;

import java.util.List;

public class TrainersGETResponse {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("trainers")
    @Expose
    private List<Trainer> trainers;

    public int getCount() {
        return count;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    @NonNull
    @Override
    public String toString() {
        return "TrainersGETResponse{" +
                "count=" + count +
                ", trainers=" + trainers +
                '}';
    }
}
