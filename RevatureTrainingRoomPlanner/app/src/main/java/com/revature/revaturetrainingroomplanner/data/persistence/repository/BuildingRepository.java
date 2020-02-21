package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Building;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;

import java.util.List;

public class BuildingRepository {

    private static final String TAG = "BuildingRepository";

    private AppDatabase mAppDatabase;
    private BaseDAO<Building> mDao;

    public BuildingRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(Building.class);
    }

    public void insertBuildingTask(Building... buildings) {
        Log.d(TAG, "insertCampusTask: inserting " + buildings.toString());
        new InsertAsyncTask(mDao).execute(buildings);
    }

    public LiveData<List<Building>> retrieveAllTask() {
        Log.d(TAG, "retrieveAllTask: retrieved all buildings");
        return mDao.getAll();
    }

    public void updateTask(Building... buildings) {
        new UpdateAsyncTask(mDao).execute(buildings);
    }

    public void deleteTask(Building... buildings) {
        new DeleteAsyncTask(mDao).execute(buildings);
    }

    public void deleteAllTask(Building... buildings) {
        new DeleteAllAsyncTask(mDao).execute(buildings);
    }

}
