package com.revature.revaturetrainingroomplanner.data.requests.responses;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.revature.revaturetrainingroomplanner.data.model.Batch;

import java.util.List;

public class LocationsGETResponse {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("batches")
    @Expose
    private List<Batch> batches;

    public int getCount() {
        return count;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    @NonNull
    @Override
    public String toString() {
        return "BatchesGETResponse{" +
                "count=" + count +
                ", batches=" + batches +
                '}';
    }
}
