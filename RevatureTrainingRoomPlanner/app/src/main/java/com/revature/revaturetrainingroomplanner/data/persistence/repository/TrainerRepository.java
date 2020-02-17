package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Trainer;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;

import java.util.List;

public class TrainerRepository {

    private AppDatabase mAppDatabase;
    private BaseDAO<Trainer> mDao;

    public TrainerRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(Trainer.class);
    }

    public void insertTrainerTask(Trainer... trainers) {
        new InsertAsyncTask(mDao).execute(trainers);
    }

    public LiveData<Trainer> retrieveByIDTask(int id) {
        return mDao.getByID(id);
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

}
