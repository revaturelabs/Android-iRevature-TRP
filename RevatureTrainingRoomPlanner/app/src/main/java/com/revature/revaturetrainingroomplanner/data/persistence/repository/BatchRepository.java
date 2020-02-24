package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Batch;
import com.revature.revaturetrainingroomplanner.data.model.BatchSkillCrossRef;
import com.revature.revaturetrainingroomplanner.data.model.BatchWithSkills;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BatchDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BatchSkillCrossRefDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.SkillDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;
import com.revature.revaturetrainingroomplanner.data.requests.ServiceGenerator;
import com.revature.revaturetrainingroomplanner.data.requests.TRPAPI;
import com.revature.revaturetrainingroomplanner.data.requests.responses.BatchesGETResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatchRepository {

    private static final String TAG = "BatchRepository";

    private AppDatabase mAppDatabase;
    private BatchDAO mDao;

    public BatchRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = (BatchDAO) mAppDatabase.getDAO(Batch.class);
    }

    public void insertBatchTask(Batch... batches) {
        Log.d(TAG, "insertCampusTask: inserting " + batches.toString());

        List<Skill> skills = new ArrayList<>();
        List<String> skillList;

        BatchSkillCrossRefDAO batchSkillCrossRefDAO = (BatchSkillCrossRefDAO) mAppDatabase.getDAO(BatchSkillCrossRef.class);
        SkillDAO skillDAO = (SkillDAO) mAppDatabase.getDAO(Skill.class);

        List<BatchSkillCrossRef> batchSkillCrossRefs = new ArrayList<>();

        for (Batch batch: batches) {
            skillList = batch.getSkills_required();

            for(String skill: skillList) {
                batchSkillCrossRefs.add(new BatchSkillCrossRef(batch.getBatch_id(), skill));

                Skill batch_skill= new Skill(skill);

                if (!skills.contains(batch_skill)) {
                    skills.add(batch_skill);
                }
            }
        }

        new InsertAsyncTask<>(mDao).execute(batches);

        new InsertAsyncTask<>(skillDAO).execute(skills.toArray(new Skill[0]));

        new InsertAsyncTask<>(batchSkillCrossRefDAO).execute(batchSkillCrossRefs.toArray(new BatchSkillCrossRef[0]));
    }

    public Batch retrieveByIDTask(long id) {
        return ((BatchDAO)mDao).getByID(id);
    }

    public LiveData<List<BatchWithSkills>> retrieveAllTask() {
        Log.d(TAG, "retrieveAllTask: retrieved all batches");
        return ((BatchDAO)mDao).getAllBatches();
    }

    public void updateTask(Batch... batches) {
        new UpdateAsyncTask<>(mDao).execute(batches);
    }

    public void deleteTask(Batch... batches) {
        new DeleteAsyncTask<>(mDao).execute(batches);
    }

    public void deleteAllTask(Batch... batches) {
        new DeleteAllAsyncTask<>(mDao).execute(batches);
    }

    public void retrieveBatchesFromAPI() {
        TRPAPI api = ServiceGenerator.getAPI();

        Call<BatchesGETResponse> responseCall = api.getBatches();

        Log.d(TAG, "retrieveBatchesFromAPI: requesting batches from API...");
        
        responseCall.enqueue(new Callback<BatchesGETResponse>() {
            @Override
            public void onResponse(Call<BatchesGETResponse> call, Response<BatchesGETResponse> response) {
                Log.d(TAG, "onResponse: server response: " + response.toString());
                if (response.code() == 200) {
                    Log.d(TAG, "onResponse: " + Objects.requireNonNull(response.body()).toString());

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
