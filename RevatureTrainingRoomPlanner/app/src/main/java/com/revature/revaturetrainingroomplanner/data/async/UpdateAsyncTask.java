package com.revature.revaturetrainingroomplanner.data.async;

import android.os.AsyncTask;
import android.util.Log;

import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;

public class UpdateAsyncTask<T, DAOT extends BaseDAO<T>> extends AsyncTask<T, Void, Void> {

    private static final String TAG = "UpdateAsyncTask";

    private DAOT mDAO;

    public UpdateAsyncTask(DAOT dao) {
        mDAO = dao;
    }

    @Override
    protected Void doInBackground(T... objects) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        mDAO.update(objects);
        return null;
    }

}
