package com.revature.revaturetrainingroomplanner.data.persistence.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.revature.revaturetrainingroomplanner.data.async.DeleteAllAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.DeleteAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.InsertAsyncTask;
import com.revature.revaturetrainingroomplanner.data.async.UpdateAsyncTask;
import com.revature.revaturetrainingroomplanner.data.model.Skill;
import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;
import com.revature.revaturetrainingroomplanner.data.persistence.database.AppDatabase;

import java.util.List;

public class SkillRepository {

    private static final String TAG = "SkillRepository";

    private AppDatabase mAppDatabase;
    private BaseDAO<Skill> mDao;

    public SkillRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mDao = mAppDatabase.getDAO(Skill.class);
    }

    public void insertSkillTask(Skill... skills) {
        Log.d(TAG, "insertCampusTask: inserting " + skills.toString());
        new InsertAsyncTask(mDao).execute(skills);
    }

    public LiveData<List<Skill>> retrieveAllTask() {
        return mDao.getAll();
    }

    public void updateTask(Skill... skills) {
        new UpdateAsyncTask(mDao).execute(skills);
    }

    public void deleteTask(Skill... skills) {
        new DeleteAsyncTask(mDao).execute(skills);
    }

    public void deleteAllTask(Skill... skills) {
        new DeleteAllAsyncTask(mDao).execute(skills);
    }

}
