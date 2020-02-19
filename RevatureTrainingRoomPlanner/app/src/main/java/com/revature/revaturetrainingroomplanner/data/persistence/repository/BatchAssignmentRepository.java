package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.BatchAssignment;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;

import java.util.List;

public class BatchAssignmentRepository {

    private static final String TAG = "BatchAssignRepository";

    private AppDatabase mAppDatabase;
    private BaseDAO<BatchAssignment> mDao;

    public BatchAssignmentRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(BatchAssignment.class);
    }

    public void insertBatchAssignmentTask(BatchAssignment... batchAssignments) {
            Log.d(TAG, "insertBatchAssignmentTask: inserting " + batchAssignments);
            new InsertAsyncTask(mDao).execute(batchAssignments);
    }

    public LiveData<List<BatchAssignment>> retrieveAllTask() {
        Log.d(TAG, "retrieveAllTask: retrieved all batches");
        return mDao.getAll();
    }

    public void updateTask(BatchAssignment... batchAssignments) {
        new UpdateAsyncTask(mDao).execute(batchAssignments);
    }

    public void deleteTask(BatchAssignment... batchAssignments) {
        new DeleteAsyncTask(mDao).execute(batchAssignments);
    }

    public void deleteAllTask(BatchAssignment... batchAssignments) {
        new DeleteAllAsyncTask(mDao).execute(batchAssignments);
    }

}
