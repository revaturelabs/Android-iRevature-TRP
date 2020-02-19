package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.TrainerDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;
import com.revature.revaturetrainingroomplanner.data.requests.ServiceGenerator;
import com.revature.revaturetrainingroomplanner.data.requests.TRPAPI;
import com.revature.revaturetrainingroomplanner.data.requests.responses.TrainersGETResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainerRepository {

    private static final String TAG = "TrainerRepository";

    private AppDatabase mAppDatabase;
    private BaseDAO<Trainer> mDao;


    public TrainerRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(Trainer.class);
    }

    public void insertTrainerTask(Trainer... trainers) {
        Log.d(TAG, "insertCampusTask: inserting " + trainers.toString());
        new InsertAsyncTask(mDao).execute(trainers);
    }

    public LiveData<Trainer> retrieveByIDTask(int id) {
        return ((TrainerDAO)mDao).getByID(id);
    }

    public LiveData<List<Trainer>> retrieveAllTask() {
        return mDao.getAll();
    }

    public void updateTask(Trainer... trainers) {
        new UpdateAsyncTask(mDao).execute(trainers);
    }

    public void deleteTask(Trainer... trainers) {
        new DeleteAsyncTask(mDao).execute(trainers);
    }

    public void deleteAllTask(Trainer... trainers) {
        new DeleteAllAsyncTask(mDao).execute(trainers);
    }

    public void retrieveTrainersFromAPI() {
        TRPAPI trainersAPI = ServiceGenerator.getAPI();

        Call<TrainersGETResponse> responseCall = trainersAPI.getTrainers();

        responseCall.enqueue(new Callback<TrainersGETResponse>() {
            @Override
            public void onResponse(Call<TrainersGETResponse> call, Response<TrainersGETResponse> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());

                    List<Trainer> trainers = response.body().getTrainers();

                    insertTrainerTask(trainers.toArray(new Trainer[0]));
                } else {
                    try {
                        Log.d(TAG, "onResponse:  " + Objects.requireNonNull(response.errorBody()).string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TrainersGETResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
