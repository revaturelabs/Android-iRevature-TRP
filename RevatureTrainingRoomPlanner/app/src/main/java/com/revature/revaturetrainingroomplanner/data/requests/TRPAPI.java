package com.revature.revaturetrainingroomplanner.data.requests;

import com.revature.revaturetrainingroomplanner.data.requests.responses.BatchesGETResponse;
import com.revature.revaturetrainingroomplanner.data.requests.responses.TrainersGETResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TRPAPI {

    @GET("batches")
    Call<BatchesGETResponse> getBatches();

    @GET("trainers")
    Call<TrainersGETResponse> getTrainers();
}
