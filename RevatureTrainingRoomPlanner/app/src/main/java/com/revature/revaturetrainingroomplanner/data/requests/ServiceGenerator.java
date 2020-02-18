package com.revature.revaturetrainingroomplanner.data.requests;

import com.revature.revaturetrainingroomplanner.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static TRPAPI batchesAPI = retrofit.create(TRPAPI.class);

    public static TRPAPI getBatchesAPI() {
        return batchesAPI;
    }
}
