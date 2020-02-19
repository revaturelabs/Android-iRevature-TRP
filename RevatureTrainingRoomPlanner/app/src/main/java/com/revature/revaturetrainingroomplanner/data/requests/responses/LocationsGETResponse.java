package com.revature.revaturetrainingroomplanner.data.requests.responses;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.revature.revaturetrainingroomplanner.data.model.Campus;

import java.util.List;

public class LocationsGETResponse {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("locations")
    @Expose
    private List<Campus> campuses;

    public int getCount() {
        return count;
    }

    public List<Campus> getLocations() {
        return campuses;
    }

    @NonNull
    @Override
    public String toString() {
        return "LocationsGETResponse{" +
                "count=" + count +
                ", locations=" + campuses +
                '}';
    }
}
