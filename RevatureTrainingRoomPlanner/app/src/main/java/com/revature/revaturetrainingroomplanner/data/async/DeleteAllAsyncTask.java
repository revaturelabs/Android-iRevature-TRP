package com.revature.revaturetrainingroomplanner.data.async;

import android.os.AsyncTask;
import android.util.Log;

import com.revature.revaturetrainingroomplanner.data.persistence.dao.BaseDAO;

public class DeleteAllAsyncTask<T, DAOT extends BaseDAO<T>> extends AsyncTask<T, Void, Void> {

    private static final String TAG = "DeleteAllAsyncTask";

    private DAOT mDAO;

    public DeleteAllAsyncTask(DAOT dao) {
        mDAO = dao;
    }

    @Override
    protected Void doInBackground(T... objects) {
        Log.d(TAG, "doInBackground: thread: " + Thread.currentThread().getName());
        mDAO.deleteAll();
        return null;
    }

}
