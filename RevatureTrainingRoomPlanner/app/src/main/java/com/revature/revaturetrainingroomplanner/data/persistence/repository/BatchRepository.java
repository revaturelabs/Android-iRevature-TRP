package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BatchDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;
import com.revature.revaturetrainingroomplanner.data.requests.ServiceGenerator;
import com.revature.revaturetrainingroomplanner.data.requests.TRPAPI;
import com.revature.revaturetrainingroomplanner.data.requests.responses.BatchesGETResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatchRepository {

    private static final String TAG = "BatchRepository";

    private AppDatabase mAppDatabase;
    private BaseDAO<Batch> mDao;

    public BatchRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(Batch.class);
    }

    public void insertBatchTask(Batch... batches) {
        new InsertAsyncTask(mDao).execute(batches);
    }

    public LiveData<Batch> retrieveByIDTask(int id) {
        return ((BatchDAO)mDao).getByID(id);
    }

    public LiveData<List<Batch>> retrieveAllTask() {
        Log.d(TAG, "retrieveAllTask: retrieved all batches");
        return mDao.getAll();
    }

    public void updateTask(Batch... batches) {
        new UpdateAsyncTask(mDao).execute(batches);
    }

    public void deleteTask(Batch... batches) {
        new DeleteAsyncTask(mDao).execute(batches);
    }

    public void deleteAllTask(Batch... batches) {
        new DeleteAllAsyncTask(mDao).execute(batches);
    }

    public void retrieveBatchesFromAPI() {
        TRPAPI batchesAPI = ServiceGenerator.getBatchesAPI();

        Call<BatchesGETResponse> responseCall = batchesAPI.getBatches();

        responseCall.enqueue(new Callback<BatchesGETResponse>() {
            @Override
            public void onResponse(Call<BatchesGETResponse> call, Response<BatchesGETResponse> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());

                    List<Batch>  batches = response.body().getBatches();

                    insertBatchTask(batches.toArray(new Batch[0]));
                } else {
                    try {
                        Log.d(TAG, "onResponse:  " + Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BatchesGETResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
