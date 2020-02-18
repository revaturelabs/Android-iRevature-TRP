package com.revature.revaturetrainingroomplanner.data.requests;

import com.revature.revaturetrainingroomplanner.data.requests.responses.BatchesGETResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BatchesAPI {

    @GET("batches")
    Call<BatchesGETResponse> getBatches();

}