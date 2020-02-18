package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Campus;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.CampusDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;

import java.util.List;

public class CampusRepository {

    private AppDatabase mAppDatabase;
    private BaseDAO<Campus> mDao;

    public CampusRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(Campus.class);
    }

    public void insertCampusTask(Campus... campuses) {
        new InsertAsyncTask(mDao).execute(campuses);
    }

    public LiveData<Campus> retrieveByIDTask(int id) {
        return ((CampusDAO)mDao).getByID(id);
    }

    public LiveData<List<Campus>> retrieveAllTask() {
        return mDao.getAll();
    }

    public void updateTask(Campus... campuses) {
        new UpdateAsyncTask(mDao).execute(campuses);
    }

    public void deleteTask(Campus... campuses) {
        new DeleteAsyncTask(mDao).execute(campuses);
    }

    public void deleteAllTask(Campus... campuses) {
        new DeleteAllAsyncTask(mDao).execute(campuses);
    }
    
}
